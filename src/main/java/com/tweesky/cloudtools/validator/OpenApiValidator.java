package com.tweesky.cloudtools.validator;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.model.SimpleRequest;
import com.atlassian.oai.validator.model.SimpleResponse;

import com.atlassian.oai.validator.report.ValidationReport;
import com.tweesky.cloudtools.dto.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class OpenApiValidator {

    private final Logger log = LoggerFactory.getLogger(OpenApiValidator.class);


    private OpenApiInteractionValidator validator;

    public OpenApiValidator(String schema) {
        this.validator = OpenApiInteractionValidator.createForInlineApiSpecification(schema)
                .build();
    }

    public ResponseData validate(OpenApiValidatorObject validatorObject) {

        SimpleRequest.Builder requestBuilder = SimpleRequest.Builder.get(validatorObject.getPath());
        if(validatorObject.getRequestBody() != null) {
            requestBuilder.withBody(validatorObject.getRequestBody());
        }

        SimpleResponse.Builder responseBuilder = SimpleResponse.Builder.status(validatorObject.getStatus());
        if(validatorObject.getResponseBody() != null) {
            responseBuilder.withBody(validatorObject.getResponseBody());
        }
        if(validatorObject.getResponseContentType() != null) {
            responseBuilder.withContentType(validatorObject.getResponseContentType());
        }

        var validationReport = validator.validate(requestBuilder.build(), responseBuilder.build());

        var responseData = new ResponseData();

        responseData.setValid(!validationReport.hasErrors());

        responseData.setErrors(validationReport.getMessages().stream()
                        .filter(message -> message.getLevel().equals(ValidationReport.Level.ERROR))
                .map(message -> message.getMessage())
                .collect(Collectors.toList()));

        responseData.setOthers(validationReport.getMessages().stream()
                .filter(message -> !message.getLevel().equals(ValidationReport.Level.ERROR))
                .map(message -> message.getMessage())
                .collect(Collectors.toList()));

        if(responseData.isValid()) {
            log.info("#### " + validatorObject.getMethod() + " " + validatorObject.getPath() + " is valid");
        } else {
            log.warn("#### " + validatorObject.getMethod() + " " + validatorObject.getPath() + " has errors");
            responseData.getErrors().stream().forEach(log::warn);
        }

        return responseData;
    }

    public static class Builder {

        private String method;
        private String path;
        private String requestBody;
        private String responseBody;


        Builder(String method) {
            this.method = method;
        }

        public static Builder forMethod(String method) {
            return new Builder(method);
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public void withRequestBody(String requestBody) {
            this.requestBody = requestBody;
        }

        public void withResponseBody(String responseBody) {
            this.path = responseBody;
        }
    }

    String getContent(String filepath) throws IOException {
        return new String(Files.readAllBytes(Path.of(filepath)), StandardCharsets.UTF_8);
    }


}
