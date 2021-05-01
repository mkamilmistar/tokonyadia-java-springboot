package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.dto.PurchaseDto;
import com.enigma.tokonyadiaboot.dto.WalletDto;
import com.enigma.tokonyadiaboot.entity.Customer;
import com.enigma.tokonyadiaboot.entity.Product;
import com.enigma.tokonyadiaboot.entity.Purchase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService{

    final String debitUrl = "http://localhost:8090/debit";


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerService customerService;

    @Override
    public void debitWallet(Customer customer, BigDecimal total){

        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromHttpUrl(debitUrl)
                .queryParam("phoneNumber", customer.getPhoneNumber())
                .queryParam("amount", total);
        restTemplate.exchange(componentsBuilder.toUriString(), HttpMethod.POST, null, String.class);
    }


}
