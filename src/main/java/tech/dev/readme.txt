Spring BOOT with Spring MVC and REST(API and test client)

Using spring-boot-maven-plugin:
mvn package - for create un executable war using spring-boot-maven-plugin

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

http://localhost:8090/h2-console



