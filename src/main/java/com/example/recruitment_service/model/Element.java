package com.example.recruitment_service.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Element {
    private LocalDate date;
    private Long numEmployer;
    private Long numJob;
    private Long numSeeker;
    private Long numResume;
}
