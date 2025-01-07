package com.ambev.tech.resource;

import com.ambev.tech.entity.OrderEntity;
import com.ambev.tech.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders/")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping("consult-order/{idPedido}")
    public List<OrderEntity> consultOrder(@PathVariable String idPedido) {
        return orderService.findByExternalId(idPedido);
    }
}
