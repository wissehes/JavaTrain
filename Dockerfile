#FROM amazoncorretto:21.0.4-alpine3.18
FROM openjdk:17-jdk-slim

LABEL authors="wissehes"
EXPOSE 8080

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
