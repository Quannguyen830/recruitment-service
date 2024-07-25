package com.example.recruitment_service.controller;

import com.example.recruitment_service.dto.dtoIn.entity.EmployerDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedEmployerDtoIn;
import com.example.recruitment_service.common.controller.ResponseController;
import com.example.recruitment_service.dto.dtoOut.EmployerDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.service.impl.BaseRedisServiceImpl;
import com.example.recruitment_service.service.impl.EmployerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employers")
@RequiredArgsConstructor
@Tag(name = "Employer", description = "Manage Employer")
public class EmployerController {

    private final EmployerServiceImpl employerService;
    private final BaseRedisServiceImpl baseRedisService;

    @Operation(summary = "Add a new employer",
            description = "Creates a new employer and stores it in the database.",
            requestBody = @RequestBody(description = "Details of the employer to be added",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EmployerDtoIn.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Employer successfully created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmployerDtoOut.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content)
            })
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody EmployerDtoIn employer) {
        return ResponseController.responseEntity(() -> employerService.add(employer), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing employer",
            description = "Updates the details of an existing employer based on the given ID.",
            requestBody = @RequestBody(description = "Updated details of the employer",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UpdatedEmployerDtoIn.class))),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Employer successfully updated",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Employer not found",
                            content = @Content)
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody UpdatedEmployerDtoIn employer) {
        employerService.update(id, employer);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "List all employers",
            description = "Retrieves the details of all employers.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employer details",
                            content = @Content(schema = @Schema(implementation = PageDtoOut.class))),
            })
    @GetMapping
    public ResponseEntity<?> list(@Valid PageDtoIn pageDtoIn) {
        return ResponseController.responseEntity(() -> employerService.list(pageDtoIn));
    }

    @Operation(summary = "Get employer by ID",
            description = "Retrieves the details of a specific employer by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Employer details",
                            content = @Content(schema = @Schema(implementation = EmployerDtoOut.class))),
                    @ApiResponse(responseCode = "404", description = "Employer not found",
                            content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        return ResponseController.responseEntity(() -> employerService.get(id), HttpStatus.OK);
    }

    @Operation(summary = "Delete an employer",
            description = "Deletes a specific employer by their ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Employer successfully deleted",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Employer not found",
                            content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        employerService.delete(id);
        return ResponseController.responseEntity(() -> HttpStatus.NO_CONTENT);
    }

}

