package com.alencion.blog.common.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpStatusCodeException;

public class BlogApiException extends HttpStatusCodeException {

    protected BlogApiException(HttpStatusCode statusCode) {
        super(statusCode);
    }

    protected BlogApiException(HttpStatusCode statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
