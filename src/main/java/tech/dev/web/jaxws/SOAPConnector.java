package tech.dev.web.jaxws;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

/**
 * Description de la classe
 * <p>
 * Date: 04/02/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

// Using WebServiceTemplate - is the core class for client-side Web service access in Spring-WS
// It contains methods for sending requests and receiving response messages
// Additionally, it can marshal objects to XML before sending them across a transport, and unmarshal any response XML into an object again

public class SOAPConnector extends WebServiceGatewaySupport {

    public Object callWebService(String url, Object request){
        return getWebServiceTemplate().marshalSendAndReceive(url, request);
    }
}
