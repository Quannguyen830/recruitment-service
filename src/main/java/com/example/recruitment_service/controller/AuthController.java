package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.entity.LoginDtoIn;
import com.example.recruitment_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDtoIn loginDtoIn) {
        return ResponseController.responseEntity(() -> authService.login(loginDtoIn));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody LoginDtoIn loginDtoIn) {
        authService.register(loginDtoIn);
        return ResponseController.responseEntity(() -> HttpStatus.OK);
    }
}
