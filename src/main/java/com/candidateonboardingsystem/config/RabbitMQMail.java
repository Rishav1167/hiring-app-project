package com.candidateonboardingsystem.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQMail {
    // for mailing
    public static final String QUEUE_NAME = "myQueue";
    public static final String EXCHANGE = "myExchange";
    public static final String ROUTING_KEY = "myRoutingKey";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false); // Non-durable queue
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY);
    }

   // for document
    public static final String DOCUMENT_QUEUE = "document.queue";
    public static final String DOCUMENT_EXCHANGE = "document.exchange";
    public static final String DOCUMENT_ROUTING_KEY = "document.routingKey";

    @Bean
    public Queue documentQueue() {
        return new Queue(DOCUMENT_QUEUE);
    }

    @Bean
    public TopicExchange documentExchange() {
        return new TopicExchange(DOCUMENT_EXCHANGE);
    }

    @Bean
    public Binding documentBinding(Queue documentQueue, TopicExchange documentExchange) {
        return BindingBuilder
                .bind(documentQueue)
                .to(documentExchange)
                .with(DOCUMENT_ROUTING_KEY);
    }

    // for deserialization  so rabbit mq can process it
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}


