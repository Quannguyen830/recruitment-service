package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.entity.LoginDtoIn;
import com.example.recruitment_service.dto.dtoOut.LoginDtoOut;

public interface AuthService {
    LoginDtoOut login(LoginDtoIn loginDtoIn);
}
