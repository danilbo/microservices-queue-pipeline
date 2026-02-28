package com.web.request_service.controller;

import com.web.request_service.dto.RequestMessage;
import com.web.request_service.service.MessageProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageProducer producer;

    public MessageController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<Void> send(@RequestBody RequestMessage message) {
        producer.handleMessage(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Void> get(@RequestBody long id){
        return ResponseEntity.ok().build();
    }
}