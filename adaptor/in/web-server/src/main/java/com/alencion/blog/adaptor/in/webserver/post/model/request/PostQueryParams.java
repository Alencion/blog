package com.alencion.blog.adaptor.in.webserver.post.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Optional;

@Getter
public class PostQueryParams {
    private final int size;
    private final int page;
    private final String order;

    @JsonCreator
    public PostQueryParams(@JsonProperty("size") Integer size,
                           @JsonProperty("page") Integer page,
                           @JsonProperty("order") String order) {
        this.size = Optional.ofNullable(size).orElse(20);
        this.page = Optional.ofNullable(page).orElse(0);
        this.order = order;
    }
}
