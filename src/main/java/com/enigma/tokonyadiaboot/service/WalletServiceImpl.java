package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.AppCofig;
import com.enigma.tokonyadiaboot.dto.WalletDto;
import com.enigma.tokonyadiaboot.entity.Customer;
import com.enigma.tokonyadiaboot.entity.Product;
import com.enigma.tokonyadiaboot.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class WalletServiceImpl implements WalletService{

    final String debitUrl = "http://localhost:8090/debit";
    final String walletUrl = "http://localhost:8090/wallet/{phoneNumber}";
    final String message = "Balance not enough";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerService customerService;


    @Override
    public void debitWallet(Purchase purchase, Product product) {

        Customer customer = customerService.findCustomerById(purchase.getCustomerId());

        //Debit
        BigDecimal price = new BigDecimal(purchase.getQuantity());
        BigDecimal total = price.multiply(product.getPrice());

        //validation
        validateBalance(customer, total);

        UriComponentsBuilder componentsBuilder = UriComponentsBuilder
                .fromHttpUrl(debitUrl)
                .queryParam("phoneNumber", customer.getPhoneNumber())
                .queryParam("amount", total);
        restTemplate.exchange(componentsBuilder.toUriString(), HttpMethod.POST, null, String.class);
    }

    private void validateBalance(Customer customer, BigDecimal total) {
        WalletDto responseWallet = restTemplate.getForObject(walletUrl, WalletDto.class, customer.getPhoneNumber());
        System.out.println("ini balance :" + responseWallet.getBalance());
        BigDecimal subtraction = responseWallet.getBalance().subtract(total);
        BigDecimal balance = responseWallet.getBalance();
        if(balance.equals(0) || balance.compareTo(subtraction) < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(message));
        }
    }
}
