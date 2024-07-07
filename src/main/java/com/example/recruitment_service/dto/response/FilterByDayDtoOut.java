package com.example.recruitment_service.dto.response;

import com.example.recruitment_service.model.Element;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterByDayDtoOut implements Serializable {
    private Long numEmployer;
    private Long numJob;
    private Long numSeeker;
    private Long numResume;
    private List<Element> chart;
}
