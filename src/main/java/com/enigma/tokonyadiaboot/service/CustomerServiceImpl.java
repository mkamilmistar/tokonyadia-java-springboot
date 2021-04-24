package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.entity.Customer;
import com.enigma.tokonyadiaboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final String notFoundMessage = "Customer with id: %s Not Found";

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer findCustomerById(String id) {
        validatePresent(id);
        return customerRepository.findById(id).get();
    }

    @Override
    public Page<Customer> searchCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    @Override
    public Customer updateCustomer(Customer customer) {
        validatePresent(customer.getId());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String id) {
        validatePresent(id);
        customerRepository.deleteById(id);
    }

    private void validatePresent(String id) {
        if(! (customerRepository.findById(id).isPresent())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(notFoundMessage, id));
        }
    }

}
