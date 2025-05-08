package com.candidateonboardingsystem.domain.authEntity;

public record AuthRequest(
        String username,
        String password
) {
}