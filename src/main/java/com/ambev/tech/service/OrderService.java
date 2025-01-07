package com.ambev.tech.service;

import com.ambev.tech.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    void processOrder(OrderEntity order);
    List<OrderEntity> findByExternalId(String externalId);
}
