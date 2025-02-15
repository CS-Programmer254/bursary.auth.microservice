package com.softideas.bursary.auth.microservice.application.services;

import com.softideas.bursary.auth.microservice.config.RabbitMQConfig;
import com.softideas.bursary.auth.microservice.contracts.EmailNotificationEvent;
import com.softideas.bursary.auth.microservice.contracts.SmsNotificationEvent;
import com.softideas.bursary.auth.microservice.contracts.UserCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public NotificationPublisher(RabbitTemplate rabbitTemplate) {

        this.rabbitTemplate = rabbitTemplate;
    }

    public <T> void publish(T message, String exchangeName, String routingKey) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

}
