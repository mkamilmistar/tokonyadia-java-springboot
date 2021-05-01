package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.dto.PurchaseDto;
import com.enigma.tokonyadiaboot.dto.WalletDto;
import com.enigma.tokonyadiaboot.entity.Customer;
import com.enigma.tokonyadiaboot.entity.Product;
import com.enigma.tokonyadiaboot.entity.Purchase;
import com.enigma.tokonyadiaboot.repository.PurchaseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    private final String walletUrl = "http://localhost:8090/wallet/{phoneNumber}";
    private final String message = "Balance not enough";
    private final String notFoundMessage = "Purchase with id: %s Not Found";
    private final String productNotEnoughMessage = "Product '%s' Not Enough";
    private final String outOfStockMessage = "Product '%s' Out Of Stock";

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ProductService productService;

    @Autowired
    WalletService walletService;

    @Autowired
    CustomerService customerService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate kafkaTemplate;


    @Override
    public Purchase getPurchaseById(String id) {
        validatePresent(id);
        return purchaseRepository.findById(id).get();
    }

    @Override
    public Page<Purchase> getPurchases(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    @Override
    public Purchase createPurchase(Purchase purchase) throws JsonProcessingException {
        purchase.setPurchaseDate(new Date());
        Product updateProduct = productService.findProductById(purchase.getProductId());
        validateStock(purchase, updateProduct);
        updateProduct.setStock(updateProduct.getStock() - purchase.getQuantity());

        //Debit
        Customer customer = customerService.findCustomerById(purchase.getCustomerId());
        BigDecimal price = new BigDecimal(purchase.getQuantity());
        BigDecimal total = price.multiply(updateProduct.getPrice());

        //validation
        validateBalance(customer, total);

        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setName(customer.getName());
        purchaseDto.setEmailTo(customer.getEmail());
        purchaseDto.setTotal(total);

        String jsonPurchase = objectMapper.writeValueAsString(purchaseDto);
        kafkaTemplate.send("tokonyadia-notification", jsonPurchase);

        walletService.debitWallet(customer, total);

        productService.updateProduct(updateProduct);
        return purchaseRepository.save(purchase);
    }

    @Override
    public void deletePurchase(String id) {
        validatePresent(id);
        purchaseRepository.deleteById(id);
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

    private void validateStock(Purchase purchase, Product updateProduct) {
        if( updateProduct.getStock() == 0){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    String.format(outOfStockMessage, updateProduct.getName()));
        }
        if( updateProduct.getStock() - purchase.getQuantity() < 0){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    String.format(productNotEnoughMessage, updateProduct.getName()));
        }
    }

    private void validatePresent(String id) {
        if(! purchaseRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(notFoundMessage, id));
        }
    }
}
