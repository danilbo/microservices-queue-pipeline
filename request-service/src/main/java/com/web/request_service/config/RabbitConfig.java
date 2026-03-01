package com.web.request_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.request_service.dto.OrderRequest;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.AmqpException;

@Configuration
public class RabbitConfig {

    public static final String ORDER_CREATE_QUEUE = "order.create.queue";
    public static final String ORDER_UPDATE_QUEUE = "order.update.queue";
    public static final String ORDER_GET_QUEUE = "order.get.queue";

    @Bean
    public Queue createOrderQueue() {
        return new Queue(ORDER_CREATE_QUEUE, true);
    }

    @Bean
    public Queue updateOrderQueue() {
        return new Queue(ORDER_UPDATE_QUEUE, true);
    }

    @Bean
    public Queue getOrderQueue() {
        return new Queue(ORDER_GET_QUEUE, true);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new MessageConverter() {
            private final ObjectMapper mapper = new ObjectMapper();

            @Override
            public Message toMessage(Object object, MessageProperties messageProperties) throws AmqpException {
                try {
                    messageProperties.setContentType("application/json");
                    return new Message(mapper.writeValueAsBytes(object), messageProperties);
                } catch (Exception e) {
                    throw new AmqpException("JSON serialization failed", e);
                }
            }

            @Override
            public Object fromMessage(Message message) throws AmqpException {
                try {
                    return mapper.readValue(message.getBody(), OrderRequest.class);
                } catch (Exception e) {
                    throw new AmqpException("JSON deserialization failed", e);
                }
            }
        };
    }
}