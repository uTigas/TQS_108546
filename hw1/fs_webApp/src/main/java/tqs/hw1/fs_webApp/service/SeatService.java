package tqs.hw1.fs_webApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.entity.Seat;
import tqs.hw1.fs_webApp.data.repository.SeatRepository;

@Service
public class SeatService {
    
    @Autowired
    SeatRepository repo;

    public List<Seat> findAvailable(Connection con){
        return repo.findAvailableSeat(con);
    }
}
