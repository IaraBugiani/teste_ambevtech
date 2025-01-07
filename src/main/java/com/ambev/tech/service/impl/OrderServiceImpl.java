package com.ambev.tech.service.impl;

import com.ambev.tech.entity.OrderEntity;
import com.ambev.tech.repository.OrderRepository;
import com.ambev.tech.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @CacheEvict(value = "orders", key = "#order.id")
    public void processOrder(OrderEntity order) {
        if (orderRepository.existsByExternalId(order.getExternalId())) {
            throw new IllegalArgumentException("Duplicate order");
        }
        order.setTotalAmount(order.getItems().stream()
                .map(item -> item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        orderRepository.save(order);
    }

    @Override
    @Cacheable(value = "orders", key = "#order.id")
    public List<OrderEntity> findByExternalId(String externalId) {
        return orderRepository.findByExternalId(externalId);
    }
}
