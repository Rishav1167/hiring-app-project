package com.candidateonboardingsystem.controller;


import com.candidateonboardingsystem.domain.authDto.*;
import com.candidateonboardingsystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid final AuthRequest authRequest) {
        AuthResponse authResponse = authService.login(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid final RegisterRequest registerRequest) {
        RegisterResponse registerResponse = authService.register(registerRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<VerifyOtpResponse> verifyOtp(@RequestBody @Valid final VerifyOtpRequest verifyOtpRequest) {
        VerifyOtpResponse verifyOtpResponse = authService.verifyOtp(verifyOtpRequest);
        return new ResponseEntity<>(verifyOtpResponse, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody @Valid final ForgotPasswordRequest forgotPasswordRequest) {
        return new ResponseEntity<>(authService.forgetPassword(forgotPasswordRequest), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Boolean> resetPassword(@RequestBody @Valid final ResetPasswordRequest resetPasswordRequest) {
        return new ResponseEntity<>(authService.resetPassword(resetPasswordRequest), HttpStatus.OK);
    }
}
