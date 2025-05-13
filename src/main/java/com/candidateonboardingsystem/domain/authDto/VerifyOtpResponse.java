package com.candidateonboardingsystem.domain.authDto;

public record VerifyOtpResponse(
        boolean success,
        String token
) {
}