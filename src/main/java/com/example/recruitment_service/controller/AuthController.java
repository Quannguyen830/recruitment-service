package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.entity.LoginDtoIn;
import com.example.recruitment_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "User authentication operations")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "User login",
            description = "Authenticates a user and returns an authentication token.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User login credentials",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoginDtoIn.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login successful",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "401", description = "Invalid login credentials",
                            content = @Content)
            })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDtoIn loginDtoIn) {
        return ResponseController.responseEntity(() -> authService.login(loginDtoIn));
    }
}
