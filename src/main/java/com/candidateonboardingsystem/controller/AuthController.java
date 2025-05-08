package com.candidateonboardingsystem.controller;


import com.candidateonboardingsystem.domain.authEntity.AuthRequest;
import com.candidateonboardingsystem.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        log.info("Generating token");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(),
                        authRequest.password())
        );
        return jwtUtil.generateToken(authRequest.username());
    }
}
