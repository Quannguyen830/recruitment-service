package com.example.recruitment_service.repository;

import com.example.recruitment_service.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, BigInteger> {

}
