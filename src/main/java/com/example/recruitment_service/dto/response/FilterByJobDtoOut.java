package com.example.recruitment_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilterByJobDtoOut implements Serializable {
    private BigInteger id;
    private String title;
    private Integer quantity;
    private Map<Integer, String> fields;
    private Map<Integer, String> provinces;
    private Integer salary;
    private LocalDate expiredAt;
    private Long employerId;
    private String employerName;
    private Map<BigInteger, String> seekers;
}
