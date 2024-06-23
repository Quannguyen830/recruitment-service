package com.example.recruitment_service.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException extends RuntimeException {
    private Integer errorCode;
    private HttpStatus statusCode;

    public ApiException(Integer errorCode, HttpStatus statusCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
}
