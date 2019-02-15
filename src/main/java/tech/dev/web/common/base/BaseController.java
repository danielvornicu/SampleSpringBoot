package tech.dev.web.common.base;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import tech.dev.commons.constantes.ConstantesGlobales;

/**
 * Description de la classe
 * <p>
 * Date: 05/02/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public abstract class BaseController {

    public static final String SAUT_LIGNE_HTML = ConstantesGlobales.SAUT_LIGNE_HTML;
    public static final String DOUBLE_SAUT_LIGNE_HTML = SAUT_LIGNE_HTML + SAUT_LIGNE_HTML;

    public static final String PARENT_DIRECTORY = ConstantesGlobales.PARENT_DIRECTORY;
    public static final String DOUBLE_PARENT_DIRECTORY = PARENT_DIRECTORY + PARENT_DIRECTORY;


    /**
     * Retourne le message d'erreur contenu dans le model
     * @param model le model
     * @return le message d'erreur
     */
    protected static String getErrorMessage(ModelMap model) {
        return (String) model.get("errorMessage");
    }

    /**
     * Ajoute un message d'erreur dans le model
     * @param model le model
     * @param message le message d'erreur
     */
    protected static void addErrorMessage(ModelMap model, String message) {
        String errorMessage = getErrorMessage(model);

        // On remplace les sauts de ligne par des <br /> pour qu'ils soient correctement affichés dans les JSP
        if (errorMessage != null) {
            // Si il y avait déjà un message d'erreur, on les concatène
            errorMessage += SAUT_LIGNE_HTML + message.replaceAll(System.getProperty("line.separator"), SAUT_LIGNE_HTML);
        } else {
            errorMessage = message.replaceAll(System.getProperty("line.separator"), SAUT_LIGNE_HTML);
        }

        model.put("errorMessage", errorMessage);
    }

    /**
     * Retourne tous les messages d'erreur du bindingResult séparés par des <br />
     * @param result le bindingResult contenant les erreurs
     * @return tous les messages d'erreur du bindingResult séparés par des <br />
     */
    protected String getAllErrorMessagesBindingResult(BindingResult result) {
        String errorMessage = "";

        for (ObjectError error : result.getAllErrors()) {
            // On enlève les double quotes présentes nécessaires pour l'affichage classique(non ajax) de spring
            if (error.getDefaultMessage() != null) {
                errorMessage += error.getDefaultMessage().replaceAll("''", "'") + SAUT_LIGNE_HTML;
            }
        }

        return errorMessage;
    }
}
