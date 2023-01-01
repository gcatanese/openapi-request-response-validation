package com.tweesky.cloudtools;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.model.SimpleRequest;
import com.atlassian.oai.validator.model.SimpleResponse;

public class OpenApiValidator {

    private OpenApiInteractionValidator validator;

    public OpenApiValidator(String schema) {
        this.validator = OpenApiInteractionValidator.createForInlineApiSpecification(schema).build();
    }


    public boolean validate(OpenApiValidatorObject validatorObject) {
        boolean ret = true;

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

        validator.validate(requestBuilder.build(), responseBuilder.build());

        return true;
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
}
