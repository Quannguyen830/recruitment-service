package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.entity.JobDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedJobDtoIn;
import com.example.recruitment_service.service.impl.JobServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.math.BigInteger;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@Tag(name = "Job", description = "Manage Job")
public class JobController {

    private final JobServiceImpl jobService;

    @Operation(summary = "Create a new job",
            description = "Creates a new job listing and stores it in the database.",
            requestBody = @RequestBody(description = "Details of the job to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = JobDtoIn.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Job successfully created",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content)
            })
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody JobDtoIn jobDtoIn) {
        return ResponseController.responseEntity(() -> jobService.add(jobDtoIn), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing job",
            description = "Updates the details of an existing job based on the given ID.",
            requestBody = @RequestBody(description = "Updated details of the job",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UpdatedJobDtoIn.class))),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Job successfully updated",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Job not found",
                            content = @Content)
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Valid @RequestBody UpdatedJobDtoIn jobDtoIn,
            @PathVariable BigInteger id
    ) {
        jobService.update(id, jobDtoIn);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "List all jobs",
            description = "Retrieves a paginated list of all job listings.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of jobs",
                            content = @Content(mediaType = "application/json"))
            })
    @GetMapping
    public ResponseEntity<?> list(@Valid PageDtoIn pageDtoIn) {
        return ResponseController.responseEntity(() -> jobService.list(pageDtoIn));
    }

    @Operation(summary = "Get job by ID",
            description = "Retrieves the details of a specific job by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Job details",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Job not found",
                            content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable BigInteger id) {
        return ResponseController.responseEntity(() -> jobService.get(id));
    }

    @Operation(summary = "Delete a job",
            description = "Deletes a specific job by its ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Job successfully deleted",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Job not found",
                            content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable BigInteger id) {
        jobService.delete(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

}
