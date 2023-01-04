package com.tweesky.cloudtools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import javax.annotation.PostConstruct;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class OpenApiValidatorApplication {

    private final Logger log = LoggerFactory.getLogger(OpenApiValidatorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(OpenApiValidatorApplication.class, args);
    }


    @PostConstruct
    public void init() {
        log.info("Running on http://localhost:8080");
    }
}
