package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    public Customer findCustomerById(String id);
    public Page<Customer> searchCustomer(Pageable pageable);
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public void deleteCustomer(String id);
    public List<Customer> findAllCustomer();
}
