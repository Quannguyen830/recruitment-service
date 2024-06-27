package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.DtoIn.EmployerDtoIn;
import com.example.recruitment_service.dto.DtoIn.PageDtoIn;
import com.example.recruitment_service.dto.DtoIn.UpdatedEmployerDtoIn;
import com.example.recruitment_service.dto.DtoOut.EmployerDtoOut;
import com.example.recruitment_service.dto.DtoOut.PageDtoOut;
import com.example.recruitment_service.model.Employer;

public interface EmployerService {
    EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn);
    EmployerDtoOut updateEmployer(long id, UpdatedEmployerDtoIn employer);
    EmployerDtoOut getEmployerById(Long id);
    PageDtoOut<Employer> getAllEmployers(PageDtoIn pageDtoIn);
    EmployerDtoOut deleteEmployer(Long id);
}
