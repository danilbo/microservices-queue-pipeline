package com.web.request_service.controller;

import com.web.request_service.config.RabbitConfig;
import com.web.request_service.dto.OrderRequest;
import com.web.request_service.dto.OrderStage;
import com.web.request_service.service.MessageProducer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class MessageController {

    private final MessageProducer producer;
    private final RabbitTemplate rabbitTemplate;

    public MessageController(MessageProducer producer, RabbitTemplate rabbitTemplate) {
        this.producer = producer;
        this.rabbitTemplate = rabbitTemplate;

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

    @GetMapping("/get")
    public ResponseEntity<OrderStage> getOrderDetails(@RequestParam long id){
        // Отправляем запрос в очередь и ждём полный объект
        Object obj = rabbitTemplate.convertSendAndReceive(RabbitConfig.ORDER_GET_QUEUE, new OrderRequest(id, OrderStage.CREATED));
        if (obj == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        OrderRequest order;
        try {
            order = (OrderRequest) obj; // теперь десериализация в OrderRequest
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Берём только статус
        return ResponseEntity.ok(order.getStage());


    }
}


