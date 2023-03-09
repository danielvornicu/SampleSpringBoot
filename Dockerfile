#
# Build stage
#
FROM maven:3.5.0-jdk-8-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:8-jre-alpine
COPY --from=build /target/SampleSpringBoot-1.0-SNAPSHOT.jar SampleSpringBoot.jar

# ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java","-jar","SampleSpringBoot.jar"]