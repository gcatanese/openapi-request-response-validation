package com.tweesky.cloudtools;

import com.tweesky.cloudtools.util.SchemaUtil;
import com.tweesky.cloudtools.validator.OpenApiValidator;
import com.tweesky.cloudtools.validator.OpenApiValidatorObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class OpenApiValidatorTest {

    @Test
    public void validate() throws IOException {
        String schema = "src/test/resources/schema/ManagementService-v1.json";

        String path = "/companies/{{companyId}}/webhooks";
        String response = "src/test/resources/json/mgmt-api-response.json";

        OpenApiValidator validator = new OpenApiValidator(SchemaUtil.getContent(schema));

        validator.validate(OpenApiValidatorObject.forMethod("GET")
                .withPath(path)
                .withResponseBody(SchemaUtil.getContent(response))
                .withResponseContentType("application/json")
                .withStatus(200)
        );

    }

}
