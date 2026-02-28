package com.web.storage.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestMessage {

    private MessageType type;
    private String payload;

    @JsonCreator
    public RequestMessage(
            @JsonProperty("type") MessageType type,
            @JsonProperty("payload") String payload
    ) {
        this.type = type;
        this.payload = payload;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}