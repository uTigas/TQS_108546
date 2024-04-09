package tqs.hw1.fs_webApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.hw1.fs_webApp.data.entity.Currency;
import tqs.hw1.fs_webApp.data.repository.CurrencyRepository;

@Service
public class CurrencyService {
    
    @Autowired
    CurrencyRepository repo;

    public Optional<Currency> getCurrency(){
        return repo.findById(1);
    }
    public Currency changeCurrency(Currency cur){
        return repo.save(cur);
    }
    
}
