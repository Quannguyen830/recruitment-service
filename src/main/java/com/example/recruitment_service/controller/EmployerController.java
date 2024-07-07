package com.example.recruitment_service.controller;

import com.example.recruitment_service.dto.request.entity.EmployerDtoIn;
import com.example.recruitment_service.dto.request.entity.PageDtoIn;
import com.example.recruitment_service.dto.request.updateEntity.UpdatedEmployerDtoIn;
import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.service.impl.BaseRedisServiceImpl;
import com.example.recruitment_service.service.impl.EmployerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employers")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerServiceImpl employerService;
    private final BaseRedisServiceImpl baseRedisService;

    @PostMapping
    public ResponseEntity<?> addEmployer(@Valid @RequestBody EmployerDtoIn employer) {
        return ResponseController.responseEntity(() -> employerService.createEmployer(employer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployers(@Valid PageDtoIn pageDtoIn) {
        return ResponseController.responseEntity(() -> employerService.getAllEmployers(pageDtoIn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployer(@PathVariable long id) {
        employerService.deleteEmployer(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployerById(@PathVariable long id) {
        baseRedisService.set("employer",String.valueOf(id));
        return ResponseController.responseEntity(() -> employerService.getEmployerById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployer(@PathVariable long id, @Valid @RequestBody UpdatedEmployerDtoIn employer) {
        employerService.updateEmployer(id, employer);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

}

