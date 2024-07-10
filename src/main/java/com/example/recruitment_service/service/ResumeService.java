package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.ResumeDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedResumeDtoIn;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.dto.dtoOut.ResumeDtoOut;

import java.math.BigInteger;

public interface ResumeService {
    ResumeDtoOut createResume(ResumeDtoIn resumeDtoIn);
    void updateResume(BigInteger id, UpdatedResumeDtoIn updatedResumeDtoIn);
    ResumeDtoOut findResumeById(BigInteger id);
    PageDtoOut<ResumeDtoOut> findAllResume(PageDtoIn pageDtoIn);
    void deleteResume(BigInteger id);
}
