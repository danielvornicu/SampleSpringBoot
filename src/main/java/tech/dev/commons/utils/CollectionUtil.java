package tech.dev.commons.utils;

import java.util.Collection;

/**
 * Utilitaire pour les collections
 * <p>
 * Date: 07/02/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public class CollectionUtil {
    /**
     * Détermine si une collection est non vide et non nulle
     * @param collection la collection à tester
     * @return vrai si la collection est non vide et non nulle, faux sinon
     */
    public static boolean isNotNullOrEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * Détermine si une collection est vide ou nulle
     * @param collection la collection à tester
     * @return vrai si la collection est vide ou nulle, faux sinon
     */
    public static boolean isNullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
