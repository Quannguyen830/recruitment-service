package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.JobDtoIn;
import com.example.recruitment_service.dto.dtoIn.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.UpdatedJobDtoIn;
import com.example.recruitment_service.service.impl.JobServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobServiceImpl jobService;

    @PostMapping("")
    public ResponseEntity<?> createJob(@Valid @RequestBody JobDtoIn jobDtoIn) {
        return ResponseController.responseEntity(() -> jobService.createJob(jobDtoIn), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(
            @Valid @RequestBody UpdatedJobDtoIn jobDtoIn,
            @PathVariable BigInteger id
    ) {
        jobService.updateJob(id, jobDtoIn);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllJobs(@Valid PageDtoIn pageDtoIn) {
        return ResponseController.responseEntity(() -> jobService.findAllJobs(pageDtoIn));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable BigInteger id) {
        return ResponseController.responseEntity(() -> jobService.findJobById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobById(@PathVariable BigInteger id) {
        jobService.deleteJobById(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

}
