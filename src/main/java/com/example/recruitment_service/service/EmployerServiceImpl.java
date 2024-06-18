package com.example.recruitment_service.service;

import com.example.recruitment_service.DtoIn.EmployerDtoIn;
import com.example.recruitment_service.DtoIn.PageDtoIn;
import com.example.recruitment_service.DtoIn.UpdatedEmployerDtoIn;
import com.example.recruitment_service.DtoOut.EmployerDtoOut;
import com.example.recruitment_service.DtoOut.PageDtoOut;
import com.example.recruitment_service.model.Employer;
import com.example.recruitment_service.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public EmployerDtoOut createEmployer(EmployerDtoIn employerDtoIn) {
        if(employerRepository.findByEmail(employerDtoIn.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
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
            new IllegalArgumentException("Employer not found")
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
            new IllegalArgumentException("Employer not found")
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

