package com.candidateonboardingsystem.service.producer;

import com.candidateonboardingsystem.config.RabbitMQMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocumentProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendCandidateId(Long candidateId) {
        log.info("Sending Candidate ID to RabbitMQ: {}", candidateId);
        rabbitTemplate.convertAndSend(
                RabbitMQMail.DOCUMENT_EXCHANGE,
                RabbitMQMail.DOCUMENT_ROUTING_KEY,
                candidateId
        );
    }
}
