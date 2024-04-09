package tqs.hw1.fs_webApp.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.repository.ConnectionRepository;

@Service
public class ConnectionService {

    @Autowired(required = true)
    ConnectionRepository repo;

    public List<String> findDistinctOriginCities(){
        return repo.findDistinctOriginCities();
    }

    public List<String> findDistinctDestinationCities(){
        return repo.findDistinctDestinationCities();
    }

    public List<Connection> findConnectionsFromTo(String origin, String destination){
        return repo.findConnectionsFromTo(origin, destination);
    }

    public List<Connection> findConnectionsFromToDateFrom(String origin, String destination, String dateFrom){

        LocalDate date = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Instant instant = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        long fromTimestampSeconds = instant.getEpochSecond();

        return repo.findConnectionsFromToDateFrom(origin, destination, fromTimestampSeconds);
    }
    
    public List<Connection> findConnectionsFromToDateTo(String origin, String destination, String dateTo){
        LocalDate date = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Instant instant = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        long toTimestampSeconds = instant.getEpochSecond();
        return repo.findConnectionsFromToDateTo(origin, destination, toTimestampSeconds);
    }
    
    public List<Connection> findConnectionsFromToDateFromTo(String origin, String destination, String dateFrom, String dateTo){
        LocalDate date = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Instant instant = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        long fromTimestampSeconds = instant.getEpochSecond();

        LocalDate date2 = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Instant instant2 = date2.atStartOfDay().toInstant(ZoneOffset.UTC);
        long toTimestampSeconds = instant2.getEpochSecond();

        return repo.findConnectionsFromToDateFromTo(origin, destination, fromTimestampSeconds, toTimestampSeconds);
    }

    public Optional<Connection> findById(int id){
        return repo.findById(id);
    }
    public List<Connection> findConnectionsTo(String destination){
        return repo.findConnectionsTo(destination);
    }
}
