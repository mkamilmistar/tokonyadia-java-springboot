package com.enigma.tokonyadiaboot.controller;

import com.enigma.tokonyadiaboot.entity.Customer;
import com.enigma.tokonyadiaboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerResController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/{id}")
    public Customer findCustomerById(@PathVariable(name = "id") String id){
        return customerService.findCustomerById(id);
    }

    @GetMapping("/customers")
    public Page<Customer> searchCustomers(@RequestParam(name = "page") Integer page,
                                          @RequestParam(name = "size") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return customerService.searchCustomer(pageable);
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @PutMapping("/customer")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomerById(@PathVariable(name = "id") String id){
        customerService.deleteCustomer(id);
    }
}
