package com.playground.commonresponseplayground.global.error.dto;

import com.playground.commonresponseplayground.global.error.exception.ErrorType;

import lombok.Getter;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final List<Field> fields;

    private ErrorResponse(ErrorType errorType) {
        this.code = errorType.getErrorCode();
        this.message = errorType.getMessage();
        this.fields = new ArrayList<>();
    }

    private ErrorResponse(ErrorType errorType, BindingResult bindingResult) {
        this.code = errorType.getErrorCode();
        this.message = errorType.getMessage();
        this.fields = Field.errorList(bindingResult);
    }

    public static ErrorResponse of(ErrorType errorType) {
        return new ErrorResponse(errorType);
    }

    public static ErrorResponse of(ErrorType errorType, BindingResult bindingResult) {
        return new ErrorResponse(errorType, bindingResult);
    }

    @Getter
    private static class Field {

        private final String field;
        private final String input;
        private final String message;

        private Field(FieldError fieldError) {
            this.field = fieldError.getField();
            this.input = fieldError.getRejectedValue() == null ? "" : fieldError.getRejectedValue().toString();
            this.message = fieldError.getDefaultMessage();
        }

        public static List<Field> errorList(BindingResult bindingResult) {
            return bindingResult.getFieldErrors()
                    .stream()
                    .map(Field::new)
                    .collect(Collectors.toList());
        }

    }

}
