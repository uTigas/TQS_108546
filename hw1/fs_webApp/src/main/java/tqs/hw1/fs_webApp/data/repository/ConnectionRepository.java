package tqs.hw1.fs_webApp.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
    @Query("SELECT DISTINCT c.origin.city FROM Connection c")
    List<String> findDistinctOriginCities();
    @Query("SELECT DISTINCT c.destination.city FROM Connection c")
    List<String> findDistinctDestinationCities();
    @Query("SELECT c FROM Connection c WHERE c.origin.city = :origin AND c.destination.city = :destination")
    List<Connection> findConnectionsFromTo(String origin, String destination);
}
