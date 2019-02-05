package tech.dev.web.jaxws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tech.dev.service.ClientService;
import tech.dev.to.ClientTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * JAX-RS Web Service
 * <p>
 * Date: 13/12/2018
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

//change portType name ClientWS(class name) -> ClientWebServ
// port name ClientWSPort -> ClientWebServicePort
// service name(ClientWS -> ClientWebService)
// targetNamespace "http://jaxws.web.dev.tech/" -> "http://jaxws.clients.com/"
// in WSDL

// NOT USED !!!!!!!!! with Spring-WS
//@WebService (actually javax.jws.WebService) is defined by JSR-181 which is a standard way of declaring WebService endpoints
// in the spirit of JavaEE 5+.

//component used to create bean using context.getBean() and pass it to Endpoint.publish() in WSApplication
@Component
//@Service
@WebService(name="ClientWebServ", portName= "ClientWebServicePort", serviceName = "ClientWebService",
targetNamespace = "http://jaxws.clients.com/"
)
public class ClientWS extends SpringBeanAutowiringSupport {

    //Dependency Injection (DI) via Spring
    private ClientService clientService;

    @Autowired
    //@WebMethod(exclude=true)
    //public au lieu de private si on declare le bean au niveau xml( spring-jaxws-applicationContext.xml)
    private void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }


    // action http://jaxws.web.dev.tech/Client/getAllClientsRequest to "fetch_clients"
    // operation name "getAllClient" -> "fetchClients"
    @WebMethod(action="fetch_clients", operationName = "fetchClients")
    public List<ClientTO> getAllClients() {
        //2.liste des TO
        List<ClientTO> clients = clientService.findAllFillTO();

        return clients;

    }

    //@WebMethod(exclude = true)
    @WebMethod(action="fetch_client", operationName = "fetchClientById")
    public ClientTO getClientById(Long id) {
        //with TO
        ClientTO client = clientService.findByIdFillTO(id);
        return client;
    }

    @WebMethod(action="save_client", operationName = "saveClient")
    public boolean saveClient(ClientTO to, boolean isCreation) {
        clientService.saveTO(to, isCreation);
        return true;
    }

    @WebMethod(action="delete_client", operationName = "deleteClientById")
    public void deleteClient(Long id) {
        clientService.deleteClientByClientId(id);
    }


}
