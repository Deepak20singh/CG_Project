package com.flight.notification.emailproducer;



import com.flight.notification.configuration.RabbitMQConfig;
import com.flight.notification.dto.EmailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmailProducer {
    private final RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToQueue(EmailDTO emailDTO) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.queue, emailDTO);
    }
}
