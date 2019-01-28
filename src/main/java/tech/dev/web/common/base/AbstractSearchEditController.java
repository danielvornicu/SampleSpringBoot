package tech.dev.web.common.base;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.dev.commons.to.base.TransferObject;

import javax.validation.Valid;

/**
 * Controlleur abstrait pour gérer la recherche et la modification d'un TO
 * <p>
 * Date: 11/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public abstract class AbstractSearchEditController<T extends TransferObject,  F extends BaseForm<T>> {

    /**
     * Affiche la liste des objects
     * @param model le model
     * @return la vue contenant l'écran de recherche
     */

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
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
     * @return la vue contenant l'écran de recherche
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(@Valid F form, ModelMap model) {

        T to = form.saveForm();
        saveTO(to, model, true);

        return "redirect:../"  + getRootView();
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
     * @param model le model
     * @return la vue contenant l'écran de recherche
     */
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @Valid F form, ModelMap model) {

        T to = form.saveForm();
        saveTO(to, model, false);

        return "redirect:../../" + getRootView();
    }

    /**
     * Annule la modification et retourne sur la liste
     * @param to le TO à annuler
     * @return la vue pour la recherche
     */

    @RequestMapping(value = "/cancel", method = RequestMethod.GET)
    public String cancel(T to ) {
        return "redirect:../" + getRootView();
    }

    /**
     * Supprime un objet.
     * @param id l'id de l'object à supprimer
     * @param model le model
     * @return la vue contenant l'écran de recherche
     */

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, ModelMap model) {
        deleteTO(id, model);
        return "redirect:../../" + getRootView();
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
     * @param isCreation true si on est en création
     * @return true si tout s'est bien passé et false sinon
     */
    protected abstract boolean saveTO(T to, ModelMap model, boolean isCreation);

    /**
     * Supprime un TO
     * @param id l'id du TO à supprimer
     * @param model le model
     */
    protected abstract void deleteTO(Long id, ModelMap model);

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
