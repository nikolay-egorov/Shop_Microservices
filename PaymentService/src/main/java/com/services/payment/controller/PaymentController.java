package com.services.payment.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@RequestMapping
@ComponentScan(basePackages = "com.services")
public class PaymentController {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @PostMapping
    public Long payOrderById(@RequestBody Long orderId) {
        rabbitMQSender.send(orderId);
        return orderId;
    }

}
