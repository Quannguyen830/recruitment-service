package com.example.recruitment_service.repository;

import com.example.recruitment_service.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, BigInteger> {
    @Query("SELECT r FROM Resume r JOIN Seeker s ON r.seeker_id = s.id ORDER BY r.title ASC, s.name ASC")
    Page<Resume> findAllResumeSorted(Pageable pageable);

    @Query("SELECT COUNT(r) FROM Resume r WHERE r.created_at BETWEEN :startDate AND :endDate")
    Long countTotalResumesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(r) FROM Resume r WHERE r.created_at = :date")
    Long countResumeByDate(@Param("date") LocalDate date);
}
