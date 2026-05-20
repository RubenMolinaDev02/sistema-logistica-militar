package com.example.location_microservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String USER_EXCHANGE = "user.exchange";
    public static final String USER_QUEUE = "user.queue";
    public static final String USER_ROUTING_KEY = "user.created";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(USER_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue(USER_QUEUE, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(USER_ROUTING_KEY);
    }
}