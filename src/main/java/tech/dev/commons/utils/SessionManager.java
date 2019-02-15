package tech.dev.commons.utils;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe pour gérer les objets en session autres que ceux définis avec @SessionAttributes.
 * Pour mettre un objet en session, il faut obligatoirement passer par cette classe.
 * <p>
 * Date: 07/02/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public class SessionManager {

    private static final String SPRING_SESSION_ATTRIBUTES = "springSessionAttributes";

    private static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }

    /**
     * Nettoyes tous les SessionAttributes de Spring
     */
    public static void cleanupSpringSessionAttributes(ModelMap model) {
        @SuppressWarnings("unchecked")
        Set<String> springSessionAttributes = (Set<String>) getSession().getAttribute(SPRING_SESSION_ATTRIBUTES);

        if (CollectionUtil.isNotNullOrEmpty(springSessionAttributes)) {
            for (String springSessionAttribute : springSessionAttributes) {
                getSession().removeAttribute(springSessionAttribute);
                model.remove(springSessionAttribute);
            }
        }
        getSession().setAttribute(SPRING_SESSION_ATTRIBUTES, new HashSet<String>());
    }

    /**
     * Ajoute un attribut dans la liste des attributs spring en session
     * @param attributeName le nom de l'attribut à ajouter
     */
    public static void addSpringSessionAttribute(String attributeName) {
        @SuppressWarnings("unchecked")
        Set<String> springSessionAttributes = (Set<String>) getSession().getAttribute(SPRING_SESSION_ATTRIBUTES);
        springSessionAttributes.add(attributeName);
    }

    /**
     * Retire un attribut de la liste des attributs spring en session
     * @param attributeName le nom de l'attribut à retirer
     */
    public static void removeSpringSessionAttribute(String attributeName) {
        @SuppressWarnings("unchecked")
        Set<String> springSessionAttributes = (Set<String>) getSession().getAttribute(SPRING_SESSION_ATTRIBUTES);
        springSessionAttributes.remove(attributeName);
    }

    /**
     * Retourne un attribut en session
     * @return l'attribut trouvé en session
     */
    public static Object getAttribute(String attributeName) {
        return getSession().getAttribute(attributeName);
    }

    /**
     * Insert un attribut en session
     * @param attributeName le nom de l'attribut en session
     * @param objet l'objet correspondant à l'attribut
     */
    public static void setAttribute(String attributeName, Object objet) {
        setAttribute(attributeName, objet, false);
    }

    /**
     * Insert un attribut en session
     * @param attributeName le nom de l'attribut en session
     * @param objet l'objet correspondant à l'attribut
     * @param addInSpring true si on veut ajouter l'attribut dans les attributs de spring pour qu'il soit viré au cleanupSpringSessionAttributes (cas du redirectAttributes qui n'ajoute pas dans le model)
     */
    public static void setAttribute(String attributeName, Object objet, boolean addInSpring) {
        getSession().setAttribute(attributeName, objet);
        if (addInSpring) {
            addSpringSessionAttribute(attributeName);
        }
    }

    /**
     * Permet de supprimer un attribut mis en Session
     *
     * @param attributeName le nom de l'attribut
     */
    public static void removeAttribute(String attributeName) {
        getSession().removeAttribute(attributeName);
    }
}