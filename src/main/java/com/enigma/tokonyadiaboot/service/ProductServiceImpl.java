package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.entity.Merchant;
import com.enigma.tokonyadiaboot.entity.Product;
import com.enigma.tokonyadiaboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private String notFoundMessage = "Product with id: %s Not Found";

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MerchantService merchantService;

    @Override
    public Product findProductById(String id) {
        validatePresent(id);
        return productRepository.findById(id).get();
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product createProduct(Product product) {
        product.setCreatedDate(new Date());
        product.setUpdateDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        validatePresent(product.getId());
        product.setUpdateDate(new Date());
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(String id) {
        validatePresent(id);
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductsByMerchant(String merchantId) {
        Merchant merchant = merchantService.findMerchantById(merchantId);
        return merchant.getProducts();
    }

    private void validatePresent(String id) {
        if(! productRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(notFoundMessage, id));
        }
    }
}
