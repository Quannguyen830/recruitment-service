package com.example.recruitment_service.common.exception;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.common.response.ApiResponse;
import com.example.recruitment_service.model.Employer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e) {
        return responseEntity(e.getErrorCode(), e.getStatusCode(), e.getMessage());
    }

    private ResponseEntity<Object> responseEntity(int errorCode, HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                ApiResponse.builder().errorCode(errorCode).statusCode(httpStatus.value())
                        .message(message).build(), httpStatus
        );
    }

}
