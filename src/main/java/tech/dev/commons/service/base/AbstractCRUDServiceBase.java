package tech.dev.commons.service.base;

import tech.dev.commons.to.base.TransferObject;
import tech.dev.commons.to.base.convertor.TOConvertor;
import tech.dev.entites.Entite;

/**
 * Classe abstraite pour les services
 * <p>
 * Date: 11/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public abstract class AbstractCRUDServiceBase<E extends Entite, T extends TransferObject> {

    protected TOConvertor<E, T> convertor;

    public AbstractCRUDServiceBase() {
    }

    /**
     * Récupère le convertor
     * @return le convertor à utiliser
     */
    protected abstract TOConvertor<E, T> getConvertor();
}
