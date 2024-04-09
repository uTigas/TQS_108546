package tqs.hw1.fs_webApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.entity.Seat;
import tqs.hw1.fs_webApp.data.repository.SeatRepository;

@Service
public class SeatService {
    
    @Autowired
    SeatRepository repo;

    public Page<Seat> findAvailable(Connection con){
        return repo.findAvailableSeat(PageRequest.of(0, 1) ,con.getId());
    }
}
