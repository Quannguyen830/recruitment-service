package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.entity.LoginDtoIn;
import com.example.recruitment_service.service.AuthService;
import com.example.recruitment_service.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Log4j2
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDtoIn loginDtoIn) {
        return ResponseController.responseEntity(() -> authService.login(loginDtoIn));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid LoginDtoIn loginDtoIn) {
        log.info("Registering...");
        return ResponseController.responseEntity(() -> authService.register(loginDtoIn));
    }
}
