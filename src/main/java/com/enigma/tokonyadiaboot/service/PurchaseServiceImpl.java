package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.entity.Product;
import com.enigma.tokonyadiaboot.entity.Purchase;
import com.enigma.tokonyadiaboot.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final String notFoundMessage = "Purchase with id: %s Not Found";
    private final String productNotEnoughMessage = "Product '%s' Not Enough";
    private final String outOfStockMessage = "Product '%s' Out Of Stock";

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ProductService productService;

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
    public Purchase createPurchase(Purchase purchase) {
        purchase.setPurchaseDate(new Date());
        Product updateProduct = productService.findProductById(purchase.getProductId());
        validateStock(purchase, updateProduct);
        updateProduct.setStock(updateProduct.getStock() - purchase.getQuantity());
        productService.updateProduct(updateProduct);
        return purchaseRepository.save(purchase);
    }

    @Override
    public void deletePurchase(String id) {
        validatePresent(id);
        purchaseRepository.deleteById(id);
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
