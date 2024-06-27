package com.example.recruitment_service.repository;

import com.example.recruitment_service.model.JobField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface JobFieldRepository extends JpaRepository<JobField, BigInteger> {

}
