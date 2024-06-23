package com.example.recruitment_service.controller;


import com.example.recruitment_service.DtoIn.EmployerDtoIn;
import com.example.recruitment_service.DtoIn.PageDtoIn;
import com.example.recruitment_service.DtoIn.UpdatedEmployerDtoIn;
import com.example.recruitment_service.DtoOut.EmployerDtoOut;
import com.example.recruitment_service.DtoOut.PageDtoOut;
import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.common.response.ApiResponse;
import com.example.recruitment_service.model.Employer;
import com.example.recruitment_service.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employers")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @PostMapping("/")
    public ResponseEntity<?> addEmployer(@RequestBody EmployerDtoIn employer) {
        return ResponseController.responseEntity(() -> employerService.createEmployer(employer), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllEmployers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageDtoIn employerPage = new PageDtoIn(page, pageSize);

        return ResponseController.responseEntity(() -> employerService.getAllEmployers(employerPage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployer(@PathVariable long id) {
        employerService.deleteEmployer(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployerById(@PathVariable long id) {
        return ResponseController.responseEntity(() -> employerService.getEmployerById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployer(@PathVariable long id, @RequestBody UpdatedEmployerDtoIn employer) {
        employerService.updateEmployer(id, employer);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

}

