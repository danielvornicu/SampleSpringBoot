package tech.dev;

/**
 * Description de la classe
 * <p>
 * Date: 24/01/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
    }

}