package com.example.recruitment_service.service;

import com.example.recruitment_service.DtoIn.JobDtoIn;
import com.example.recruitment_service.DtoIn.PageDtoIn;
import com.example.recruitment_service.DtoIn.UpdatedJobDtoIn;
import com.example.recruitment_service.DtoOut.JobDtoOut;
import com.example.recruitment_service.DtoOut.PageDtoOut;
import com.example.recruitment_service.model.Job;
import com.example.recruitment_service.repository.JobRepository;

import java.math.BigInteger;

public interface JobService {
    JobDtoOut createJob(JobDtoIn jobDtoIn);
    void updateJob(UpdatedJobDtoIn updatedJobDtoIn);
    JobDtoOut findJobById(BigInteger id);
    PageDtoOut<Job> findAllJobs(PageDtoIn pageDtoIn);
    void deleteJobById(BigInteger id);
}
