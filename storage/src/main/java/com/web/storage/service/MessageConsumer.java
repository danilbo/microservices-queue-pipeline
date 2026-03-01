package com.web.storage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.storage.config.RabbitConfig;
import com.web.storage.dto.OrderRequest;
import com.web.storage.dto.OrderStage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private final ObjectMapper objectMapper;

    public MessageConsumer() {
        this.objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules(); // поддержка Instant
    }

    @RabbitListener(queues = {"order.create.queue"})
    public void create(OrderRequest order) {
        // JSON уже десериализован в OrderRequest
        System.out.println("Need to create order: " + order);
        // Здесь можно вызывать сервис создания заказа
    }

    @RabbitListener(queues = {"order.update.queue"})
    public void update(OrderRequest order) {
        // JSON уже десериализован в OrderRequest
        System.out.println("Need to update order with id: " + order.getOrderID());
        // Здесь можно вызывать сервис обновления заказа
    }


    @RabbitListener(queues = RabbitConfig.ORDER_GET_QUEUE)
    public OrderRequest getOrderStage(OrderRequest request) {
        System.out.println("Get order: " + request.getOrderID());
        // Можно вернуть полный объект с заполненным stage
        request.setStage(OrderStage.CANCELED);
        return request;
    }

}
