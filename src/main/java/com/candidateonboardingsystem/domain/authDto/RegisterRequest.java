package com.candidateonboardingsystem.domain.authDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Email must not be blank")
        @Email
        String email,

        @NotBlank(message = "Password must not be blank")
        @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
        String password,

        @NotBlank(message = "Role cannot be empty")
        String role
) {
}