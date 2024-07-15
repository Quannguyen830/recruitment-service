package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.filter.FilterByDayDtoIn;
import com.example.recruitment_service.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "Admin", description = "Admin operations")
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "Get statistics by day",
            description = "Retrieves statistics filtered by day based on the provided filter criteria.",
            requestBody = @RequestBody(description = "Filter criteria for statistics by day",
                    required = true,
                    content = @Content(schema = @Schema(implementation = FilterByDayDtoIn.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Invalid filter criteria",
                            content = @Content)
            })
    @GetMapping("/filterByDay")
    public ResponseEntity<?> getStatsByDay(@Valid @RequestBody FilterByDayDtoIn filterByDayDtoIn) {
        return ResponseController.responseEntity(() -> adminService.filterByDay(filterByDayDtoIn));
    }

    @Operation(summary = "Find matching seeker",
            description = "Finds a matching job seeker based on the job ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Matching seeker found successfully",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Matching seeker not found",
                            content = @Content)
            })
    @GetMapping("/findMatchingSeeker/{id}")
    public ResponseEntity<?> getMatchingSeeker(@PathVariable BigInteger id) {
        return ResponseController.responseEntity(() -> adminService.findMatchingJobAndSeeker(id));
    }

}
