package tqs.hw1.fs_webApp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.java.AfterAll;

import tqs.hw1.fs_webApp.data.entity.Currency;
import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.entity.Bus;
import tqs.hw1.fs_webApp.data.entity.Reservation;
import tqs.hw1.fs_webApp.data.entity.Seat;
import tqs.hw1.fs_webApp.data.entity.Stint;
import tqs.hw1.fs_webApp.data.entity.Terminal;
import tqs.hw1.fs_webApp.data.repository.BusRepository;
import tqs.hw1.fs_webApp.data.repository.ConnectionRepository;
import tqs.hw1.fs_webApp.data.repository.CurrencyRepository;
import tqs.hw1.fs_webApp.data.repository.ReservationRepository;
import tqs.hw1.fs_webApp.data.repository.SeatRepository;
import tqs.hw1.fs_webApp.data.repository.StintRepository;
import tqs.hw1.fs_webApp.data.repository.TerminalRepository;

@SpringBootTest
public class PersistenceTest {

    @Autowired
    private BusRepository busRepo;
    
    @Autowired
    private CurrencyRepository currencyRepo;

    @Autowired
    private ConnectionRepository connectionRepo;

    @Autowired
    private SeatRepository seatRepo;

    @Autowired
    private StintRepository stintRepo;

    @Autowired
    private TerminalRepository terminalRepo;

    @Autowired
    private ReservationRepository reservationRepo;

    private Bus bus;
    private Connection connection;
    private Currency currency;
    private Reservation reservation;
    private Seat seat;
    private Stint stint;
    private Terminal terminal;
    
    @BeforeEach
    void setUp(){
        bus = new Bus("Test Bus");
        connection = new Connection();
        connection.setPrice(220.0);
        connection.setId(1);
        currency = new Currency();
        currency.setCurrency("EUR");
        currency.setId(1);
        reservation = new Reservation();
        reservation.setToken("Test Token");
        reservation.setId(1);
        seat = new Seat();
        seat.setSeatNumber(2);
        seat.setId(1);
        stint = new Stint();
        stint.setId(1);
        terminal = new Terminal();
        terminal.setCity("Test City");
        terminal.setName("Test Terminal");
    }

    @Test
    void busRepoPersists(){
        busRepo.save(bus);
        assertEquals(busRepo.findById("Test Bus").get().getId(), bus.getId());
    }
    
    @Test
    void currencyRepoPersists(){
        currencyRepo.save(currency);
        assertEquals(currencyRepo.findById(1).get().getId(), currency.getId());
    }

    @Test
    void reservationRepoPersists(){
        reservationRepo.save(reservation);
        assertEquals(reservationRepo.findById(1).get().getId(), reservation.getId());
    }

    @Test
    void connectionRepoPersists(){
        connectionRepo.save(connection);
        assertEquals(connectionRepo.findById(1).get().getId(), connection.getId());
    }

    @Test
    void stintRepoPersists(){
        stintRepo.save(stint);
        assertEquals(stintRepo.findById(1).get().getId(), stint.getId());
    }

    @Test
    void terminalRepoPersists(){
        terminalRepo.save(terminal);
        assertEquals(terminalRepo.findById("Test Terminal").get().getName(), terminal.getName());
    }

    @Test
    void seatRepoPersists(){
        seatRepo.save(seat);
        assertEquals(seatRepo.findById(1).get().getId(), seat.getId());
    }
    @AfterAll
    void undo(){
        busRepo.delete(bus);
        currencyRepo.delete(currency);
        terminalRepo.delete(terminal);
        connectionRepo.delete(connection);
        seatRepo.delete(seat);
        stintRepo.delete(stint);
        reservationRepo.delete(reservation);


    }
}
