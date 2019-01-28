package tech.dev.entites;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Description de la classe
 * <p>
 * Date: 19/07/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

@Entity
@Table(name = "ADRESSE")
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "LIGNE1")
    private String ligne1;

    @Column(name = "LIGNE2")
    private String ligne2;

    @Column(name = "CODE_POSTAL")
    private Integer codePostal;

    @Column(name = "VILLE")
    private String ville;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLigne1() {
        return ligne1;
    }

    public void setLigne1(String ligne1) {
        this.ligne1 = ligne1;
    }

    public String getLigne2() {
        return ligne2;
    }

    public void setLigne2(String ligne2) {
        this.ligne2 = ligne2;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Adresse [" +
                "id=" + this.getId() + ", " +
                "ligne1='" + this.getLigne1() + "', " +
                "ligne2='" + this.getLigne2() + "', " +
                "codePostal=" + this.getCodePostal() + ", " +
                "ville='" + this.getVille() + '\'' +
                ']';
    }
}
