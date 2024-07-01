package com.example.recruitment_service.repository;

import com.example.recruitment_service.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, BigInteger> {
    @Query("SELECT r FROM Resume r JOIN Seeker s ON r.seeker_id = s.id ORDER BY r.title ASC, s.name ASC")
    Page<Resume> findAllResumeSorted(Pageable pageable);
}
