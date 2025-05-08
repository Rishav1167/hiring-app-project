package com.candidateonboardingsystem.service;

import com.candidateonboardingsystem.config.RabbitMQMail;
import com.candidateonboardingsystem.utils.dtos.CandidateDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCandidate(CandidateDTO candidateDTO) {
        if ("offered".equalsIgnoreCase(candidateDTO.getStatus()) ||
                "rejected".equalsIgnoreCase(candidateDTO.getStatus())) {
            rabbitTemplate.convertAndSend(
                    RabbitMQMail.EXCHANGE,
                    RabbitMQMail.ROUTING_KEY,
                    candidateDTO
            );
        }
    }
}
