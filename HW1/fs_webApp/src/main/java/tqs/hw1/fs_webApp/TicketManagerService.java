package tqs.hw1.fs_webApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.hw1.fs_webApp.data.entity.City;
import tqs.hw1.fs_webApp.data.repository.BusRepository;
import tqs.hw1.fs_webApp.data.repository.CityRepository;
import tqs.hw1.fs_webApp.data.repository.ConnectionRepository;

@Service
public class TicketManagerService {
   
    @Autowired(required = true)
    BusRepository busRepo;

    @Autowired(required = true)
    ConnectionRepository connectionRepo;

    @Autowired(required = true)
    CityRepository cityRepo;

    public List<City> getAllCities(){
        return cityRepo.findAll();
    }
    
}
