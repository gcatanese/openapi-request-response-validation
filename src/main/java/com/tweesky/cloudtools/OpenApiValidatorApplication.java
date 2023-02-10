package com.tweesky.cloudtools;

import com.tweesky.cloudtools.config.ApplicationProperty;
import com.tweesky.cloudtools.schema.SchemaMap;
import com.tweesky.cloudtools.schema.SchemaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import javax.annotation.PostConstruct;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class OpenApiValidatorApplication {

    private final Logger log = LoggerFactory.getLogger(OpenApiValidatorApplication.class);

    public static final String DEFAULT_SCHEMA_KEY = "default";

    @Autowired
    private SchemaUtil schemaUtil;

    @Autowired
    private ApplicationProperty applicationProperty;

    public static void main(String[] args) {
        SpringApplication.run(OpenApiValidatorApplication.class, args);
    }


    @PostConstruct
    public void init() {
        String defaultSchema = schemaUtil.getDefaultContent();
        if(defaultSchema != null) {
            SchemaMap.set(DEFAULT_SCHEMA_KEY, defaultSchema);
        }
        log.info("Running on http://localhost:" + applicationProperty.getPort() );
    }
}
