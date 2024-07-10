package com.example.recruitment_service.dto.dtoIn.filter;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterByDayDtoIn {

    @NotEmpty
    private String fromDate;

    @NotEmpty
    private String toDate;

}
