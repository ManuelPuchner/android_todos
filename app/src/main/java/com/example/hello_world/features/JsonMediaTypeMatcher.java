package com.example.hello_world.features;

import jakarta.ws.rs.core.MediaType;

/** an interface that amends a class with a function to check for the application/json MediaType
 */
public interface JsonMediaTypeMatcher {
    public static final String APPLICATION = "application";
    public static final String JSON = "json";

    default boolean isJson(MediaType mediaType) {
        return mediaType.getType().equalsIgnoreCase(APPLICATION) && mediaType.getSubtype().equalsIgnoreCase(JSON);
    }
}
