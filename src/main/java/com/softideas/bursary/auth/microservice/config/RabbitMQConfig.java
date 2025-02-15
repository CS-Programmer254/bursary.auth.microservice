package com.softideas.bursary.auth.microservice.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "notification.exchange";
    public static final String EMAIL_QUEUE = "email.queue";
    public static final String SMS_QUEUE = "sms.queue";
    public static final String EMAIL_ROUTING_KEY = "notification.email";
    public static final String SMS_ROUTING_KEY = "notification.sms";

    @Bean
    public TopicExchange topicExchange() {

        return new TopicExchange(EXCHANGE_NAME);

    }

    @Bean
    public Queue emailQueue() {

        return new Queue(EMAIL_QUEUE, false);

    }

    @Bean
    public Queue smsQueue() {

        return new Queue(SMS_QUEUE, false);

    }

    @Bean
    public Binding emailBinding(Queue emailQueue, TopicExchange topicExchange) {

        return BindingBuilder.bind(emailQueue).to(topicExchange).with(EMAIL_ROUTING_KEY);

    }

    @Bean
    public Binding smsBinding(Queue smsQueue, TopicExchange topicExchange) {

        return BindingBuilder.bind(smsQueue).to(topicExchange).with(SMS_ROUTING_KEY);

    }
}
