package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.ResumeDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedResumeDtoIn;
import com.example.recruitment_service.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("")
    public ResponseEntity<?> createResume(@Valid @RequestBody ResumeDtoIn resumeDtoIn) {
        return ResponseController.responseEntity(() -> resumeService.add(resumeDtoIn));
    }

    @PutMapping("")
    public ResponseEntity<?> updateResume(
            BigInteger id,
            @Valid @RequestBody UpdatedResumeDtoIn updatedResumeDtoIn
    ) {
        resumeService.update(id, updatedResumeDtoIn);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResumeById(@PathVariable BigInteger id) {
        return ResponseController.responseEntity(() -> resumeService.get(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllResume(@Valid PageDtoIn pageDtoIn) {
        return ResponseController.responseEntity(() -> resumeService.list(pageDtoIn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResume(@PathVariable BigInteger id) {
        resumeService.delete(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }
}
