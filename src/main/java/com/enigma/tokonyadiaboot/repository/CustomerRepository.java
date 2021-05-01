package com.enigma.tokonyadiaboot.repository;

import com.enigma.tokonyadiaboot.entity.Customer;
import com.enigma.tokonyadiaboot.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
