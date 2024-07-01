package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.dto.dtoIn.EmployerDtoIn;
import com.example.recruitment_service.dto.dtoIn.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.UpdatedEmployerDtoIn;
import com.example.recruitment_service.dto.dtoOut.EmployerDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.common.errorCode.ErrorCode;
import com.example.recruitment_service.common.exception.ApiException;
import com.example.recruitment_service.model.Employer;
import com.example.recruitment_service.repository.EmployerRepository;
import com.example.recruitment_service.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    @Override
    public EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn) {
        if(employerRepository.findByEmail(employerDtoIn.getEmail()).isPresent()) {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Email is already in use");
        }
        Employer newEmployer = EmployerDtoIn.from(employerDtoIn);
        return EmployerDtoOut.from(newEmployer);
    }

    @Override
    public void updateEmployer(long id, UpdatedEmployerDtoIn updatedEmployer) {
        Employer employer = employerRepository.findById(id).orElseThrow(() ->
            new ApiException(ErrorCode.DATA_NOT_FOUND, HttpStatus.BAD_REQUEST, "Employer not found")
        );
        employer.setName(updatedEmployer.getUsername());
        employer.setId(updatedEmployer.getId());
        employer.setDescription(updatedEmployer.getDescription());
        employer.setProvince(updatedEmployer.getProvinceId());
        employer.setUpdatedAt(LocalDate.now());
        employerRepository.save(employer);
    }

    @Override
    public EmployerDtoOut getEmployerById(Long id) {
        Employer employer = employerRepository.findById(id).orElseThrow(() ->
            new ApiException(ErrorCode.DATA_NOT_FOUND, HttpStatus.BAD_REQUEST, "Employer not found")
        );

        return EmployerDtoOut.from(employer);
    }

    @Override
    public PageDtoOut<EmployerDtoOut> getAllEmployers(PageDtoIn pageDtoIn) {
        Pageable pageable = PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize()
                , Sort.by("id").ascending());
        Page<Employer> page = employerRepository.findAll(pageable);
        return PageDtoOut.from(page.getTotalPages(), page.getSize(), page.getTotalElements()
        , page.stream().map(EmployerDtoOut::from).toList());
    }

    @Override
    public void deleteEmployer(Long id) {
        if(!employerRepository.existsById(id)) {
            EmployerDtoOut.builder().build();
            return;
        }
        employerRepository.deleteById(id);
        EmployerDtoOut.builder().build();
    }

}

