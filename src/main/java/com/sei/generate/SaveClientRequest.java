//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.0 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.11.09 à 06:13:15 PM CET 
//


package com.sei.generate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour saveClientRequest complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="saveClientRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://jaxws.clients.com/client}clientTO" minOccurs="0"/&gt;
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveClientRequest", propOrder = {
    "arg0",
    "arg1"
})
@XmlRootElement(name = "saveClientRequest")
public class SaveClientRequest {

    protected ClientTO arg0;
    protected boolean arg1;

    /**
     * Obtient la valeur de la propriété arg0.
     * 
     * @return
     *     possible object is
     *     {@link ClientTO }
     *     
     */
    public ClientTO getArg0() {
        return arg0;
    }

    /**
     * Définit la valeur de la propriété arg0.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientTO }
     *     
     */
    public void setArg0(ClientTO value) {
        this.arg0 = value;
    }

    /**
     * Obtient la valeur de la propriété arg1.
     * 
     */
    public boolean isArg1() {
        return arg1;
    }

    /**
     * Définit la valeur de la propriété arg1.
     * 
     */
    public void setArg1(boolean value) {
        this.arg1 = value;
    }

}
