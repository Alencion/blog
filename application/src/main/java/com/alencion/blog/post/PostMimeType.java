package com.alencion.blog.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum PostMimeType {
    JSON("application/json"), MARKDOWN("text/x-markdown"), HTML("text/html"), UNKNOWN("unknown");

    private static final Map<String, PostMimeType> VALUE_MAP = Arrays.stream(values()).collect(Collectors.toMap(PostMimeType::getValue, Function.identity()));

    @JsonCreator
    public static PostMimeType of(String value) {
        return VALUE_MAP.getOrDefault(value.toLowerCase(), UNKNOWN);
    }

    private final String value;

    PostMimeType(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
