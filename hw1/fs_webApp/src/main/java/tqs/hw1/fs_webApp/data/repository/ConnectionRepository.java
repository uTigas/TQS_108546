package tqs.hw1.fs_webApp.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tqs.hw1.fs_webApp.data.entity.Terminal;
import tqs.hw1.fs_webApp.data.entity.Connection;

@Repository
public interface ConnectionRepository extends CrudRepository<Connection, Integer>{
    List<Connection> findAll();
    List<Connection> findByDepartureGreaterThanEqual(long departure);
    List<Connection> findByArrivalLessThanEqual(long departure);
    List<Connection> findByOrigin(Terminal origin);
    List<Connection> findByDestination(Terminal destination);
}
