package com.candidateonboardingsystem.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO implements Serializable {
    private long id;
    private String fullName;
    private String email;
    private String phone;
    private String status;

    private LocalDateTime sentAt;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}

