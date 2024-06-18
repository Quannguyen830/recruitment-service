package com.example.recruitment_service.service;

import com.example.recruitment_service.DtoIn.EmployerDtoIn;
import com.example.recruitment_service.DtoIn.PageDtoIn;
import com.example.recruitment_service.DtoIn.UpdatedEmployerDtoIn;
import com.example.recruitment_service.DtoOut.EmployerDtoOut;
import com.example.recruitment_service.DtoOut.PageDtoOut;
import com.example.recruitment_service.model.Employer;

public interface EmployerService {
    EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn);
    EmployerDtoOut updateEmployer(long id, UpdatedEmployerDtoIn employer);
    EmployerDtoOut getEmployerById(Long id);
    PageDtoOut<Employer> getAllEmployers(PageDtoIn pageDtoIn);
    EmployerDtoOut deleteEmployer(Long id);
}
