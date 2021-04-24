package com.enigma.tokonyadiaboot.service;

import com.enigma.tokonyadiaboot.entity.Merchant;
import com.enigma.tokonyadiaboot.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MerchantServiceImpl implements MerchantService{

    private String notFoundMessage = "Merchant with id: %s Not Found";

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Merchant findMerchantById(String id) {
        validatePresent(id);
        return merchantRepository.findById(id).get();
    }

    @Override
    public Page<Merchant> getMerchants(Pageable pageable) {
        return merchantRepository.findAll(pageable);
    }

    @Override
    public Merchant createMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant updateMerchant(Merchant merchant) {
        validatePresent(merchant.getId());
        return merchantRepository.save(merchant);
    }

    @Override
    public void deleteMerchant(String id) {
        validatePresent(id);
        merchantRepository.deleteById(id);
    }

    private void validatePresent(String id) {
        if(! merchantRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(notFoundMessage, id));
        }
    }

}
