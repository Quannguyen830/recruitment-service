package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.entity.EmployerDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedEmployerDtoIn;
import com.example.recruitment_service.dto.dtoOut.EmployerDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;

public interface EmployerService {
    EmployerDtoOut add(EmployerDtoIn employerDtoIn);
    void update(long id, UpdatedEmployerDtoIn employer);
    EmployerDtoOut get(Long id);
    PageDtoOut<EmployerDtoOut> list(PageDtoIn pageDtoIn);
    void delete(Long id);
}
