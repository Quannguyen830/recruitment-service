package com.example.recruitment_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resume")
public class Resume {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "seeker_id")
    private BigInteger seeker_id;

    @Column(name = "career_obj")
    private String career_obj;

    @Column(name = "title")
    private String title;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "fields")
    private String fields;

    @Column(name = "provinces")
    private String provinces;

    @Column(name = "created_at")
    private LocalDate created_at;

    @Column(name = "updated_at")
    private LocalDate updated_at;

}
