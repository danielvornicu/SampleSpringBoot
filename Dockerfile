# First stage: complete build environment
FROM maven:3.8.3-openjdk-17 AS builder

# pom.xml and source code
COPY . .

# package jar
RUN mvn clean install

# Second stage: runtime environment
FROM openjdk:17-jdk-slim

# copy jar from the first stage
COPY --from=builder /target/SampleSpringBoot-1.0-SNAPSHOT.jar SampleSpringBoot.jar

# ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "SampleSpringBoot.jar"]