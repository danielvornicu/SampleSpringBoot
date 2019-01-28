package tech.dev.entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Description de la classe
 * <p>
 * Date: 19/07/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

@Entity
@Table(name = "CLIENT")
@NamedQueries({
        @NamedQuery(
                name = "Client.findAll",
                query = "select c from Client c "
                        + "left join fetch c.adresse a "
                        + "left join fetch c.commandes "
        ),
        @NamedQuery(
                name = "Client.findById",
                query = "select c from Client c "
                        + "left join fetch c.adresse a "
                        + "left join fetch c.commandes "
                        + "where c.id = :id "
        ),
        @NamedQuery(
        name = "Client.findClientsByAdresseId",
        query = "select c from Client c "
                + "where c.adresse.id = :adresseId "
        ),
        @NamedQuery(
                name = "Client.deleteById",
                query = "delete from Client c "
                        + "where c.id = :id "
        ),
        @NamedQuery(
                name = "Client.deleteByAdreseId",
                query = "delete from Client c "
                        + "where c.adresse.id = :adresseId "
        )
})
public class Client extends Entite {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="PRENOM")
    private String prenom;

    @Column(name="NOM")
    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADRESSE_ID")
    private Adresse adresse;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    //@JsonIgnore
    private Set<Commande> commandes;

    public Client() {
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }

    @Override
    public String toString() {
        return "Client [" +
                "id=" + this.getId() + ", " +
                "prenom='" + this.getPrenom()+ "', " +
                "nom='" + this.getNom() + "', " +
                "adresse=" + this.getAdresse() + ", " +
                "commandes=" + this.getCommandes() +
                ']';
    }

}
