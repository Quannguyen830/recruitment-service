package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.ResumeDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedResumeDtoIn;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.dto.dtoOut.ResumeDtoOut;

import java.math.BigInteger;

public interface ResumeService {
    ResumeDtoOut add(ResumeDtoIn resumeDtoIn);
    void update(BigInteger id, UpdatedResumeDtoIn updatedResumeDtoIn);
    ResumeDtoOut get(BigInteger id);
    PageDtoOut<ResumeDtoOut> list(PageDtoIn pageDtoIn);
    void delete(BigInteger id);
}
