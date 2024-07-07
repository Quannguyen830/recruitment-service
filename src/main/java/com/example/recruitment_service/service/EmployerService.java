package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.request.entity.EmployerDtoIn;
import com.example.recruitment_service.dto.request.entity.PageDtoIn;
import com.example.recruitment_service.dto.request.updateEntity.UpdatedEmployerDtoIn;
import com.example.recruitment_service.dto.response.EmployerDtoOut;
import com.example.recruitment_service.dto.response.PageDtoOut;

public interface EmployerService {
    EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn);
    void updateEmployer(long id, UpdatedEmployerDtoIn employer);
    EmployerDtoOut getEmployerById(Long id);
    PageDtoOut<EmployerDtoOut> getAllEmployers(PageDtoIn pageDtoIn);
    void deleteEmployer(Long id);
}
