package tqs.hw1.fs_webApp.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.entity.Seat;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer>{
    List<Seat> findAll();
    
    @Query("SELECT s FROM Seat s JOIN s.bus b JOIN Connection c ON c.bus = b WHERE c = :connection AND s NOT IN (SELECT st.seat FROM Stint st WHERE st.con = :connection)")
    List<Seat> findAvailableSeat(Connection connection);

}
