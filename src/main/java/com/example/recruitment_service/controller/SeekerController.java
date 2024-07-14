package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.SeekerDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedSeekerDtoIn;
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
        return ResponseController.responseEntity(() -> seekerService.add(seekerDtoIn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSeeker(
            @Valid @RequestBody UpdatedSeekerDtoIn updatedSeekerDtoIn,
            @PathVariable BigInteger id
    ) {
        seekerService.update(id, updatedSeekerDtoIn);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeekerById(@PathVariable BigInteger id) {
        return ResponseController.responseEntity(() -> seekerService.get(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllSeekers(@Valid PageDtoIn pageDtoIn) {
        return ResponseController.responseEntity(() -> seekerService.list(pageDtoIn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeeker(@PathVariable BigInteger id) {
        seekerService.delete(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

}
