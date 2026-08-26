package com.nina.customerservice.customer_service;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final ObjectMapper objectMapper;

    private Map<Long, Customer> customerMap = Collections.emptyMap();

    public CustomerServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void loadCustomers() {
        try (InputStream inputStream = new ClassPathResource("customer.json").getInputStream()) {

            List<Customer> customers = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Customer>>() {}
            );

            this.customerMap = customers.stream()
                    .collect(Collectors.toMap(Customer::getId, customer -> customer));

            System.out.println("Loaded " + customerMap.size() + " customers from customer.json");

        } catch (IOException e) {
            throw new RuntimeException("Failed to load customer.json", e);
        }
    }

    @Override
    public Customer getCustomer(Long Id) {
        return customerMap.get(Id);
    }

    @Override
    public List<Customer> getCustomer() {
        return new ArrayList<>(customerMap.values());
    }
}
