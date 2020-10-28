//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.0 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.05.14 à 04:30:08 PM CEST 
//


package com.sei.generate;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sei.generate package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sei.generate
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FetchClientsRequest }
     * 
     */
    public FetchClientsRequest createFetchClientsRequest() {
        return new FetchClientsRequest();
    }

    /**
     * Create an instance of {@link FetchClientsResponse }
     * 
     */
    public FetchClientsResponse createFetchClientsResponse() {
        return new FetchClientsResponse();
    }

    /**
     * Create an instance of {@link ClientTO }
     * 
     */
    public ClientTO createClientTO() {
        return new ClientTO();
    }

    /**
     * Create an instance of {@link FetchClientByIdRequest }
     * 
     */
    public FetchClientByIdRequest createFetchClientByIdRequest() {
        return new FetchClientByIdRequest();
    }

    /**
     * Create an instance of {@link FetchClientByIdResponse }
     * 
     */
    public FetchClientByIdResponse createFetchClientByIdResponse() {
        return new FetchClientByIdResponse();
    }

    /**
     * Create an instance of {@link SaveClientRequest }
     * 
     */
    public SaveClientRequest createSaveClientRequest() {
        return new SaveClientRequest();
    }

    /**
     * Create an instance of {@link SaveClientResponse }
     * 
     */
    public SaveClientResponse createSaveClientResponse() {
        return new SaveClientResponse();
    }

    /**
     * Create an instance of {@link DeleteClientByIdRequest }
     * 
     */
    public DeleteClientByIdRequest createDeleteClientByIdRequest() {
        return new DeleteClientByIdRequest();
    }

    /**
     * Create an instance of {@link DeleteClientByIdResponse }
     * 
     */
    public DeleteClientByIdResponse createDeleteClientByIdResponse() {
        return new DeleteClientByIdResponse();
    }

    /**
     * Create an instance of {@link GetInfoRequest }
     * 
     */
    public GetInfoRequest createGetInfoRequest() {
        return new GetInfoRequest();
    }

    /**
     * Create an instance of {@link GetInfoResponse }
     * 
     */
    public GetInfoResponse createGetInfoResponse() {
        return new GetInfoResponse();
    }

}
