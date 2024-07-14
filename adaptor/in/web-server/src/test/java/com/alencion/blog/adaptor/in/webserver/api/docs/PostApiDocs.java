package com.alencion.blog.adaptor.in.webserver.api.docs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@WebFluxTest
@AutoConfigureRestDocs

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class PostApiDocs {

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp(@Autowired ApplicationContext context) {

        this.webTestClient = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                //                .entityExchangeResultConsumer(document("{class-name}/{method-name}"))
                .build();
    }

    @Test
    void test() {
        this.webTestClient.get()
                .uri("/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(document("index", links(
                        linkWithRel("alpha").description("Link to the alpha resource"),
                        linkWithRel("bravo").description("Link to the bravo resource"))));
    }
}
