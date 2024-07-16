package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.entity.LoginDtoIn;
import com.example.recruitment_service.dto.dtoOut.LoginDtoOut;
import lombok.extern.java.Log;

public interface AuthService {
    void register(LoginDtoIn loginDtoIn);
    LoginDtoOut login(LoginDtoIn loginDtoIn);
}
