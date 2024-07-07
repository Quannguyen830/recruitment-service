package com.example.recruitment_service.dto.response;

import com.example.recruitment_service.model.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDtoOut implements Serializable {

    private BigInteger id;
    private BigInteger seekerId;
    private String seekerName;
    private String careerObj;
    private String title;
    private Integer salary;
    private HashMap<Integer, String> fields;
    private HashMap<Integer, String> provinces;

    public static ResumeDtoOut from(
            Resume resume, String seekerName, HashMap<Integer,
            String> fields, HashMap<Integer, String> provinces
    ) {
        return ResumeDtoOut.builder().id(resume.getId()).seekerId(resume.getSeeker_id())
                .seekerName(seekerName).careerObj(resume.getCareer_obj()).title(resume.getTitle())
                .salary(resume.getSalary()).fields(fields).provinces(provinces).build();
    }

}
