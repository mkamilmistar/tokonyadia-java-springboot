package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.entity.Purchase;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    public Purchase getPurchaseById(String id);
    public Page<Purchase> getPurchases(Pageable pageable);
    public Purchase createPurchase(Purchase purchase) throws JsonProcessingException;
    public void deletePurchase(String id);
}
