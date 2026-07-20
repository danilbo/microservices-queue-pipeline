package com.web.request_service.controller;

import com.web.request_service.config.RabbitConfig;
import com.web.request_service.dto.OrderRequest;
import com.web.request_service.dto.OrderStage;
import com.web.request_service.service.MessageProducer;
import com.web.request_service.service.StorageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class MessageController {

    private final MessageProducer producer;
    private final StorageService service;
    private final RabbitTemplate rabbitTemplate;

    public MessageController(MessageProducer producer, RabbitTemplate rabbitTemplate, StorageService service) {
        this.producer = producer;
        this.rabbitTemplate = rabbitTemplate;
        this.service = service;

    }

    @PostMapping("/create")
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest order) {
        producer.sendToStorage(order);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateOrder(@RequestBody OrderRequest order) {
        producer.updateOrder(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public OrderStage getOrderDetails(@PathVariable long id) {
        return service.getOrderById(id);
    }
}


