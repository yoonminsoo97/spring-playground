package com.playground.commonresponseplayground.global.common.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.playground.commonresponseplayground.global.error.dto.ErrorResponse;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@JsonRootName("result")
@Getter
public class ApiResponse<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String SUCCESS_RESPONSE_PROPERTY_NAME = "data";
    private static final String FAIL_RESPONSE_PROPERTY_NAME = "error";

    private final String status;
    private final Map<String, T> data;

    private ApiResponse(String status, String propertyName, T data) {
        this.status = status;
        this.data = Collections.singletonMap(propertyName, data);
    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(SUCCESS_STATUS, SUCCESS_RESPONSE_PROPERTY_NAME, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(SUCCESS_STATUS, SUCCESS_RESPONSE_PROPERTY_NAME, data);
    }

    public static ApiResponse<ErrorResponse> fail(ErrorResponse errorResponse) {
        return new ApiResponse<>(FAIL_STATUS, FAIL_RESPONSE_PROPERTY_NAME, errorResponse);
    }

    @JsonAnyGetter
    public Map<String, T> getData() {
        return data;
    }

}
