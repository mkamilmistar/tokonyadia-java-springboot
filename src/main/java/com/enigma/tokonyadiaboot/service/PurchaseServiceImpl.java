package com.enigma.tokonyadiaboot.service;

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

    private String notFoundMessage = "Purchase with id: %s Not Found";

    @Autowired
    PurchaseRepository purchaseRepository;

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
        return purchaseRepository.save(purchase);
    }

    @Override
    public void deletePurchase(String id) {
        validatePresent(id);
        purchaseRepository.deleteById(id);
    }

    private void validatePresent(String id) {
        if(! purchaseRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(notFoundMessage, id));
        }
    }
}
