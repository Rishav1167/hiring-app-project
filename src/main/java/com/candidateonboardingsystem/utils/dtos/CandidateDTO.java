package com.candidateonboardingsystem.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO implements Serializable {
    private long id;
    private String fullName;
    private String email;
    private String phone;
    private String status;
}

