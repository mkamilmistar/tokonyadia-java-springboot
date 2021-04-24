package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.entity.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MerchantService {
    public Merchant findMerchantById(String id);
    public Page<Merchant> getMerchants(Pageable pageable);
    public Merchant createMerchant(Merchant merchant);
    public Merchant updateMerchant(Merchant merchant);
    public void deleteMerchant(String id);
}
