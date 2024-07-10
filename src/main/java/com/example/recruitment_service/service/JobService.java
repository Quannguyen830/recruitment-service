package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.entity.JobDtoIn;
import com.example.recruitment_service.dto.dtoIn.entity.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.updateEntity.UpdatedJobDtoIn;
import com.example.recruitment_service.dto.dtoOut.JobDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;

import java.math.BigInteger;

public interface JobService {
    JobDtoOut createJob(JobDtoIn jobDtoIn);
    void updateJob(BigInteger id, UpdatedJobDtoIn updatedJobDtoIn);
    JobDtoOut findJobById(BigInteger id);
    PageDtoOut<JobDtoOut> findAllJobs(PageDtoIn pageDtoIn);
    void deleteJobById(BigInteger id);
}
