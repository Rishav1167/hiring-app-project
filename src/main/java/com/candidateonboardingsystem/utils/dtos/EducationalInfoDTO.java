package com.candidateonboardingsystem.utils.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EducationalInfoDTO {

    @NotBlank(message = "Degree is required")
    private String degree;

    @NotBlank(message = "University name is required")
    private String university;

    @NotNull(message = "Passing year is required")
    @Min(value = 1950, message = "Year must be after 1950")
    @Max(value = 2100, message = "Year must be before 2100")
    private Integer passingYear;

    @NotNull(message = "Percentage is required")
    @DecimalMin(value = "0.0", message = "Percentage must be >= 0")
    @DecimalMax(value = "100.0", message = "Percentage must be <= 100")
    private Double percentage;

    @NotNull(message = "Candidate ID is required")
    private Long candidateId;
}