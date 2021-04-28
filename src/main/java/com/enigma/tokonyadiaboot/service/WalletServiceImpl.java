package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.AppCofig;
import com.enigma.tokonyadiaboot.entity.Customer;
import com.enigma.tokonyadiaboot.entity.Product;
import com.enigma.tokonyadiaboot.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerService customerService;

    @Override
    public void debitWallet(Purchase purchase, Product product) {
        Customer customer = customerService.findCustomerById(purchase.getCustomerId());
        BigDecimal price = new BigDecimal(purchase.getQuantity());
        BigDecimal total = price.multiply(product.getPrice());
        System.out.println(total);

        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8090/debit")
                .queryParam("phoneNumber", customer.getPhoneNumber())
                .queryParam("amount", total);
        restTemplate.exchange(componentsBuilder.toUriString(), HttpMethod.POST, null, String.class);
    }
}
