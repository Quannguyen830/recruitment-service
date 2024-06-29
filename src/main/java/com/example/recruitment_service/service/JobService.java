package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.JobDtoIn;
import com.example.recruitment_service.dto.dtoIn.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.UpdatedJobDtoIn;
import com.example.recruitment_service.dto.dtoOut.JobDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.model.Job;

import java.math.BigInteger;

public interface JobService {
    JobDtoOut createJob(JobDtoIn jobDtoIn);
    void updateJob(UpdatedJobDtoIn updatedJobDtoIn);
    JobDtoOut findJobById(BigInteger id);
    PageDtoOut<Job> findAllJobs(PageDtoIn pageDtoIn);
    void deleteJobById(BigInteger id);
}
