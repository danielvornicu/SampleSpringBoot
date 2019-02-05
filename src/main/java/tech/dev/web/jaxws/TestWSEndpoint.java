package tech.dev.web.jaxws;

import com.sei.generate.GetInfoRequest;
import com.sei.generate.GetInfoResponse;
import com.sei.generate.ObjectFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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
public class TestWSEndpoint {
    private static final String NAMESPACE_URI = "http://jaxws.test.com/test";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInfoRequest")
    @ResponsePayload
    public GetInfoResponse hello(@RequestPayload GetInfoRequest request) {

        String outputString = "Hello " + request.getArg0() + "!";

        ObjectFactory factory = new ObjectFactory();
        GetInfoResponse response = factory.createGetInfoResponse();

        response.setReturn(outputString);
        return response;

//        QName qname = new QName("getInfoResponse");
//        JAXBElement<GetInfoResponse> jaxbElement = new JAXBElement<GetInfoResponse>(qname, GetInfoResponse.class, response);
//        return jaxbElement;
    }

}
