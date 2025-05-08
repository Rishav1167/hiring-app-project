package com.candidateonboardingsystem.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String degree;
    private String university;
    private Integer passingYear;
    private Double percentage;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}