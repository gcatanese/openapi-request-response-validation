FROM openjdk:17-jdk-slim

RUN mkdir -p /software
COPY target/openapi-request-response-validator.jar /software/openapi-request-response.validator.jar

WORKDIR /software
CMD java $JAVA_OPTS -jar openapi-request-response.validator.jar
