package com.enigma.tokonyadiaboot.repository;

import com.enigma.tokonyadiaboot.entity.Customer;
import com.enigma.tokonyadiaboot.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    public List<Purchase> findPurchasesByCustomer_Id(String customerId);
}
