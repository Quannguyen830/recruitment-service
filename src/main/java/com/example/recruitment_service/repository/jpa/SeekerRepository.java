package com.example.recruitment_service.repository.jpa;

import com.example.recruitment_service.model.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeekerRepository extends JpaRepository<Seeker, BigInteger> {
    @Query("SELECT COUNT(s) FROM Seeker s WHERE s.created_at BETWEEN :startDate AND :endDate")
    Long countTotalSeekerByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(s) FROM Seeker s WHERE s.created_at = :date")
    Long countSeekerByDate(@Param("date") LocalDate date);

    @Query("SELECT s FROM Seeker s JOIN Resume r ON r.seeker_id = s.id WHERE r.salary <= :salary AND r.fields IN :fields AND r.provinces IN :provinces")
    List<Seeker> findMatchingSeekers(@Param("salary") Integer salary, @Param("fields") List<String> fields, @Param("provinces") List<String> provinces);
}
