package com.tweesky.cloudtools.schema;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SchemaUtil {

    /**
     * Get schema content from file
     * @param filepath
     * @return
     * @throws IOException
     */
    public static String getContent(String filepath) throws IOException {
        return new String(Files.readAllBytes(Path.of(filepath)), StandardCharsets.UTF_8);
    }

    public static String getContent(InputStream inputStream) throws IOException {
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
}
