package com.alencion.blog.common.utils;

import com.alencion.blog.common.exception.BlogAppException;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class AssertUtils {

    private static final String DEFAULT_MESSAGE = "[AssertException] ";


    public static void isNull(Object o) {
        isNull(o, getDefaultMessage("must not be null."));
    }

    public static void isNull(Object o, String message) {
        if (Objects.isNull(o)) throw new BlogAppException(message);
    }

    public static void nonNull(Object o) {
        nonNull(o, getDefaultMessage("must be null."));
    }

    public static void nonNull(Object o, String message) {
        if (Objects.nonNull(o)) throw new BlogAppException(message);
    }

    public static void isEmpty(String s) {
        isEmpty(s, getDefaultMessage(s + " must not be empty."));
    }

    public static void isEmpty(String s, String message) {
        if (!StringUtils.hasLength(s)) throw new BlogAppException(message);
    }

    public static void nonEmpty(String s) {
        nonEmpty(s, getDefaultMessage("must be empty."));
    }

    public static void nonEmpty(String s, String message) {
        if (!StringUtils.hasLength(s)) throw new BlogAppException(message);
    }

    private static String getDefaultMessage(String message) {
        return DEFAULT_MESSAGE + message;
    }

    private AssertUtils() {
        throw new AssertionError();
    }
}
