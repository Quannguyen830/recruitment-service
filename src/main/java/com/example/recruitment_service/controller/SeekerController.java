package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.SeekerDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedSeekerDtoIn;
import com.example.recruitment_service.service.SeekerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seekers")
@Tag(name = "Seeker", description = "Manage Seeker")
public class SeekerController {

    private final SeekerService seekerService;

    @Operation(summary = "Create a new job seeker",
            description = "Creates a new job seeker and stores it in the database.",
            requestBody = @RequestBody(description = "Details of the job seeker to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = SeekerDtoIn.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Job seeker successfully created",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content)
            })
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody SeekerDtoIn seekerDtoIn) {
        return ResponseController.responseEntity(() -> seekerService.add(seekerDtoIn), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing job seeker",
            description = "Updates the details of an existing job seeker based on the given ID.",
            requestBody = @RequestBody(description = "Updated details of the job seeker",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UpdatedSeekerDtoIn.class))),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Job seeker successfully updated",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Job seeker not found",
                            content = @Content)
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Valid @RequestBody UpdatedSeekerDtoIn updatedSeekerDtoIn,
            @PathVariable BigInteger id
    ) {
        seekerService.update(id, updatedSeekerDtoIn);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get job seeker by ID",
            description = "Retrieves the details of a specific job seeker by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Job seeker details",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Job seeker not found",
                            content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable BigInteger id) {
        return ResponseController.responseEntity(() -> seekerService.get(id));
    }

    @Operation(summary = "List all job seekers",
            description = "Retrieves a paginated list of all job seekers.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of job seekers",
                            content = @Content(mediaType = "application/json"))
            })
    @GetMapping
    public ResponseEntity<?> list(@Valid PageDtoIn pageDtoIn) {
        return ResponseController.responseEntity(() -> seekerService.list(pageDtoIn));
    }

    @Operation(summary = "Delete a job seeker",
            description = "Deletes a specific job seeker by their ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Job seeker successfully deleted",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Job seeker not found",
                            content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable BigInteger id) {
        seekerService.delete(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

}
