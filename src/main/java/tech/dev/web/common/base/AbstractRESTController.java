package tech.dev.web.common.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.dev.commons.to.base.TransferObject;

import java.util.List;

/**
 * Controlleur abstrait pour gérer la recherche et la modification d'un TO en mode REST
 * <p>
 * Date: 12/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public abstract class AbstractRESTController<T extends TransferObject> {

    /**
     * Affiche la liste des objects
     * @return la reponse HTTP avec la liste des objets et le status
     */

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<T>> index() {;
        List<T> list = initializeIndexTO();
        return new ResponseEntity<List<T>>(list, HttpStatus.OK);
    }

    /**
     * Retourne la reponse HTTP avec l'object TO et le status
     * @param id l'id de l'objet à consulter ( :.+ permet de ne pas tronquer l'url finissant par un .)
     * @return la reponse HTTP avec l'object TO et le status
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<T> show(@PathVariable("id") Long id) {
        try {
            T to = initializeShowTO(id);
            return new ResponseEntity<T>(to, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Supprime un objet.
     * @param id l'id de l'object à supprimer
     * @return la reponse HTTP avec la liste des TO apres supression et le status
     */

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ResponseEntity<List<T>> delete(@PathVariable("id") Long id) {
        List<T> list = deleteTO(id);
        return new ResponseEntity<List<T>>(list, HttpStatus.OK);
    }

    /**
     * Crée un objet.
     * @param to l'object à creer
     * @return la reponse HTTP avec la liste des TO apres insertion et le status
     */

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<List<T>> create(@RequestBody T to) {
        List<T> list = saveTO(to, true);
        return new ResponseEntity<List<T>>(list, HttpStatus.OK);
    }

    /**
     * Modifie un objet.
     * @param to l'object à modifier
     * @return la reponse HTTP avec la liste des TO apres modification et le status
     */

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public ResponseEntity<List<T>> update(@RequestBody T to) {
        List<T> list = saveTO(to, false);
        return new ResponseEntity<List<T>>(list, HttpStatus.OK);
    }

    /**
     * Initialise les valeurs pour la liste totale des TO
     * @return la liste des to
     */
    protected abstract List<T> initializeIndexTO();

    /**
     * Initialise les valeurs pour la consultation d'un TO
     * @param id l'id du TO a consulter
     * @return le TO a consulter
     */

    protected abstract T initializeShowTO(Long id);

    /**
     * Appelle le service pour créer ou modifier le TO
     * @param to le TO à mettre à jour
     * @param isCreation true si on est en création
     * @return la liste des to apres creation/modification
     */
    protected abstract List<T> saveTO(T to, boolean isCreation);

    /**
     * Supprime un TO
     * @param id l'id du TO a supprimer
     * @return la liste des to apres suppresion
     */
    protected abstract List<T> deleteTO(Long id);
}
