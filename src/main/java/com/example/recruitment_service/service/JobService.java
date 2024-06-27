package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.DtoIn.JobDtoIn;
import com.example.recruitment_service.dto.DtoIn.PageDtoIn;
import com.example.recruitment_service.dto.DtoIn.UpdatedJobDtoIn;
import com.example.recruitment_service.dto.DtoOut.JobDtoOut;
import com.example.recruitment_service.dto.DtoOut.PageDtoOut;
import com.example.recruitment_service.model.Job;

import java.math.BigInteger;

public interface JobService {
    JobDtoOut createJob(JobDtoIn jobDtoIn);
    void updateJob(UpdatedJobDtoIn updatedJobDtoIn);
    JobDtoOut findJobById(BigInteger id);
    PageDtoOut<Job> findAllJobs(PageDtoIn pageDtoIn);
    void deleteJobById(BigInteger id);
}
