Spring BOOT with Spring MVC and REST(API and test client)

Using spring-boot-maven-plugin:
mvn package - for create un executable war using spring-boot-maven-plugin
mvn generate-sources - for generate Java classes dans le package com.sei.generate
                       We will use these classes when we implement the client and provider of the SOAP service

Il faut avoir la version entre le compilateur et le runtime(1.8 par ex)
java -jar target/SampleSpringBoot-1.0-SNAPSHOT.war - run the .war file

Spring MVC:
http://localhost:8090/client
http://localhost:8090/client/new
http://localhost:8090/client/1  - consult
http://localhost:8090/client/1/edit
http://localhost:8090/client/1/delete

REST Api:
http://localhost:8090/clients           HTTP GET - all clients
http://localhost:8090/clients/new       HTTP GET  - show creation window
http://localhost:8090/clients/new       HTTP POST - save created client
http://localhost:8090/clients/1         HTTP GET - consult single client
http://localhost:8090/clients/1/edit    HTTP GET   - show modification window
http://localhost:8090/clients/1/edit    HTTP POST  - save modificated client
http://localhost:8090/clients/1/delete  HTTP GET  - delete selected client and show the remaining list of clients

A simple client for REST api:
http://localhost:8090/rsclient
http://localhost:8090/rsclient/new
http://localhost:8090/rsclient/1  - consult
http://localhost:8090/rsclient/1/edit
http://localhost:8090/rsclient/1/delete

If we call the REST API from Angular add this annotation to Rest Controller:
@CrossOrigin(origins = "http://localhost:4200")

http://localhost:8090/h2-console
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Pasword: <leave this empty>

SOAP:
Soap WS wsdl acces pour SimpleSpringBootApplication.java
http://localhost:8090/ws/client.wsdl

1.Client for Client/Test WS generation(First Method)
>mkdir SEI
>cd SEI (Service Endpoint Interface)
>mkdir src

>wsimport -keep -s src http://localhost:8090/ws/client.wsdl
>wsimport -keep -s src http://localhost:8090/ws/test.wsdl

Copy files from sei/src into new project package called sei ou seitest.

2.Client for Client/Test WS generation(Second Method)
Using jaxb2-maven-plugin for generate Java classes from XML Schemas (client.xsd, test.xsd file located in src/main/resource/xsd directory)

A simple client for SOAP WS(version web):
http://localhost:8090/wsclient
http://localhost:8090/wsclient/new
http://localhost:8090/wsclient/1  - consult
http://localhost:8090/wsclient/1/edit
http://localhost:8090/wsclient/1/delete

A simple client for SOAP WS (version java): lancer SimpleSpringBootApplication.java that implements CommandLineRunner run()

Plugin Chrome for testing SOAP WS(Wizdler or soapUI or Boomerang) for browsing WSDL and make Request/Response.