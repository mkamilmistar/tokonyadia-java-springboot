package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.constant.AppConfigConstant;
import com.enigma.tokonyadiaboot.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
public class WalletServiceImpl implements WalletService{

//    final String debitUrl = "http://localhost:8090/debit";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerService customerService;

    @Autowired
    AppConfigService appConfigService;

    @Override
    public void debitWallet(Customer customer, BigDecimal total){

        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromHttpUrl(appConfigService.getValue(AppConfigConstant.WALLET_ENDPOINT_URL))
                .queryParam("phoneNumber", customer.getPhoneNumber())
                .queryParam("amount", total);
        restTemplate.exchange(componentsBuilder.toUriString(), HttpMethod.POST, null, String.class);
    }
}
