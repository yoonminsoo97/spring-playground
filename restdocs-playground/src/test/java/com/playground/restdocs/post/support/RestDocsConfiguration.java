package com.playground.restdocs.post.support;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

@TestConfiguration
public class RestDocsConfiguration {

    @Bean
    public RestDocumentationResultHandler restDocumentationResultHandler() {
        return MockMvcRestDocumentation.document(
                "{class-name}/{method-name}",       //  Rest Docs 이름 설정
                Preprocessors.preprocessRequest(    // 공통 요청 헤더 설정
                        Preprocessors.modifyHeaders()
                                .remove("Content-length")
                                .remove("Host"),
                        Preprocessors.prettyPrint() // 요청 json 내용 깔끔하게 출력
                ),
                Preprocessors.preprocessResponse(   // 공통 응답 헤더 설정
                        Preprocessors.modifyHeaders()
                                .remove("Content-length")
                                .remove("X-Content-Type.Options"),
                        Preprocessors.prettyPrint() // 응답 json 내용 깔끔하게 출력
                )
        );
    }

}
