package com.enigma.tokonyadiaboot.controller;

import com.enigma.tokonyadiaboot.entity.Merchant;
import com.enigma.tokonyadiaboot.entity.Product;
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

    @GetMapping("/merchant/{id}")
    public Merchant findMerchantById(@PathVariable(name = "id") String id){
        return merchantService.findMerchantById(id);
    }

    @GetMapping("/merchant/{id}/product")
    public List<Product> findProductsByMerchant(@PathVariable(name = "id") String id){
        return productService.findProductsByMerchant(id);
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
