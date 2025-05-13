package com.candidateonboardingsystem.domain.authDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank(message = "Username must not be blank")
        @Email
        String email,
        @NotBlank(message = "Password must not be blank")
        @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
        String password
) {
}