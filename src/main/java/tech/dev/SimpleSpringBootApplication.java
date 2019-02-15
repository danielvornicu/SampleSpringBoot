package tech.dev;

import com.sei.generate.ClientTO;
import com.sei.generate.FetchClientsRequest;
import com.sei.generate.FetchClientsResponse;
import com.sei.generate.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;

/**
 * Description de la classe
 * <p>
 * Date: 24/01/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

// same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication(scanBasePackages={"tech.dev"})
public class SimpleSpringBootApplication implements CommandLineRunner {
    private static Logger LOGGER = LoggerFactory.getLogger(SimpleSpringBootApplication.class);

    @Autowired
    private WebServiceTemplate webServiceTemplate;


    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringBootApplication.class, args);
        LOGGER.debug("Spring Boot application ready");
    }

    //implements CommandLineRunner run() method to test une methode du Web Service (pas obligatoire)
    @Override
    public void run(String... strings) throws Exception {
        ObjectFactory factory = new ObjectFactory();
        FetchClientsRequest request = factory.createFetchClientsRequest();

        FetchClientsResponse response = (FetchClientsResponse) webServiceTemplate.marshalSendAndReceive(request);
        List<ClientTO> clients = response.getReturns();
        LOGGER.debug("CALL SOAP WS response is all clients list: ");
        for (ClientTO client : clients) {
            LOGGER.debug(client.getId()  + ", " + client.getPrenom()  + ", " + client.getNom());
        }
    }

}
