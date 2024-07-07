package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.request.entity.JobDtoIn;
import com.example.recruitment_service.dto.request.entity.PageDtoIn;
import com.example.recruitment_service.dto.request.updateEntity.UpdatedJobDtoIn;
import com.example.recruitment_service.dto.response.JobDtoOut;
import com.example.recruitment_service.dto.response.PageDtoOut;

import java.math.BigInteger;

public interface JobService {
    JobDtoOut createJob(JobDtoIn jobDtoIn);
    void updateJob(BigInteger id, UpdatedJobDtoIn updatedJobDtoIn);
    JobDtoOut findJobById(BigInteger id);
    PageDtoOut<JobDtoOut> findAllJobs(PageDtoIn pageDtoIn);
    void deleteJobById(BigInteger id);
}
