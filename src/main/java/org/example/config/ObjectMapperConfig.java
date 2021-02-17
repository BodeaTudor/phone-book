package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperConfig {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
