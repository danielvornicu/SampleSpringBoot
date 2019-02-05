package tech.dev.web.jaxws;

import com.sei.generate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tech.dev.service.ClientService;
import tech.dev.to.ClientTO;

/**
 * Description de la classe
 * <p>
 * Date: 31/01/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

//@Endpoint (actually org.springframework.ws.server.endpoint.annotation. Endpoint) is Spring-WS sepcific annotation
// which is a member of @Component family of annotations - it is both used by autodiscovery (component scan) mechanism
// and as an annotation to decorate WebService endpoints (invoked by Spring-WS' handlers).

//If you're using Spring-WS you can't use @WebService annotation - Spring can't scan these classes and SpringWS can't invoke them.

@Endpoint
public class ClientWSEndpoint {
    private static Logger LOGGER = LoggerFactory.getLogger(ClientWSEndpoint.class);

    private static final String NAMESPACE_URI = "http://jaxws.clients.com/client";

    //Dependency Injection (DI) via Spring
    private ClientService clientService;

    @Autowired
    private void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "fetchClientsRequest")
    @ResponsePayload
    public FetchClientsResponse getAllClients(@RequestPayload FetchClientsRequest request) {

        ObjectFactory factory = new ObjectFactory();
        FetchClientsResponse response = factory.createFetchClientsResponse();

        for (ClientTO clientTO : clientService.findAllFillTO()) {

            com.sei.generate.ClientTO client =  new com.sei.generate.ClientTO();
            client.setId(clientTO.getId());
            client.setPrenom(clientTO.getPrenom());
            client.setNom(clientTO.getNom());

            //pas de setter generé
            response.getReturns().add(client);

            LOGGER.info("Endpoint adding client '{}' to response FetchClientsResponse.",  client.getPrenom() + " "+ client.getNom());
        }

        //QName qname = new QName("fetchClientsResponse");
        //JAXBElement<FetchClientsResponse> jaxbElement = new JAXBElement<FetchClientsResponse>(qname, FetchClientsResponse.class, response);
        //return jaxbElement;

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "fetchClientByIdRequest")
    @ResponsePayload
    public FetchClientByIdResponse getClientById(@RequestPayload FetchClientByIdRequest request) {

        ObjectFactory factory = new ObjectFactory();
        FetchClientByIdResponse response = factory.createFetchClientByIdResponse();
        //
        ClientTO clientTO = clientService.findByIdFillTO(request.getArg0());

        com.sei.generate.ClientTO client =  new com.sei.generate.ClientTO();
        client.setId(clientTO.getId());
        client.setPrenom(clientTO.getPrenom());
        client.setNom(clientTO.getNom());

        response.setReturn(client);

        return response;

        //QName qname = new QName("fetchClientByIdResponse");
        //JAXBElement<FetchClientByIdResponse> jaxbElement = new JAXBElement<FetchClientByIdResponse>(qname, FetchClientByIdResponse.class, response);
        //return jaxbElement;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "saveClientRequest")
    @ResponsePayload
    public SaveClientResponse saveClient(@RequestPayload SaveClientRequest request) {

        ObjectFactory factory = new ObjectFactory();
        SaveClientResponse response = factory.createSaveClientResponse();
        //
        ClientTO to =  new ClientTO();
        to.setId(request.getArg0().getId());
        to.setPrenom(request.getArg0().getPrenom());
        to.setNom(request.getArg0().getNom());
        //
        boolean isCreation = request.isArg1();
        clientService.saveTO(to, isCreation);

        response.setReturn(true);
        return response;

        //QName qname = new QName("saveClientResponse");
        //JAXBElement<SaveClientResponse> jaxbElement = new JAXBElement<SaveClientResponse>(qname, SaveClientResponse.class, response);
        //return jaxbElement;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteClientByIdRequest")
    @ResponsePayload
    public DeleteClientByIdResponse deleteClient(@RequestPayload DeleteClientByIdRequest request) {

        ObjectFactory factory = new ObjectFactory();
        DeleteClientByIdResponse response = factory.createDeleteClientByIdResponse();
        //
        clientService.deleteClientByClientId(request.getArg0());

        return response;

        //QName qname = new QName("deleteClientByIdResponse");
        //JAXBElement<DeleteClientByIdResponse> jaxbElement = new JAXBElement<DeleteClientByIdResponse>(qname, DeleteClientByIdResponse.class, response);
        //return jaxbElement;
    }

}
