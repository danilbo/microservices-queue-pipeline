package com.web.storage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.storage.dto.RequestMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private final ObjectMapper objectMapper;

    public MessageConsumer() {
        this.objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules(); // поддержка Instant
    }

    @RabbitListener(queues = {"storage.queue", "analytics.queue"})
    public void receive(byte[] body) {
        try {
            RequestMessage message = objectMapper.readValue(body, RequestMessage.class);

            switch (message.getType()){
                case STORAGE -> System.out.println(message.getPayload() + " " + message.getType());
                case ANALYTICS -> System.out.println("Message type is  " + message.getType() + " its sectret for you!");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse message", e);
        }
    }
}