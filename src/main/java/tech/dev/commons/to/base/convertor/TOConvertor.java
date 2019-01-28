package tech.dev.commons.to.base.convertor;

import tech.dev.commons.to.base.TransferObject;
import tech.dev.entites.Entite;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite des TOConvertor
 * <p>
 * Date: 11/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public abstract class TOConvertor<E extends Entite, T extends TransferObject> {

    public abstract T entity2TO(E entity, boolean fillAll);

    public abstract E fillEntity(T to, E entity) throws Exception;

    public List<T> entityList2TOList(List<E> entityList, boolean fillAll) {
        List<T> toList = new ArrayList<T>();
        for (E e : entityList) {
            toList.add(entity2TO(e, fillAll));
        }
        return toList;
    }
}
