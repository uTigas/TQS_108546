package tqs.hw1.fs_webApp.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.fs_webApp.data.entity.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer>{
    List<Reservation> findAll();

    Optional<Reservation> findByToken(String token);
}
