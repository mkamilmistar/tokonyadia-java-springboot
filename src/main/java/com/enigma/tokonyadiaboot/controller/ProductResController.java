package com.enigma.tokonyadiaboot.controller;

import com.enigma.tokonyadiaboot.entity.Product;
import com.enigma.tokonyadiaboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductResController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/{id}")
    public Product findProductById(@PathVariable(name = "id") String id){
        return productService.findProductById(id);
    }

    @GetMapping("/products")
    public Page<Product> getProducts(@RequestParam(name = "page") Integer page,
                                     @RequestParam(name = "size") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        return productService.getProducts(pageable);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProdcut(product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable(name = "id") String id){
        productService.deleteProductById(id);
    }
}
