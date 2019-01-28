package tech.dev.entites;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Description de la classe
 * <p>
 * Date: 19/07/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

@Entity
@Table(name = "COMMANDE")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NOMBRE_PRODUITS")
    private Integer nombreProduits;

    @Column(name = "MONTANT")
    private Long montant;

    // volontairement je n'utilise pas Date & Time API
    @Column(name = "DATE_COMMANDE")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNombreProduits() {
        return nombreProduits;
    }

    public void setNombreProduits(Integer nombreProduits) {
        this.nombreProduits = nombreProduits;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
