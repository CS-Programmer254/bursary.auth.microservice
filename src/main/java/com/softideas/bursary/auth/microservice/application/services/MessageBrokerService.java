package com.softideas.bursary.auth.microservice.application.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageBrokerService {

    private final RabbitAdmin rabbitAdmin;

    private final RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(MessageBrokerService.class);

    @Autowired
    public MessageBrokerService(RabbitTemplate rabbitTemplate, RabbitAdmin rabbitAdmin) {

        this.rabbitTemplate = rabbitTemplate;

        this.rabbitAdmin = rabbitAdmin;

    }

    public <T> void publish(T message, String exchangeName, String routingKey,String queueName) {

        try {

            declareExchangeQueueAndBinding(rabbitAdmin, exchangeName, queueName, routingKey);

            rabbitTemplate.convertAndSend(exchangeName, routingKey, message);

            logger.info("Message sent to exchange '{}' with routing key '{}' and queue '{}': {}",exchangeName, routingKey, queueName, message);

        } catch (Exception e) {

            logger.error("Failed to send message to RabbitMQ: {}", e.getMessage());

        }

    }

    public void declareExchangeQueueAndBinding(RabbitAdmin rabbitAdmin, String exchangeName, String queueName, String routingKey) {

        TopicExchange exchange = new TopicExchange(exchangeName, true, false);

        rabbitAdmin.declareExchange(exchange);

        Queue queue = new Queue(queueName, true, false, false);

        rabbitAdmin.declareQueue(queue);

        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);

        rabbitAdmin.declareBinding(binding);

    }

}
