package com.tweesky.cloudtools.schema;

import com.tweesky.cloudtools.config.ApplicationProperty;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SchemaUtilTest {

    @Autowired
    ApplicationProperty applicationProperty;

    @Autowired
    SchemaUtil schemaUtil;

    @Test
    void getContent() throws IOException {
        String filepath = "src/test/resources/schema/BasicJson.json";
        assertTrue(schemaUtil.getContent(filepath).contains("\"openapi\" : \"3.0.0\""));
    }

    @Test
    void testGetContent() throws IOException {
        String filepath = "src/test/resources/schema/BasicJson.json";
        InputStream inputStream = new FileInputStream(filepath);
        assertTrue(schemaUtil.getContent(inputStream).contains("\"openapi\" : \"3.0.0\""));

    }

    @Test
    void getDefaultContent() {
        String filepath = "src/test/resources/schema/BasicJson.json";
        applicationProperty.setInputSpecs(filepath);
        assertTrue(schemaUtil.getDefaultContent().contains("\"openapi\" : \"3.0.0\""));
    }
}