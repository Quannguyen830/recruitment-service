package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.entity.JobDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedJobDtoIn;
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

    @PostMapping
    public ResponseEntity<?> createJob(@Valid @RequestBody JobDtoIn jobDtoIn) {
        return ResponseController.responseEntity(() -> jobService.add(jobDtoIn), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(
            @Valid @RequestBody UpdatedJobDtoIn jobDtoIn,
            @PathVariable BigInteger id
    ) {
        jobService.update(id, jobDtoIn);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getAllJobs(@Valid PageDtoIn pageDtoIn) {
        return ResponseController.responseEntity(() -> jobService.list(pageDtoIn));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable BigInteger id) {
        return ResponseController.responseEntity(() -> jobService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobById(@PathVariable BigInteger id) {
        jobService.delete(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

}
