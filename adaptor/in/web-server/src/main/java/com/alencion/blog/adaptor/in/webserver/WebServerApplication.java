package com.alencion.blog.adaptor.in.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.alencion.blog")
@SpringBootApplication
public class WebServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class, args);
    }
}