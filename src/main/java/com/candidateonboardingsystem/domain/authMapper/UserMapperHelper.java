package com.candidateonboardingsystem.domain.authMapper;

import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperHelper {
    private final PasswordEncoder passwordEncoder;

    public UserMapperHelper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Named("encodePassword")
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}

