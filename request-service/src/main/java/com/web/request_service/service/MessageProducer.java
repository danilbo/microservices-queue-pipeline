package com.web.request_service.service;



import com.web.request_service.config.RabbitConfig;
import com.web.request_service.dto.MessageType;
import com.web.request_service.dto.RequestMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void handleMessage(RequestMessage message){
        switch (message.getType()){
            case STORAGE -> sendToStorage(message.getPayload());
            case ANALYTICS -> sendToAnalytics(message.getPayload());
            case null, default -> System.out.println("We are not handling this case!");
        }
    }

    public void sendToStorage(String text) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.STORAGE_QUEUE,
                new RequestMessage(MessageType.STORAGE,text)
        );
    }

    public void sendToAnalytics(String text) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.ANALYTICS_QUEUE,
                new RequestMessage(MessageType.ANALYTICS,text)
        );
    }
}