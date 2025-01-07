package com.ambev.tech.repository;

import com.ambev.tech.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Boolean existsByExternalId(String externalId);

    List<OrderEntity> findByExternalId(String externalId);
}
