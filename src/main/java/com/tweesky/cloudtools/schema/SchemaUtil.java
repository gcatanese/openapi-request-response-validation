package com.tweesky.cloudtools.schema;

import com.tweesky.cloudtools.config.ApplicationProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SchemaUtil {

    private static final Logger log = LoggerFactory.getLogger(SchemaUtil.class);

    @Autowired
    private ApplicationProperty applicationProperty;

    /**
     * Get schema content from file
     *
     * @param filepath
     * @return
     * @throws IOException
     */
    public String getContent(String filepath) throws IOException {
        return new String(Files.readAllBytes(Path.of(filepath)), StandardCharsets.UTF_8);
    }

    /**
     * Get schema content from inputStream
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public String getContent(InputStream inputStream) throws IOException {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    /**
     * Get schema from default file (openapi.yml or openapi.json)
     *
     * @return
     */
    public String getDefaultContent() {
        String ret = null;

        try {

            if (!applicationProperty.getInputSpecs().equalsIgnoreCase("n/a")) {
                File inputSpecs = new File(applicationProperty.getInputSpecs());
                if (inputSpecs.exists()) {
                    log.info("Loading schema from " + applicationProperty.getInputSpecs());
                    ret = getContent(inputSpecs.getPath());
                } else {
                    log.info(applicationProperty.getInputSpecs() + " NOT found");
                    throw new RuntimeException(applicationProperty.getInputSpecs() + " NOT found");
                }

            } else {

                File openApiYml = new File("openapi/openapi.yaml");
                if (openApiYml.exists()) {
                    log.info("Loading schema from openapi/openapi.yaml");
                    ret = getContent(openApiYml.getPath());
                } else {
                    File openApiJson = new File("openapi/openapi.json");
                    if (openApiJson.exists()) {
                        log.info("Loading schema from openapi/openapi.json");
                        ret = getContent(openApiJson.getPath());
                    } else {
                        log.info("Default schema file NOT found");
                    }
                }
            }
        } catch (IOException exception) {
            log.warn(exception.getMessage(), exception);
        }

        return ret;
    }
}
