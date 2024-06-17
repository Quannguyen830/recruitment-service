package com.example.recruitment_service.service;


import com.example.recruitment_service.model.Employer;
import com.example.recruitment_service.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    public Optional<Employer> createEmployer(Employer employer) {
        if(employerRepository.findByEmail(employer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        employer.setCreatedAt(LocalDate.now());
        employer.setUpdatedAt(LocalDate.now());
        return Optional.of(employerRepository.save(employer));
    }

    public Optional<Employer> updateEmployer(long id, Employer updatedEmployer) {
        if(employerRepository.existsById(id)) {
            throw new IllegalArgumentException("Employer does not exist");
        }
        return employerRepository.findById(id).map((employer) -> {
            employer.setName(updatedEmployer.getName());
            employer.setProvince(updatedEmployer.getProvince());
            employer.setDescription(updatedEmployer.getDescription());
            employer.setUpdatedAt(LocalDate.now());
            return employerRepository.save(employer);
        });
    }

    public Optional<Employer> findEmployerById(long id) {
        if(employerRepository.existsById(id)) {
            throw new IllegalArgumentException("Employer does not exist");
        }
        return employerRepository.findById(id);
    }

    public Page<Employer> getEmployers(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        return (Page<Employer>) employerRepository.findAll(pageable);
    }

    public void deleteEmployer(long id) {
        if(!employerRepository.existsById(id)) {
            throw new IllegalArgumentException("Employer does not exist");
        }
        employerRepository.deleteById(id);
    }
}

