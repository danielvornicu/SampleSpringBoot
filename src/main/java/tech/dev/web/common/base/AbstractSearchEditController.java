package tech.dev.web.common.base;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tech.dev.commons.to.base.TransferObject;
import tech.dev.commons.utils.SessionManager;

import javax.validation.Valid;

/**
 * Controlleur abstrait pour gérer la recherche et la modification d'un TO
 * <p>
 * Date: 11/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public abstract class AbstractSearchEditController<T extends TransferObject,  F extends BaseForm<T>> extends BaseController{

    /**
     * Affiche la liste des objects
     * @param model le model
     * @return la vue contenant l'écran de recherche
     */

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {

        // On nettoyes la session à ce moment là car c'est le seul endroit sûr où on n'a pas besoin des SessionAttributes
        SessionManager.cleanupSpringSessionAttributes(model);

        initializeIndexTO(model);
        return getListView();
    }

    /**
     * Retourne le formulaire de consultation d'un objet
     * @param id l'id de l'objet à consulter ( :.+ permet de ne pas tronquer l'url finissant par un .)
     * @param form le formulaire de saisi
     * @param model le model
     * @return la vue pour consulter l'objet
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, F form, ModelMap model) {
        initializeShowTO(id, form, model);
        return getShowView();
    }

    /**
     * Mapping pour retourner le formulaire de création d'un objet
     * @param form le formulaire de saisi
     * @param model le model
     * @return la vue pour créer l'objet
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newObject(F form, ModelMap model) {
        initializeNewTO(form, model);
        return getEditView(form);
    }

    /**
     * Crée un objet.
     * @param form le formulaire de saisie de l'objet
     * @param result le bindingResult contenant éventuellement des erreurs
     * @param model le model
     * @param status le sessionStatus
     * @param redirectAttributes les atributes de redirection
     * @return la vue contenant l'écran de recherche
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid F form, BindingResult result, ModelMap model, SessionStatus status,  RedirectAttributes redirectAttributes) {
        return createOrUpdate(form, result, model, status, redirectAttributes, true);
    }

    /**
     * Retourne le formulaire de modification d'un objet
     * @param id l'id de l'objet à modifier
     * @param form le formulaire de saisi
     * @param model le model
     * @return la vue pour modifier l'objet
     */
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, F form, ModelMap model) {
        initializeEditTO(id, form, model);
        return getEditView(form);
    }

    /**
     * Modifie un objet.
     * @param id l'id de l'object à supprimer
     * @param form le formulaire de saisi
     * @param result le bindingResult contenant éventuellement des erreurs
     * @param model le model
     * @param status le sessionStatus
     * @param redirectAttributes les atributes de redirection
     * @return la vue contenant l'écran de recherche
     */
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @Valid F form, BindingResult result, ModelMap model, SessionStatus status,  RedirectAttributes redirectAttributes) {
        return createOrUpdate(form, result, model, status, redirectAttributes, false);
    }

    /**
     * Valide le formulaire ainsi que le TO associé.
     * <p/>
     * Si tout est valide, appelle la création ou la modification du TO
     * <p/>
     * Enfin, efface les données de la session et redirige vers la vue définie dans getRedirectAfterEdit
     * @param form le formulaire de saisie de l'objet
     * @param result le bindingResult contenant éventuellement des erreurs
     * @param model le model
     * @param status le sessionStatus
     * @param redirectAttributes les atributes de redirection
     * @param isCreation true si on est en création
     * @return la vue actuelle s'il y a des erreurs, la redirection définie dans getRedirectAfterEdit sinon. Dans le cas ajax avec erreur, retourne un JsonDialogReturn contenant l'erreur.
     */
    private String createOrUpdate(@Valid F form, BindingResult result, ModelMap model, SessionStatus status, RedirectAttributes redirectAttributes, boolean isCreation) {

        if (result.hasErrors()) {
            addErrorMessage(model, getAllErrorMessagesBindingResult(result));
        }

        T to = form.saveForm();

        //manual validation vs by annotation @Valid (in ClientForm)
/*        try {
            ValidationUtil.validData(to);
        } catch (Exception e) {
            addErrorMessage(model, e.getMessage());
            return getEditView(form);
        }*/

        if (result.hasErrors()) {
            return getEditView(form);
        }

        // On met à jour le TO
        saveTO(to, model, redirectAttributes, isCreation);

        // Efface les données de la session
        status.setComplete();
        return getRedirectAfterEdit(form, isCreation, false);
    }

    /**
     * Retourne la redirection utilisée après la modification. <br />
     * @param form form le formulaire de saisi
     * @param isCreation si la fonction est appelé lors d'une creation
     * @param isCancel si la fonction est appelé lors du cancel
     * @return la chaine de caractères correspondant à la redirection
     */
    protected String getRedirectAfterEdit(F form, boolean isCreation, boolean isCancel) {
        if (form.getRedirectUrl() != null) {
            if (isCancel || isCreation){
                return "redirect:" + form.getRedirectUrl();
            } else {
                return "redirect:" + PARENT_DIRECTORY + form.getRedirectUrl();
            }
        } else { //par default
            if (isCancel || isCreation){
                //apres un /cancel or /new on revient un seul pas en arriere pour revenir a la liste
                return "redirect:" + PARENT_DIRECTORY + getRootView();
            } else {
                //edit et validation
                //apres un edit on revient 2 pas en arriere pour revenir a la liste
                // .../spring-mvc/client/id/edit => .../spring-mvc/client (2 pas)
                return "redirect:" + DOUBLE_PARENT_DIRECTORY  + getRootView();
            }
        }
    }


    /**
     * Annule la modification et retourne sur la liste
     * @param form form le formulaire de saisi
     * @param status le sessionStatus
     * @return la vue pour la recherche
     */

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public String cancel(F form, SessionStatus status) {

        // Efface les données de la session
        status.setComplete();
        return getRedirectAfterEdit(form, false, true);
    }

    /**
     * Supprime un objet.
     * @param id l'id de l'object à supprimer
     * @param model le model
     * @param redirectAttributes les atributes de redirection
     * @return la vue contenant l'écran de recherche
     */

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, ModelMap model, RedirectAttributes redirectAttributes) {
        deleteTO(id, model, redirectAttributes);
        return "redirect:" + DOUBLE_PARENT_DIRECTORY + getRootView();
    }

    /**
     * Initialise les valeurs pour la liste totale des TO
     * @param model le model
     */
    protected abstract void initializeIndexTO(ModelMap model);

    /**
     * Initialise les valeurs pour la consultation d'un TO
     * @param id l'id du TO a consulter
     * @param form le formulaire de saisi
     * @param model le model
     */
    protected abstract void initializeShowTO(Long id, F form, ModelMap model);

    /**
     * Initialise les valeurs pour la création d'un nouveau TO
     * @param form le formulaire de saisi
     * @param model le model
     */
    protected abstract void initializeNewTO(F form, ModelMap model);

    /**
     * Initialise les valeurs pour la modification d'un TO
     * @param id l'id du TO à modifier
     * @param form le formulaire de saisi
     * @param model le model
     */
    protected abstract void initializeEditTO(Long id, F form, ModelMap model);

    /**
     * Appelle le service pour créer ou modifier le TO
     * @param to le TO à mettre à jour
     * @param model le model
     * @param redirectAttributes les atributes de redirection
     * @param isCreation true si on est en création
     * @return true si tout s'est bien passé et false sinon
     */
    protected abstract boolean saveTO(T to, ModelMap model, RedirectAttributes redirectAttributes, boolean isCreation);

    /**
     * Supprime un TO
     * @param id l'id du TO à supprimer
     * @param model le model
     * @param redirectAttributes les atributes de redirection
     */
    protected abstract void deleteTO(Long id, ModelMap model, RedirectAttributes redirectAttributes);

    /**
     * Retourne la chaine de caractère correspondant à la base des vues du controller
     * @return la chaine de caractères correspondant à la base des vues
     */
    protected abstract String getRootView();

    /**
     * Retourne la vue utilisée pour la liste
     * @return la chaine de caractères correspondant à la vue
     */
    protected String getListView() {
        return getRootView() + "Liste";
    }

    /**
     * Retourne la vue utilisée pour la consultation
     * @return la chaine de caractères correspondant à la vue
     */
    protected String getShowView() {
        return getRootView() + "Consult";
    }

    /**
     * Retourne la vue utilisée pour la modification
     * @return la chaine de caractères correspondant à la vue
     */
    protected String getEditView(F form) {
        return getRootView() + "Fiche";
    }

}
