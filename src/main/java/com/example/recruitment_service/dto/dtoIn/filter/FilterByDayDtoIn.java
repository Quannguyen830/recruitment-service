package com.example.recruitment_service.dto.dtoIn.filter;

import com.example.recruitment_service.common.annotation.ValidDate;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterByDayDtoIn {

    @NotEmpty
    private String fromDate;

    @NotEmpty
    private String toDate;

}
