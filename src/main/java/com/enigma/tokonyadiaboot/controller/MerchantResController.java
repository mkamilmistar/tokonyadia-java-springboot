package com.enigma.tokonyadiaboot.controller;

import com.enigma.tokonyadiaboot.entity.Customer;
import com.enigma.tokonyadiaboot.entity.Merchant;
import com.enigma.tokonyadiaboot.entity.Product;
import com.enigma.tokonyadiaboot.entity.Purchase;
import com.enigma.tokonyadiaboot.projectioin.CustomerPurchasesProjection;
import com.enigma.tokonyadiaboot.service.CustomerService;
import com.enigma.tokonyadiaboot.service.MerchantService;
import com.enigma.tokonyadiaboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MerchantResController {

    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/merchant/{id}")
    public Merchant findMerchantById(@PathVariable(name = "id") String id){
        return merchantService.findMerchantById(id);
    }

    @GetMapping("/merchant/{id}/product")
    public List<Product> findProductsByMerchant(@PathVariable(name = "id") String id){
        return productService.findProductsByMerchant(id);
    }

    @GetMapping("/merchant/{id}/customer")
    public List<CustomerPurchasesProjection> findCustomerByMerchant(@PathVariable(name = "id") String merchantId){
        return merchantService.getAllCustomersByMerchantId(merchantId);
    }

    @GetMapping("/merchant/customers")
    public List<Customer> findCustomers(){
        return customerService.findAllCustomer();
    }

    @GetMapping("/merchant/{id}/customers")
    public List<Customer> findCustomersByMerchant(@PathVariable(name = "id") String merchantId){
        return null;
    }

    @GetMapping("/merchants")
    public Page<Merchant> getMerchants(@RequestParam(name = "page") Integer page,
                                       @RequestParam(name = "size") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return merchantService.getMerchants(pageable);
    }

    @PostMapping("/merchant")
    public Merchant createMerchant(@RequestBody Merchant merchant){
        return merchantService.createMerchant(merchant);
    }

    @PutMapping("/merchant")
    public Merchant updateMerchant(@RequestBody Merchant merchant){
        return merchantService.updateMerchant(merchant);
    }

    @DeleteMapping("/merchant/{id}")
    public void deleteMerchant(@PathVariable(name = "id") String id){
        merchantService.deleteMerchant(id);
    }
}
