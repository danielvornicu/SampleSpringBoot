# First stage: complete build environment
FROM maven:3.5.0-jdk-8-alpine AS build

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn clean package -DskipTests

# Second stage: minimal runtime environment
FROM openjdk:8-jre-alpine

# copy jar from the first stage
COPY --from=build /target/SampleSpringBoot-1.0-SNAPSHOT.jar SampleSpringBoot.jar

# ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "SampleSpringBoot.jar"]