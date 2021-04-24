package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.entity.Merchant;
import com.enigma.tokonyadiaboot.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public Product findProductById(String id);
    public Page<Product> getProducts(Pageable pageable);
    public Product createProduct(Product product);
    public Product updateProdcut(Product product);
    public void deleteProductById(String id);
    public List<Product> findAllByMerchant(String merchantId);
}
