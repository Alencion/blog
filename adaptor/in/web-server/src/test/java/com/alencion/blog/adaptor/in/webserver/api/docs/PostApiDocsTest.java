package com.alencion.blog.adaptor.in.webserver.api.docs;

import com.alencion.blog.adaptor.in.webserver.post.controller.PostCommandController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@WebFluxTest(controllers = PostCommandController.class)
@AutoConfigureRestDocs(uriHost = "blog.alencion.com")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class PostApiDocsTest {

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp(ApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .filter(WebTestClientRestDocumentation.documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void test() {
        WebTestClient.ResponseSpec exchange = this.webTestClient.post()
                .uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("""
                        {
                            "title": "title",
                            "mimeType": "application/json",
                            "content": "hello"
                        }
                        """))
                .exchange();

        exchange
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .consumeWith(WebTestClientRestDocumentation.document("post",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("title").type(JsonFieldType.STRING).description(""),
                                PayloadDocumentation.fieldWithPath("mimeType").type(JsonFieldType.STRING).description(""),
                                PayloadDocumentation.fieldWithPath("content").type(JsonFieldType.STRING).description("")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("content.title").type(JsonFieldType.STRING).description(""),
                                PayloadDocumentation.fieldWithPath("content.mimeType").type(JsonFieldType.STRING).description(""),
                                PayloadDocumentation.fieldWithPath("content.content").type(JsonFieldType.STRING).description("")
                        )));
    }
}

