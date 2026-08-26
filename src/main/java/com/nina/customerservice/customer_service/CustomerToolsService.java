package com.nina.customerservice.customer_service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerToolsService {

    private CustomerService customerService;

    public CustomerToolsService(CustomerService customerService){
        this.customerService = customerService;
    }

    @Tool(description = "Get all the customers info")
    public List<Customer> getAllCustomer() {
        return customerService.getCustomer();
    }

    @Tool(description = "Get customer info using id")
    public Customer getCustomer(@ToolParam(description = "Customer ID")Long Id){
        return customerService.getCustomer(Id);
    }

}
