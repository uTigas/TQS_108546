package tqs.hw1.fs_webApp.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.fs_webApp.data.entity.Seat;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer>{
    List<Seat> findAll();
}
