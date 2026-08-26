package com.nina.customerservice.customer_service;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerToolConfiguration {

    @Bean
    public ToolCallbackProvider getCustomerTools(CustomerToolsService customerToolsService) {
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(customerToolsService)
                .build();
    }
}
