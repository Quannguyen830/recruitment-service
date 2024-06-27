package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.dto.DtoIn.EmployerDtoIn;
import com.example.recruitment_service.dto.DtoIn.PageDtoIn;
import com.example.recruitment_service.dto.DtoIn.UpdatedEmployerDtoIn;
import com.example.recruitment_service.dto.DtoOut.EmployerDtoOut;
import com.example.recruitment_service.dto.DtoOut.PageDtoOut;
import com.example.recruitment_service.common.errorCode.ErrorCode;
import com.example.recruitment_service.common.exception.ApiException;
import com.example.recruitment_service.model.Employer;
import com.example.recruitment_service.repository.EmployerRepository;
import com.example.recruitment_service.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn) {
        if(employerRepository.findByEmail(employerDtoIn.getEmail()).isPresent()) {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Email is already in use");
        }
        Employer newEmployer = new Employer();
        newEmployer.setName(employerDtoIn.getName());
        newEmployer.setEmail(employerDtoIn.getEmail());
        newEmployer.setDescription(employerDtoIn.getDescription());
        newEmployer.setCreatedAt(LocalDate.now());
        newEmployer.setUpdatedAt(LocalDate.now());
        employerRepository.save(newEmployer);

        return EmployerDtoOut.from(newEmployer);
    }

    @Override
    public EmployerDtoOut updateEmployer(long id, UpdatedEmployerDtoIn updatedEmployer) {
        Employer employer = employerRepository.findById(id).orElseThrow(() ->
            new ApiException(ErrorCode.DATA_NOT_FOUND, HttpStatus.BAD_REQUEST, "Employer not found")
        );
        employer.setName(updatedEmployer.getUsername());
        employer.setId(updatedEmployer.getId());
        employer.setDescription(updatedEmployer.getDescription());
        employer.setProvince(updatedEmployer.getProvinceId());
        employer.setUpdatedAt(LocalDate.now());

        employerRepository.save(employer);

        return EmployerDtoOut.from(employer);
    }

    @Override
    public EmployerDtoOut getEmployerById(Long id) {
        Employer employer = employerRepository.findById(id).orElseThrow(() ->
            new ApiException(ErrorCode.DATA_NOT_FOUND, HttpStatus.BAD_REQUEST, "Employer not found")
        );

        return EmployerDtoOut.from(employer);
    }

    @Override
    public PageDtoOut<Employer> getAllEmployers(PageDtoIn pageDtoIn) {
        Pageable pageable = PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize());
        Page<Employer> page = employerRepository.findAll(pageable);
        return PageDtoOut.from(page.getTotalPages(), page.getSize(), page.getTotalElements()
        , page.getContent());
    }

    @Override
    public EmployerDtoOut deleteEmployer(Long id) {
        if(!employerRepository.existsById(id)) {
            return EmployerDtoOut.builder().build();
        }
        employerRepository.deleteById(id);
        return EmployerDtoOut.builder().build();
    }

}

