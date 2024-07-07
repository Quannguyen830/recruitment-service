package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.request.entity.LoginDtoIn;
import com.example.recruitment_service.dto.response.LoginDtoOut;

public interface AuthService {
    LoginDtoOut login(LoginDtoIn loginDtoIn);
}
