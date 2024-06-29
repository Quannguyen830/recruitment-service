package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.dto.dtoIn.JobDtoIn;
import com.example.recruitment_service.dto.dtoIn.PageDtoIn;
import com.example.recruitment_service.dto.dtoIn.UpdatedJobDtoIn;
import com.example.recruitment_service.dto.dtoOut.JobDtoOut;
import com.example.recruitment_service.dto.dtoOut.PageDtoOut;
import com.example.recruitment_service.common.errorCode.ErrorCode;
import com.example.recruitment_service.common.exception.ApiException;
import com.example.recruitment_service.model.Employer;
import com.example.recruitment_service.model.Job;
import com.example.recruitment_service.model.JobField;
import com.example.recruitment_service.model.JobProvince;
import com.example.recruitment_service.repository.EmployerRepository;
import com.example.recruitment_service.repository.JobFieldRepository;
import com.example.recruitment_service.repository.JobProvinceRepository;
import com.example.recruitment_service.repository.JobRepository;
import com.example.recruitment_service.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;
    private final JobProvinceRepository provinceRepository;
    private final JobFieldRepository fieldRepository;

    @Override
    public JobDtoOut createJob(JobDtoIn jobDtoIn) {
        Job job = jobDtoIn.from();
        job.setCreated_at(LocalDate.now());
        job.setUpdated_at(LocalDate.now());
        Optional<Employer> employer = employerRepository.findById(jobDtoIn.getEmployerId());

        if(employer.isEmpty()) {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Employer not found");
        }

        HashMap<Integer, String> provinces = getProvinces(job.getProvinces());
        HashMap<Integer, String> fields = getFields(job.getFields());
        jobRepository.save(job);
        return JobDtoOut.from(job, fields, provinces, employer.get().getName());
    }

    @Override
    public void updateJob(UpdatedJobDtoIn updatedJobDtoIn) {
        Job job = updatedJobDtoIn.from();
        job.setUpdated_at(LocalDate.now());
        jobRepository.save(job);
    }

    @Override
    public JobDtoOut findJobById(BigInteger id) {
        Optional<Job> jobFound = jobRepository.findById(id);

        if(jobFound.isEmpty()) {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Job not found");
        }

        Job job = jobFound.get();
        Optional<Employer> employerFound = employerRepository.findById(job.getEmployerId());

        if(employerFound.isEmpty()) {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Employer not found");
        }

        Employer employer = employerFound.get();

        HashMap<Integer, String> fields = getFields(job.getFields());
        HashMap<Integer, String> provinces = getProvinces(job.getProvinces());

        return JobDtoOut.from(job, fields, provinces, employer.getName());
    }

    @Override
    public PageDtoOut<Job> findAllJobs(PageDtoIn pageDtoIn) {
        Pageable pageable = PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize());
        Page<Job> page = jobRepository.findAllJobsSorted(pageable);
        return PageDtoOut.from(page.getTotalPages(), page.getSize(), page.getTotalElements(), page.getContent());
    }

    @Override
    public void deleteJobById(BigInteger id) {
        if(jobRepository.findById(id).isPresent()) {
            jobRepository.deleteById(id);
        } else {
            throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Job not found");
        }
    }

    private HashMap<Integer, String> getProvinces(String jobProvince) {
        HashMap<Integer, String> provinces = new HashMap<>();
        String[] provinceIds = jobProvince.split("-");
        for(int i=1; i<provinceIds.length; i++) {
            Optional<JobProvince> provinceFound = provinceRepository.findById(new BigInteger(provinceIds[i]));
            if(provinceFound.isPresent()) {
                provinces.put(i-1, provinceFound.get().getName());
            } else {
                throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Province not found");
            }
        }
        return provinces;
    }

    private HashMap<Integer, String> getFields(String jobField) {
        HashMap<Integer, String> fields = new HashMap<>();
        String[] fieldIds = jobField.split("-");
        for(int i=1; i<fieldIds.length; i++) {
            Optional<JobField> fieldFound = fieldRepository.findById(new BigInteger(fieldIds[i]));
            if(fieldFound.isPresent()) {
                fields.put(i-1, fieldFound.get().getName());
            } else {
                throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "Field not found");
            }
        }
        return fields;
    }
}
