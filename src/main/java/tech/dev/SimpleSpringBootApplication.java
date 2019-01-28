package tech.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class SimpleSpringBootApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(SimpleSpringBootApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringBootApplication.class, args);
        LOGGER.debug("Spring Boot application ready");
    }
}
