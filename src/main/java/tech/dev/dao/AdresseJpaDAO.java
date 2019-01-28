package tech.dev.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Description de la classe
 * <p>
 * Date: 19/07/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */
@Repository
public class AdresseJpaDAO {

    @PersistenceContext
    EntityManager em;

    private static Logger LOGGER = LoggerFactory.getLogger(AdresseJpaDAO.class);

    public AdresseJpaDAO() {
        super();
    }

    public void deleteById(Long id){
        Query query = em.createQuery("DELETE FROM Adresse a WHERE a.id = :Id");
        int count = query.setParameter("Id", id).executeUpdate();
        //System.out.println("Nombre d'adresses supprimés: "  + count);
        LOGGER.debug("Nombre d'adresses supprimés: "  + count);
    }
}
