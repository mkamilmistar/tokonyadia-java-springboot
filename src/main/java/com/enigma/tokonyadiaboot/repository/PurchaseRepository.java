package com.enigma.tokonyadiaboot.repository;

import com.enigma.tokonyadiaboot.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
}
