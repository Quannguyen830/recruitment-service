package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.request.entity.PageDtoIn;
import com.example.recruitment_service.dto.request.entity.ResumeDtoIn;
import com.example.recruitment_service.dto.request.updateEntity.UpdatedResumeDtoIn;
import com.example.recruitment_service.dto.response.PageDtoOut;
import com.example.recruitment_service.dto.response.ResumeDtoOut;

import java.math.BigInteger;

public interface ResumeService {
    ResumeDtoOut createResume(ResumeDtoIn resumeDtoIn);
    void updateResume(BigInteger id, UpdatedResumeDtoIn updatedResumeDtoIn);
    ResumeDtoOut findResumeById(BigInteger id);
    PageDtoOut<ResumeDtoOut> findAllResume(PageDtoIn pageDtoIn);
    void deleteResume(BigInteger id);
}
