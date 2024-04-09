package tqs.hw1.fs_webApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.hw1.fs_webApp.data.entity.Stint;
import tqs.hw1.fs_webApp.data.repository.StintRepository;

@Service
public class StintsService {
    
    @Autowired
    StintRepository repo;

    public Optional<Stint> getStint(int id){
        return repo.findById(id);
    }

    public Stint saveStint(Stint st){
        return repo.save(st);
    }
    
}
