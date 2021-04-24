package com.enigma.tokonyadiaboot.repository;

import com.enigma.tokonyadiaboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
