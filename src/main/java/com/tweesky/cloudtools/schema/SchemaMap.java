package com.tweesky.cloudtools.schema;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SchemaMap {

    private static Map<String, String> schemaMap = new HashMap<>();

    public static void set(String key, String schema) {
        schemaMap.put(key, schema);
    }

    public static String get(String key) {
        return schemaMap.get(key);
    }

    public static Set<String> getKeys() {
        return schemaMap.keySet();
    }
}
