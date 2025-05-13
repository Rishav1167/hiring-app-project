package com.candidateonboardingsystem.service;

import com.candidateonboardingsystem.domain.authDto.OtpMessage;
import org.springframework.stereotype.Service;

@Service
public class OtpMessageService {
    private final EmailService emailService;

    public OtpMessageService(final EmailService emailService) {
        this.emailService = emailService;
    }

    public void sendOtpEmail(OtpMessage otpMessage) {
        emailService.sendOtpMail(otpMessage);
    }
}
