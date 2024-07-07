package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.request.filter.FilterByDayDtoIn;
import com.example.recruitment_service.dto.response.FilterByDayDtoOut;
import com.example.recruitment_service.dto.response.FilterByJobDtoOut;

import java.math.BigInteger;

public interface AdminService {
    FilterByDayDtoOut filterByDay(FilterByDayDtoIn filterByDayDtoIn);
    FilterByJobDtoOut findMatchingJobAndSeeker(BigInteger id);
}
