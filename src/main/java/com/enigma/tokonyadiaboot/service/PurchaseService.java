package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    public Purchase getPurchaseById(String id);
    public Page<Purchase> getPurchases(Pageable pageable);
    public Purchase createPurchase(Purchase purchase);
    public void deletePurchase(String id);
}
