package com.candidateonboardingsystem.domain.authDto;

public record OtpMessage(
        String exchange,
        String routingKey,
        String email,
        String otp
) {
}