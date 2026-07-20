package com.web.storage.controller;


import com.web.storage.dto.OrderRequest;
import com.web.storage.dto.OrderStage;
import com.web.storage.entity.OrderEntity;
import com.web.storage.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class StorageController {


    private final OrderService service;

    public StorageController(OrderService service) {
        this.service = service;
    }
    @GetMapping("/{id}")
    public OrderRequest get(@PathVariable long id) {
        OrderEntity orderEntity= service.get(id);
        return new OrderRequest(orderEntity.getOrderID(), orderEntity.getStage());
    }

}


