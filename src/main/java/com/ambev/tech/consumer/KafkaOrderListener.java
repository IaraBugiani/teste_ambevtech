package com.ambev.tech.consumer;

import com.ambev.tech.entity.OrderEntity;
import com.ambev.tech.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaOrderListener {
    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "order-events", groupId = "order-service")
    public void listenOrderEvents(OrderEntity order) {
        orderService.processOrder(order);
    }

}
