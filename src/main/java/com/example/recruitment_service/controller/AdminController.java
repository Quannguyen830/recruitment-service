package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.request.filter.FilterByDayDtoIn;
import com.example.recruitment_service.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/filterByDay")
    public ResponseEntity<?> getStatsByDay(@Valid @RequestBody FilterByDayDtoIn filterByDayDtoIn) {
        return ResponseController.responseEntity(() -> adminService.filterByDay(filterByDayDtoIn));
    }

    @GetMapping("/findMatchingSeeker/{id}")
    public ResponseEntity<?> getMatchingSeeker(@PathVariable BigInteger id) {
        return ResponseController.responseEntity(() -> adminService.findMatchingJobAndSeeker(id));
    }

}
