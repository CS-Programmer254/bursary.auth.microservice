package com.softideas.bursary.auth.microservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String USER_CREATED_EXCHANGE_NAME = "Sms.Notification.Exchange";

    public static final String USER_CREATED_ROUTING_KEY = "User.Created.Routing.Key";

    public static final String USER_CREATED_QUEUE = "User.Created.Event.Queue";

    @Bean
    public TopicExchange userCreatedExchange() {

        return new TopicExchange(USER_CREATED_EXCHANGE_NAME, true, false);

    }

    @Bean
    public Queue userCreatedQueue() {

        return new Queue(USER_CREATED_QUEUE, true, false, false);

    }

    @Bean
    public Binding userCreatedBinding(Queue userCreatedQueue, TopicExchange userCreatedExchange) {

        return BindingBuilder.bind(userCreatedQueue).to(userCreatedExchange).with(USER_CREATED_ROUTING_KEY);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {

        return new RabbitAdmin(connectionFactory);

    }
}