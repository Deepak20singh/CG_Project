package com.flight.notification.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class RabbitMQConfig {
    public static final String queue = "email_queue";
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);

    @Bean
    public Queue emailQueue() {
        logger.info("Creating queue: {}", queue);  // Log for queue creation
        return new Queue(queue, true); // Durable queue
    }
}
