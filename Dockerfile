FROM openjdk:17.0.2-jdk-slim

RUN mkdir -p /software
COPY target/openapi-request-response-validator.jar /software/openapi-request-response-validator.jar
COPY openapi/openapi.yaml /openapi/openapi.yaml
CMD java $JAVA_OPTS -jar /software/openapi-request-response-validator.jar
