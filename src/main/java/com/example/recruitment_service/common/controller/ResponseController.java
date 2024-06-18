package com.example.recruitment_service.common.controller;

import com.example.recruitment_service.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseController {
    public static <T> ResponseEntity<ApiResponse<T>> responseEntity(CallbackFunction<T> callbackFunction) {
        return responseEntity(callbackFunction, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> responseEntity(CallbackFunction<T> callbackFunction, HttpStatus httpStatus) {
        T result = callbackFunction.execute();
        return ResponseEntity.status(httpStatus).body(ApiResponse.success(result));
    }
}
