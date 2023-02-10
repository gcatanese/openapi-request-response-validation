package com.tweesky.cloudtools.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperty {

    @Value("${server.port}")
    int port;

    @Value("${INPUT_SPECS:n/a}")
    private String inputSpecs;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getInputSpecs() {
        return inputSpecs;
    }

    public void setInputSpecs(String inputSpecs) {
        this.inputSpecs = inputSpecs;
    }
}
