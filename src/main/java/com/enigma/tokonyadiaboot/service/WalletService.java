package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.entity.Customer;
import com.enigma.tokonyadiaboot.entity.Product;
import com.enigma.tokonyadiaboot.entity.Purchase;

import java.math.BigDecimal;

public interface WalletService {

    public void debitWallet(Purchase purchase, Product product);
}
