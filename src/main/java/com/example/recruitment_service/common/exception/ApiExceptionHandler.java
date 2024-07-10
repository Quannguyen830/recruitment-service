package com.example.recruitment_service.common.exception;

import com.example.recruitment_service.common.response.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class ApiExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e) {
        if(e.getStatusCode().is4xxClientError()) {
            log.debug("Handling ApiException with error code {}, status {}, message {}",
                    e.getErrorCode(), e.getStatusCode(), e.getMessage());
        } else if (e.getStatusCode().is5xxServerError()) {
            log.error("Error with ApiException {}", e.getMessage());
        }
        return responseEntity(e.getErrorCode(), e.getStatusCode(), e.getMessage());
    }

    private ResponseEntity<?> responseEntity(int errorCode, HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                ApiResponse.error(errorCode, httpStatus.value(), message), httpStatus
        );
    }
}
