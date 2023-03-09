# First stage: complete build environment
FROM maven:3.5.0-jdk-8-alpine AS build

# add pom.xml and source code
ADD ./pom.xml /home/app/pom.xml
ADD ./src /home/app/src

# package jar
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Second stage: minimal runtime environment
FROM openjdk:8-jre-alpine

# copy jar from the first stage
COPY --from=build /home/app/target/SampleSpringBoot-1.0-SNAPSHOT.jar /usr/local/lib/SampleSpringBoot.jar

# ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/local/lib/SampleSpringBoot.jar"]