# First stage: complete build environment
FROM maven:3.5.0-jdk-8-alpine AS build

# pom.xml and source code
COPY src /home/app/src
COPY pom.xml /home/app

# package jar
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Second stage: minimal runtime environment
FROM openjdk:8-jre-alpine

# copy jar from the first stage
COPY --from=build /home/app/target/SampleSpringBoot-1.0-SNAPSHOT.jar /usr/local/lib/SampleSpringBoot.jar

# ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/local/lib/SampleSpringBoot.jar"]