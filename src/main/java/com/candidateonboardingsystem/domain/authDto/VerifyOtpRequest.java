package com.candidateonboardingsystem.domain.authDto;

public record VerifyOtpRequest(
        String email,
        String otp
) {
}