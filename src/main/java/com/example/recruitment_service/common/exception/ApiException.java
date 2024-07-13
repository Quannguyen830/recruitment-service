package com.example.recruitment_service.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
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
