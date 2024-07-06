package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.entity.EmployerDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedEmployerDtoIn;
import com.example.recruitment_service.dto.dtoOut.EmployerDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;

public interface EmployerService {
    EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn);
    void updateEmployer(long id, UpdatedEmployerDtoIn employer);
    EmployerDtoOut getEmployerById(Long id);
    PageDtoOut<EmployerDtoOut> getAllEmployers(PageDtoIn pageDtoIn);
    void deleteEmployer(Long id);
}
