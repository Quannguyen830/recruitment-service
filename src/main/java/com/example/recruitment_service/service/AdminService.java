package com.example.recruitment_service.service;

import com.example.recruitment_service.dto.dtoIn.filter.FilterByDayDtoIn;
import com.example.recruitment_service.dto.dtoOut.FilterByDayDtoOut;
import com.example.recruitment_service.dto.dtoOut.FilterByJobDtoOut;

import java.math.BigInteger;
import java.util.HashMap;

public interface AdminService {
    FilterByDayDtoOut filterByDay(FilterByDayDtoIn filterByDayDtoIn);
    FilterByJobDtoOut findMatchingJobAndSeeker(BigInteger id);
}
