package com.alencion.toolbox.logger;


import org.slf4j.Logger;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

public record AccessLog(
        HttpMethod method,
        String url,
        Flux<DataBuffer> requestBody,
        HttpStatusCode responseHttpCode

) {

    public static AccessLog of(ServerHttpRequest request, ServerHttpResponse response) {
        return new AccessLog(
                request.getMethod(),
                request.getURI().toString(),
                request.getBody(),
                response.getStatusCode()
        );
    }

    public void log(Logger logger) {
        requestBody.doOnNext(buffer -> {
            ByteArrayOutputStream bodyStream = new ByteArrayOutputStream();
            try (DataBuffer.ByteBufferIterator readableByteBuffers = buffer.readableByteBuffers()) {
                Channels.newChannel(bodyStream).write(readableByteBuffers.next());

                logger.debug("method:{} url:{} body:{} http_code:{}", method.name(), url, bodyStream, responseHttpCode.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
