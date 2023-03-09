#
# Build stage
#
FROM maven:3.6.3-jdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM java:8
COPY --from=build /target/SampleSpringBoot-1.0-SNAPSHOT.jar SampleSpringBoot.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]