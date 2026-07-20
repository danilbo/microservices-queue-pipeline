package com.web.request_service.client;


import com.web.request_service.dto.OrderRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "http://localhost:8081/order")
public interface OrderClient {

    @GetExchange("/{id}")
    OrderRequest getOrderById(@PathVariable("id") Long id);
}
