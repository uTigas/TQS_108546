package tqs.hw1.fs_webApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.hw1.fs_webApp.data.entity.Reservation;
import tqs.hw1.fs_webApp.data.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository repo;

    public List<Reservation> getAllTickets(){
        return repo.findAll();
    }

    public Reservation saveTicket(Reservation ticket){
        return repo.save(ticket);
    }

    public Optional<Reservation> findByToken(String token){
        return repo.findByToken(token);
    }

    public void deleteTicket(Reservation ticket){
        repo.delete(ticket);
    }
}
