package com.alencion.blog.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PostMimeTypeTest {

    private final ObjectMapper objectMapper;

    public PostMimeTypeTest() {
        this.objectMapper = JsonMapper.builder()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .addModule(new JavaTimeModule())
                .build();

    }

    void serializeJson() throws JsonProcessingException {
        PostMimeType markdown = PostMimeType.MARKDOWN;

        String s = objectMapper.writeValueAsString(markdown);
        Assertions.assertThat(s).isEqualTo("text/x-markdown");
    }
}
