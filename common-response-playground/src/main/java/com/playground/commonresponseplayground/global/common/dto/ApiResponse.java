package com.playground.commonresponseplayground.global.common.dto;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final String message;
    private final int status;
    private final T result;

    private ApiResponse(String message, int status, T result) {
        this.message = message;
        this.status = status;
        this.result = result;
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(SUCCESS, 200, null);
    }

    public static <T> ApiResponse<T> success(T result) {
        return new ApiResponse<>(SUCCESS, 200, result);
    }

    public static ApiResponse<ErrorResponse> fail(int status, ErrorResponse errorResponse) {
        return new ApiResponse<>(FAIL, status, errorResponse);
    }

}
