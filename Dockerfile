FROM openjdk:21-jdk-slim

RUN mkdir -p /software
COPY target/openapi-request-response-validator.jar /software/openapi-request-response-validator.jar

CMD java $JAVA_OPTS -jar /software/openapi-request-response-validator.jar
