package com.tweesky.cloudtools.util;

import java.io.IOException;
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
}
