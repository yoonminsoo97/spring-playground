package com.playground.restdocs.post.controller;

import com.playground.restdocs.post.dto.PostDetailResponse;
import com.playground.restdocs.post.dto.PostWriteRequest;
import com.playground.restdocs.post.exception.NotFoundPostException;
import com.playground.restdocs.post.service.PostService;
import com.playground.restdocs.post.support.RestDocsTestSupport;

import org.junit.jupiter.api.Test;

import org.mockito.BDDMockito;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = PostController.class)
class PostControllerTest extends RestDocsTestSupport {

    @MockBean
    private PostService postService;

    @Test
    void postWrite() throws Exception {
        PostWriteRequest postWriteRequest = new PostWriteRequest("title", "writer", "content");

        BDDMockito.willDoNothing().given(postService).postWrite(BDDMockito.any(PostWriteRequest.class));

        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postWriteRequest))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(restDocs.document(
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("title")
                                        .type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                PayloadDocumentation.fieldWithPath("writer")
                                        .description(JsonFieldType.STRING)
                                        .description("게시글 작성자"),
                                PayloadDocumentation.fieldWithPath("content")
                                        .description(JsonFieldType.STRING)
                                        .description("게시글 내용")
                        )
                ));
    }

    @Test
    void postWriteInvalidInputValue() throws Exception {
        PostWriteRequest invalidPostWriteRequest = new PostWriteRequest("title", "", "content");

        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidPostWriteRequest))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("입력 값이 잘못되었습니다."))
                .andDo(restDocs.document(
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("title")
                                        .type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                PayloadDocumentation.fieldWithPath("writer")
                                        .description(JsonFieldType.STRING)
                                        .description("게시글 작성자"),
                                PayloadDocumentation.fieldWithPath("content")
                                        .description(JsonFieldType.STRING)
                                        .description("게시글 내용")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("message")
                                        .type(JsonFieldType.STRING)
                                        .description("에러 메세지")
                        )
                ));
    }

    @Test
    void postDetail() throws Exception {
        PostDetailResponse postDetailResponse = new PostDetailResponse(1L, "title", "writer", "content");

        BDDMockito.given(postService.postDetail(BDDMockito.anyLong())).willReturn(postDetailResponse);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/posts/{postNumber}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.postNumber").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.writer").value("writer"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("content"))
                .andDo(restDocs.document(
                        RequestDocumentation.pathParameters(
                                RequestDocumentation.parameterWithName("postNumber")
                                        .description("게시글 번호")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("postNumber")
                                        .type(JsonFieldType.NUMBER)
                                        .description("게시글 번호"),
                                PayloadDocumentation.fieldWithPath("title")
                                        .type(JsonFieldType.STRING)
                                        .description("게시글 제목"),
                                PayloadDocumentation.fieldWithPath("writer")
                                        .type(JsonFieldType.STRING)
                                        .description("게시글 작성자"),
                                PayloadDocumentation.fieldWithPath("content")
                                        .type(JsonFieldType.STRING)
                                        .description("게시글 내용")
                        )
                ));
    }

    @Test
    void postDetailNotFoundPost() throws Exception {
        BDDMockito.willThrow(new NotFoundPostException()).given(postService).postDetail(BDDMockito.anyLong());

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/posts/{postNumber}", 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("게시글을 찾을 수 없습니다."))
                .andDo(restDocs.document(
                        RequestDocumentation.pathParameters(
                                RequestDocumentation.parameterWithName("postNumber")
                                        .description("게시글 번호")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("message")
                                        .type(JsonFieldType.STRING)
                                        .description("에러 메세지")
                        )
                ));
    }

}