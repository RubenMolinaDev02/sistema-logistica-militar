package com.example.user_microservice.service;

import com.example.user_microservice.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserEventProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendUserCreatedEvent(Map<String, Object> event) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.USER_EXCHANGE,
                RabbitConfig.USER_ROUTING_KEY,
                event
        );
    }
}