package com.nina.customerservice.customer_service;

import java.util.List;

public interface CustomerService {
    public Customer getCustomer(Long Id);
    public List<Customer> getCustomer();
}
