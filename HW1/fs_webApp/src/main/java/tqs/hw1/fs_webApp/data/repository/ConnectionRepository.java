package tqs.hw1.fs_webApp.data.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.fs_webApp.data.entity.City;
import tqs.hw1.fs_webApp.data.entity.Connection;

@Repository
public interface ConnectionRepository extends CrudRepository<Connection, Long>{
    List<Connection> findAll();
    List<Connection> findByDepartureGreaterThanEqual(LocalTime departure);
    List<Connection> findByArrivalLessThanEqual(LocalTime departure);
    List<Connection> findByOrigin(City origin);
    List<Connection> findByDestination(City destination);
}
