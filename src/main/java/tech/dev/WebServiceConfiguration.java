package tech.dev;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Description de la classe
 * <p>
 * Date: 29/01/2019
 *
 * @author d.vornicu
 * @version 1.0 $Revision$ $Date$
 */

//@EnableWs - enable the support for the @Endpoint annotation

@EnableWs
@Configuration
public class WebServiceConfiguration {

    //1. Configuration for produce Spring Web Service(SOAP)
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        //MessageDispatcherServlet - this is a standard Servlet which extends from the standard Spring Web DispatcherServlet
        //and wraps a MessageDispatcher

        // In other words: the MessageDispatcherServlet combines the attributes of the MessageDispatcher and DispatcherServlet
        // and as a result allows the handling of XML messages over HTTP

        //Note that it is important to inject and set the ApplicationContext to the MessageDispatcherServlet,
        // otherwise it will not automatically detect other Spring Web Services related beans (such as the lower Wsdl11Definition)

        // URI Mapping is "/ws/*"
        // The web container will use this path to map incoming HTTP requests to the servlet

        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }

    @Bean(name = "client")
    public Wsdl11Definition defaultWsdl11Definition(XsdSchema clientsSchema) {
        //Version 1: The SimpleWsdl11Definition exposes a standard WSDL 1.1 using the specified WSDL file:
        //SimpleWsdl11Definition definition = new SimpleWsdl11Definition();
        //definition.setWsdl(new ClassPathResource("/wsdl/client.wsdl"));

        //Version 2: The DefaultWsdl11Definition exposes a standard WSDL 1.1 using the specified schema .xsd file:
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("ClientWebServ");
        definition.setTargetNamespace("http://jaxws.clients.com/client");
        definition.setServiceName("ClientWebService");
        definition.setLocationUri("/ws/client");
        definition.setSchema(clientsSchema);

        return definition;
    }

    @Bean(name = "clientsSchema")
    public XsdSchema clientsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/client.xsd"));
    }

    @Bean(name = "test")
    public Wsdl11Definition  testWsdl11Definition(XsdSchema testSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("TestWebServ");
        definition.setTargetNamespace("http://jaxws.test.com/test");
        definition.setServiceName("TestWebService");
        definition.setLocationUri("/ws/test");
        definition.setSchema(testSchema);

        return definition;
    }

    @Bean(name = "testSchema")
    public XsdSchema testSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/test.xsd"));
    }

}
