package com.web.request_service.service;


import com.web.request_service.config.RabbitConfig;
import com.web.request_service.dto.OrderRequest;
import com.web.request_service.dto.OrderStage;
import org.apache.logging.log4j.util.Cast;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class MessageProducer {
    private final RabbitTemplate rabbitTemplate;
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendToStorage(OrderRequest order) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.ORDER_CREATE_QUEUE,
                order
        );
    }
    public void updateOrder(OrderRequest order) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.ORDER_UPDATE_QUEUE,
                order
        );
    }


    public OrderStage getOrderStage(String orderId) {
        // convertSendAndReceive блокирует вызов и ждёт ответа
        Object response = rabbitTemplate.convertSendAndReceive(
                RabbitConfig.ORDER_GET_QUEUE, // очередь запроса
                Long.parseLong(orderId)       // payload запроса
        );

        if (response instanceof OrderStage stageResponse) {
            return stageResponse;
        } else {
            throw new RuntimeException("Invalid response from storage");
        }
    }
}
