package com.candidateonboardingsystem.domain.entity;

import com.candidateonboardingsystem.domain.entity.Candidate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountHolderName;
    private String accountNumber;
    private String ifscCode;
    private String bankName;
    private String branchName;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
