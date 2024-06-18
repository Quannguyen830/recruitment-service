package com.example.recruitment_service.common.response;

import com.example.recruitment_service.common.errorCode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private Integer errorCode;
    private Integer statusCode;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder().errorCode(ErrorCode.SUCCESS).statusCode(HttpStatus.OK.value())
                .data(data).build();
    }

    public static <T> ApiResponse<T> error(Integer errorCode, Integer statusCode, String message) {
        return ApiResponse.<T>builder().errorCode(errorCode).statusCode(statusCode).message(message).build();
    }

}
