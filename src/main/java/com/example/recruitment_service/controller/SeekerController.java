package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.SeekerDtoIn;
import com.example.recruitment_service.dto.dtoIn.UpdatedSeekerDtoIn;
import com.example.recruitment_service.dto.dtoOut.SeekerDtoOut;
import com.example.recruitment_service.service.SeekerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seekers")
public class SeekerController {

    private final SeekerService seekerService;

    @PostMapping("")
    public ResponseEntity<?> createSeeker(@Valid @RequestBody SeekerDtoIn seekerDtoIn) {
        return ResponseController.responseEntity(() -> seekerService.createSeeker(seekerDtoIn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSeeker(
            @Valid @RequestBody UpdatedSeekerDtoIn updatedSeekerDtoIn,
            @PathVariable BigInteger id
    ) {
        seekerService.updateSeeker(id, updatedSeekerDtoIn);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeekerById(@PathVariable BigInteger id) {
        return ResponseController.responseEntity(() -> seekerService.findSeekerById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllSeekers(@Valid PageDtoIn pageDtoIn) {
        return ResponseController.responseEntity(() -> seekerService.findAllSeeker(pageDtoIn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeeker(@PathVariable BigInteger id) {
        seekerService.deleteSeeker(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

}
