package com.candidateonboardingsystem.domain.authDto;

import java.io.Serializable;

public record AuthResponse(
        String message
) implements Serializable {
}