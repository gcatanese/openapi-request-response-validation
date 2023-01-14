package com.tweesky.cloudtools.schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SchemaUtil {

    private static final Logger log = LoggerFactory.getLogger(SchemaUtil.class);


    /**
     * Get schema content from file
     *
     * @param filepath
     * @return
     * @throws IOException
     */
    public static String getContent(String filepath) throws IOException {
        return new String(Files.readAllBytes(Path.of(filepath)), StandardCharsets.UTF_8);
    }

    /**
     * Get schema content from inputStream
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String getContent(InputStream inputStream) throws IOException {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    /**
     * Get schema from default file (openapi.yml or openapi.json)
     * @return
     */
    public static String getDefaultContent() {
        String ret = null;

        try {
            File openApiYml = new File("./openapi.yaml");
            if (openApiYml.exists()) {
                log.info("Loading schema from ./openapi.yaml");
                ret = getContent(openApiYml.getPath());
            } else {
                File openApiJson = new File("./openapi.json");
                if(openApiJson.exists()) {
                    log.info("Loading schema from ./openapi.json");
                    ret = getContent(openApiJson.getPath());
                } else {
                    log.info("Default schema file NOT found");
                }
            }
        } catch (IOException exception) {
            log.warn(exception.getMessage(), exception);
        }

        return ret;
    }
}
