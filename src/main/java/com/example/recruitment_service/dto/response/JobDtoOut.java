package com.example.recruitment_service.dto.response;

import com.example.recruitment_service.model.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDtoOut implements Serializable {
    private BigInteger id;
    private String title;
    private Integer quantity;
    private String description;
    private Map<Integer, String> jobField;
    private Map<Integer, String> jobProvince;
    private Integer salary;
    private LocalDate expiredAt;
    private Long employerId;
    private String employerName;

    public static JobDtoOut from(
            Job job, HashMap<Integer, String> jobField, HashMap<Integer, String> jobProvince, String employerName
    ) {
        return JobDtoOut.builder().id(job.getId()).title(job.getTitle()).quantity(job.getQuantity())
                .description(job.getDescription()).jobField(jobField).jobProvince(jobProvince)
                .salary(job.getSalary()).expiredAt(job.getExpired_at()).employerId(job.getEmployerId())
                .employerName(employerName).build();
    }
}
