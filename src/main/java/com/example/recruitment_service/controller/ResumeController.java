package com.example.recruitment_service.controller;

import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.ResumeDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedResumeDtoIn;
import com.example.recruitment_service.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resumes")
@Tag(name = "Resume", description = "Manage Resume")
public class ResumeController {

    private final ResumeService resumeService;

    @Operation(summary = "Add a new resume",
            description = "Creates a new resume and stores it in the database.",
            requestBody = @RequestBody(description = "Details of the resume to be added",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ResumeDtoIn.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resume successfully created",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content)
            })
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody ResumeDtoIn resumeDtoIn) {
        return ResponseController.responseEntity(() -> resumeService.add(resumeDtoIn), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing resume",
            description = "Updates the details of an existing resume based on the given ID.",
            requestBody = @RequestBody(description = "Updated details of the resume",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UpdatedResumeDtoIn.class))),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Resume successfully updated",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resume not found",
                            content = @Content)
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable BigInteger id,
            @Valid @RequestBody UpdatedResumeDtoIn updatedResumeDtoIn
    ) {
        resumeService.update(id, updatedResumeDtoIn);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get resume by ID",
            description = "Retrieves the details of a specific resume by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resume details",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Resume not found",
                            content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable BigInteger id) {
        return ResponseController.responseEntity(() -> resumeService.get(id));
    }

    @Operation(summary = "List all resumes",
            description = "Retrieves a paginated list of all resumes.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of resumes",
                            content = @Content(mediaType = "application/json"))
            })
    @GetMapping
    public ResponseEntity<?> list(@Valid PageDtoIn pageDtoIn) {
        return ResponseController.responseEntity(() -> resumeService.list(pageDtoIn));
    }

    @Operation(summary = "Delete a resume",
            description = "Deletes a specific resume by its ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Resume successfully deleted",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resume not found",
                            content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable BigInteger id) {
        resumeService.delete(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }
}
