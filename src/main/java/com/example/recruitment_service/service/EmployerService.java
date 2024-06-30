package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.EmployerDtoIn;
import com.example.recruitment_service.dto.dtoIn.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.UpdatedEmployerDtoIn;
import com.example.recruitment_service.dto.dtoOut.EmployerDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.model.Employer;

public interface EmployerService {
    EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn);
    void updateEmployer(long id, UpdatedEmployerDtoIn employer);
    EmployerDtoOut getEmployerById(Long id);
    PageDtoOut<Employer> getAllEmployers(PageDtoIn pageDtoIn);
    void deleteEmployer(Long id);
}
