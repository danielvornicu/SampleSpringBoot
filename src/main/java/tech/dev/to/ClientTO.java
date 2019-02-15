package tech.dev.to;


import tech.dev.commons.to.base.TransferObject;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Description de la classe
 * <p>
 * Date: 10/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

public class ClientTO extends TransferObject implements Serializable {

    private Long id;

    @NotEmpty(message = "{client.validation.prenom.empty}")
    private String prenom;

    @NotEmpty(message = "{client.validation.nom.empty}")
    private String nom;

    public ClientTO() {
        this.id = -1L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Client [" +
                "id=" + this.getId() + ", " +
                "prenom='" + this.getPrenom()+ "', " +
                "nom='" + this.getNom() +
                ']';
    }
}
