package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.entity.LoginDtoIn;
import com.example.recruitment_service.dto.dtoOut.LoginDtoOut;
import com.example.recruitment_service.model.user.Customer;
import lombok.extern.java.Log;

public interface AuthService {
    Customer register(LoginDtoIn loginDtoIn);
    LoginDtoOut login(LoginDtoIn loginDtoIn);
}
