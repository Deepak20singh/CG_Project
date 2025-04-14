package com.flight.notification.controller;

import com.flight.notification.emailproducer.EmailProducer;
import com.flight.notification.dto.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailProducer producer;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailDTO emailDTO) {
        producer.sendToQueue(emailDTO);
        return "Email queued successfully";
    }
}
