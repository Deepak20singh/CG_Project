package com.flight.notification.emailsender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.notification.configuration.RabbitMQConfig;
import com.flight.notification.dto.EmailDTO;
import com.flight.notification.emailservice.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private final EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);

    public EmailSender(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.queue)
    public void receiveMessage(byte[] messageBytes) {
        try {
             ObjectMapper objectMapper = new ObjectMapper();
            EmailDTO emailDTO = objectMapper.readValue(messageBytes, EmailDTO.class);
            logger.info("Received email message: " + emailDTO);


            emailService.sendMail(emailDTO);
            logger.info("Email sent successfully to: " + emailDTO.getTo());
        } catch (Exception e) {
            logger.error("Error processing email message", e);
        }

    }
}
