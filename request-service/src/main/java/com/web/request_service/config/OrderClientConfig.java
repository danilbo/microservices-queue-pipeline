package com.web.request_service.config;

import com.web.request_service.client.OrderClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(basePackages = "com.web.request_service.client",
types = {OrderClient.class})
public class OrderClientConfig {

}
