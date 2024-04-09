package tqs.hw1.fs_webApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.entity.Currency;
import tqs.hw1.fs_webApp.data.entity.Stint;
import tqs.hw1.fs_webApp.data.entity.Terminal;
import tqs.hw1.fs_webApp.data.entity.Reservation;
import tqs.hw1.fs_webApp.support.TokenGenerator;

@Service
public class TicketManagerService {

    @Autowired(required = true)
    BusService bus;

    @Autowired(required = true)
    ConnectionService connection;

    @Autowired(required = true)
    TerminalService terminal;

    @Autowired(required = true)
    ReservationService ticket;

    @Autowired(required = true)
    SeatService seats;

    @Autowired(required = true)
    StintsService stints;

    @Autowired(required = true)
    CurrencyService currency;

    public List<Terminal> getAllTerminals(){
        return terminal.getAllTerminals();
    }
    
    public List<String> getOrigins(){
        return connection.findDistinctOriginCities();
    }

    public List<String> getDestinations(){
        return connection.findDistinctDestinationCities();
    }

    public List<Connection> getConnectionsFromTo(String origin, String destination){
        return connection.findConnectionsFromTo(origin, destination);
    }
    public List<Connection> getConnectionsFromToDateFrom(String origin, String destination, String dateFrom){
        return connection.findConnectionsFromToDateFrom(origin, destination, dateFrom);
    }
    public List<Connection> getConnectionsFromToDateTo(String origin, String destination, String dateTo){
        return connection.findConnectionsFromToDateTo(origin, destination, dateTo);
    }
    public List<Connection> getConnectionsFromToDateFromTo(String origin, String destination, String dateFrom, String dateTo){
        return connection.findConnectionsFromToDateFromTo(origin, destination, dateFrom, dateTo);
    }
    public List<Connection> getConnectionsTo(String destination){
        return connection.findConnectionsTo(destination);
    }

    public Optional<Reservation> getTicket(String token){
        return ticket.findByToken(token);
    }

    public Reservation createTicket(List<Integer> connectionsId){
        Reservation newTicket = new Reservation();
        
        List<Connection> newConnections = new ArrayList<>(); 
        for (int id : connectionsId) {
            newConnections.add(connection.findById(id).get());
        }

        List<Stint> newStints = new ArrayList<>();

        for (Connection con : newConnections) {
            Stint newStint= new Stint();
            newStint.setCon(con);
            System.out.println("Available Seats:" + seats.findAvailable(con).size());
            newStint.setSeat(seats.findAvailable(con).get(0));

            newStints.add(newStint);
            stints.saveStint(newStint);
        }
        
        newTicket.setStints(newStints);
        newTicket.setTimestamp(System.currentTimeMillis());
        newTicket.setToken(TokenGenerator.generateToken());
        
        ticket.saveTicket(newTicket);
        return newTicket;
        
    }

    public Optional<Currency> getCurrency(){
        return currency.getCurrency();
    }
    
    public Currency changeCurrency(Currency cur){
        return currency.changeCurrency(cur);
    }
}
