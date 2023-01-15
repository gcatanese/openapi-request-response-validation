package com.tweesky.cloudtools;

import com.tweesky.cloudtools.schema.SchemaUtil;
import com.tweesky.cloudtools.validator.OpenApiValidator;
import com.tweesky.cloudtools.validator.OpenApiValidatorObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class OpenApiValidatorTest {

    private final Logger log = LoggerFactory.getLogger(OpenApiValidatorTest.class);

    @Autowired
    SchemaUtil schemaUtil;

    @Test
    public void validate() throws IOException {
        String schema = "src/test/resources/schema/BasicJson.json";

        OpenApiValidator validator = new OpenApiValidator(schemaUtil.getContent(schema));

        var responseData = validator.validate(OpenApiValidatorObject.forMethod("GET")
                .withPath("/users/1")
                .withHeaders(new ArrayList<>())
                .withResponseBody(getUserResponseBody())
                .withResponseContentType("application/json")
                .withStatus(200)
        );

        assertTrue(responseData.isValid());
    }

    @Test
    public void validateFail() throws IOException {
        String schema = "src/test/resources/schema/BasicJson.json";

        OpenApiValidator validator = new OpenApiValidator(schemaUtil.getContent(schema));

        var responseData = validator.validate(OpenApiValidatorObject.forMethod("POST")
                .withPath("/users/1")
                .withHeaders(new ArrayList<>())
                .withResponseBody(getUserResponseBody())
                .withResponseContentType("application/json")
                .withStatus(200)
        );

        assertFalse(responseData.isValid());
    }

    private String getUserResponseBody() {
        return "{" +
                " \"id\": 1," +
                " \"firstName\": \"Beppe\"," +
                " \"lastName\": \"Catanese\"" +
                "}";

    }

}
