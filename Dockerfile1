# First stage: complete build environment
FROM maven:3.8.6-jdk-8-slim AS builder

# pom.xml and source code
COPY . .

# package jar
RUN mvn clean package -DskipTests

# Second stage: runtime environment
FROM openjdk:8-jdk-alpine

# copy jar from the first stage
COPY --from=builder /target/SampleSpringBoot-1.0-SNAPSHOT.jar SampleSpringBoot.jar

# ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "SampleSpringBoot.jar"]