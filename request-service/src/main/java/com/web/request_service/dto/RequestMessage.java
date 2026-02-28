package com.web.request_service.dto;


public class RequestMessage {


    /** Тип сообщения (для маршрутизации и логики) */
    private MessageType type;

    /** Основная полезная нагрузка */
    private String payload;



    public RequestMessage(MessageType type, String payload) {
        this.type = type;
        this.payload = payload;
    }


    public MessageType getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

}