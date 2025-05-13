package com.candidateonboardingsystem.controller.consumer;

import com.candidateonboardingsystem.domain.authDto.OtpMessage;
import com.candidateonboardingsystem.service.OtpMessageService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OtpListener {
    private final OtpMessageService otpMessageService;

    public OtpListener(final OtpMessageService otpMessageService) {
        this.otpMessageService = otpMessageService;
    }

    @RabbitListener(queues = "hiringAuthOtpQueue")
    public void receive(final OtpMessage message) {
        if (message.routingKey().equals("auth.otp")){
            otpMessageService.sendOtpEmail(message);
        }
    }
}
