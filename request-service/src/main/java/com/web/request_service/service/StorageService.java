package com.web.request_service.service;

import com.web.request_service.client.OrderClient;
import com.web.request_service.dto.OrderStage;
import org.springframework.stereotype.Service;


@Service
public class StorageService {
    private final OrderClient client;

    public StorageService(OrderClient client) {
        this.client = client;
    }

    public OrderStage getOrderById(long id) {
        return client.getOrderById(id).getStage();
    }

}
