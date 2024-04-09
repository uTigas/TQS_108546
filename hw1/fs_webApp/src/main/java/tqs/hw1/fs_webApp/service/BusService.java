package tqs.hw1.fs_webApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.hw1.fs_webApp.data.entity.Bus;
import tqs.hw1.fs_webApp.data.repository.BusRepository;

@Service
public class BusService {
    
    @Autowired
    BusRepository repo;

    public Optional<Bus> getBus(String id){
        return repo.findById(id);
    }
    
}
