package com.web.storage.service;

import com.web.storage.dto.OrderStage;
import com.web.storage.entity.OrderEntity;
import com.web.storage.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderEntity save(long id, OrderStage stage) {
        return repository.save(new OrderEntity(id, stage));
    }

    public OrderEntity get(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    @PostConstruct
    public void test() {
        System.out.println("GAGAGAGa");
        repository.findAll().forEach(o -> System.out.println(o.getOrderID() + " " + o.getStage()));
        System.out.println("GAGAGAGa");

    }
}