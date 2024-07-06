package com.example.recruitment_service.dto.dtoIn.entity;

import com.example.recruitment_service.model.Job;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDtoIn {

    @NotNull
    private Long employerId;
    @NotEmpty
    private String title;
    @NotNull
    private Integer quantity;
    @NotEmpty
    private String description;
    @NotEmpty
    private String fieldIds;
    @NotEmpty
    private String provinceIds;
    @NotNull
    private Integer salary;
    @NotNull
    private LocalDate expiredAt;

    public static Job from(JobDtoIn jobDtoIn) {
        return Job.builder().employerId(jobDtoIn.employerId).title(jobDtoIn.title).quantity(jobDtoIn.quantity)
                .description(jobDtoIn.description).fields(jobDtoIn.fieldIds).provinces(jobDtoIn.provinceIds)
                .salary(jobDtoIn.salary).expired_at(jobDtoIn.expiredAt).created_at(LocalDate.now())
                .updated_at(LocalDate.now()).build();
    }

}
