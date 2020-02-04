package com.services.accounting.controller;

import com.services.accounting.exception.NoEntityException;
import com.services.accounting.model.Order;
import com.services.accounting.model.OrderStatus;
import com.services.accounting.repo.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Component
@ComponentScan(basePackages = "com.services.accounting")
public class RabbitMQReceiver {

    private final RabbitTemplate amqpTemplateReceiver;

    @Value("${rabbit.rabbitmq.queueAccount}")
    private String queue;

    @Value("${rabbit.rabbitmq.routingKeyAccount}")
    private String routingkey;

    @Autowired
    private OrderRepository orderRepository;

    public RabbitMQReceiver(RabbitTemplate amqpTemplateReceiver ) {
        this.amqpTemplateReceiver = amqpTemplateReceiver;
    }

    @RabbitListener(queues = "${rabbit.rabbitmq.queueAccount}")
    public void receivePaidOrder(Long orderId) throws NoEntityException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoEntityException(orderId));
        order.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(order);
        System.out.println("Received paid order= " + order);

    }


}