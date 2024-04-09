package tqs.hw1.fs_webApp.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.fs_webApp.data.entity.Seat;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer>{
    List<Seat> findAll();
    
    @Query("SELECT s FROM Seat s WHERE s NOT IN (SELECT s2 FROM Seat s2 JOIN Stint st ON st.seat.id = s2.id AND s2.bus.id = s.bus.id WHERE st.con.id = :conId)")
    Page<Seat> findAvailableSeat(Pageable pageable, int conId);

}
