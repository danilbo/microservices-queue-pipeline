package com.web.request_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.request_service.dto.RequestMessage;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.AmqpException;

@Configuration
public class RabbitConfig {

    public static final String STORAGE_QUEUE = "storage.queue";
    public static final String ANALYTICS_QUEUE = "analytics.queue";

    @Bean
    public Queue storageQueue() {
        return new Queue(STORAGE_QUEUE, true);
    }

    @Bean
    public Queue analyticsQueue() {
        return new Queue(ANALYTICS_QUEUE, true);
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
                    return mapper.readValue(message.getBody(), RequestMessage.class);
                } catch (Exception e) {
                    throw new AmqpException("JSON deserialization failed", e);
                }
            }
        };
    }
}