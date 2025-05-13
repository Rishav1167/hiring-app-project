package com.candidateonboardingsystem.service.producer;

import com.candidateonboardingsystem.config.RabbitMQMail;
import com.candidateonboardingsystem.domain.dtos.CandidateDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
