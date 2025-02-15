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
    public void publish (UserCreatedEvent message,String exchangeName,String routingKey) {

        rabbitTemplate.convertAndSend(exchangeName, RabbitMQConfig.EMAIL_ROUTING_KEY, message);

    }


//    public void sendEmailNotification(EmailNotificationEvent message) {
//
//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.EMAIL_ROUTING_KEY, message);
//
//    }
//
//    public void sendSmsNotification(SmsNotificationEvent message) {
//
//        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.SMS_ROUTING_KEY, message);
//
//    }
}
