package com.example.recruitment_service.controller;


import com.example.recruitment_service.model.Employer;
import com.example.recruitment_service.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/employers")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @PostMapping("/add")
    public ResponseEntity<?> addEmployer(@RequestBody Employer employer) {
        try {
            Employer createdEmployer = employerService.createEmployer(employer).orElseThrow();
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllEmployers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        Page<Employer> employerPage = employerService.getEmployers(page, pageSize);

        Map<String, Object> response = new HashMap<>();
        response.put("employers", employerPage.getNumber() + 1);
        response.put("pageSize", employerPage.getSize());
        response.put("totalElements", employerPage.getTotalElements());
        response.put("totalPages", employerPage.getTotalPages());
        response.put("data", employerPage.getContent());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployer(@PathVariable long id) {
        employerService.deleteEmployer(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Object());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployerById(@PathVariable long id) {
        Employer employer = null;
        if(employerService.findEmployerById(id).isPresent()) {
            employer = employerService.findEmployerById(id).get();
        }
        return ResponseEntity.status(HttpStatus.OK).body(employer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployer(@PathVariable long id, @RequestBody Employer employer) {
        Optional<Employer> employerOptional = employerService.updateEmployer(id, employer);
        if(employerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employer not found");
        }
    }

}

