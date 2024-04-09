package tqs.hw1.fs_webApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.entity.Currency;
import tqs.hw1.fs_webApp.data.entity.Reservation;
import tqs.hw1.fs_webApp.data.entity.Terminal;
import tqs.hw1.fs_webApp.service.ExchangeRateService;
import tqs.hw1.fs_webApp.service.TicketManagerService;
import tqs.hw1.fs_webApp.support.PurchaseForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class RestAPI {

    private static final Logger logger = LoggerFactory.getLogger(RestAPI.class);

    @Autowired
    private TicketManagerService service;

    @Autowired
    private ExchangeRateService exchange;

    @GetMapping("/origins")
    public ResponseEntity<List<String>> getOriginCities() {
        logger.info("[RestAPI] - Getting origin cities");
        return new ResponseEntity<>(service.getOrigins(), HttpStatus.OK);
    }

    @GetMapping("/destinations")
    public ResponseEntity<List<String>> getDestinationCities() {
        logger.info("[RestAPI] - Getting destination cities");
        return new ResponseEntity<>(service.getDestinations(), HttpStatus.OK);
    }

    @GetMapping("/terminals")
    public ResponseEntity<List<Terminal>> getTerminals() {
        logger.info("[RestAPI] - Getting terminals");
        return new ResponseEntity<>(service.getAllTerminals(), HttpStatus.OK);
    }

    @GetMapping("/connections")
    public ResponseEntity<List<Connection>> getConnections(@RequestParam String origin, @RequestParam String destination, @RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate) {
        logger.info("[RestAPI] - Getting connections from '{}' to '{}' with dates '{}' to '{}'", origin, destination, fromDate, toDate);
        List<Connection> connections;
        if (toDate == null && fromDate == null) {
            connections = service.getConnectionsFromTo(origin, destination);
        } else if (toDate != null && fromDate == null) {
            connections = service.getConnectionsFromToDateTo(origin, destination, toDate);
        } else if (toDate == null && fromDate != null) {
            connections = service.getConnectionsFromToDateFrom(origin, destination, fromDate);
        } else {
            connections = service.getConnectionsFromToDateFromTo(origin, destination, fromDate, toDate);
        }
        return new ResponseEntity<>(connections, HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public ResponseEntity<Reservation> getTicket(@RequestParam String token) {
        logger.info("[RestAPI] - Getting ticket with token '{}'", token);
        Optional<Reservation> ticket = service.getTicket(token);

        if (ticket.isPresent()) {
            logger.info("[RestAPI] - Ticket with token '{}' found", token);
            return new ResponseEntity<>(ticket.get(), HttpStatus.OK);
        } else {
            logger.info("[RestAPI] - Ticket with token '{}' not found", token);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/tickets")
    public ResponseEntity<Reservation> createTicket(@RequestBody PurchaseForm form) {
        logger.info("[RestAPI] - Creating ticket with purchase form {}", form);
        List<Integer> ids = new ArrayList<>();
        ids.add(form.getConnectionId());
        int returnId = form.getReturnId();
        if (returnId != -1) {
            ids.add(returnId);
        }

        Reservation createdTicket = service.createTicket(ids);

        if (createdTicket != null) {
            logger.info("[RestAPI] - Ticket created successfully");
            return new ResponseEntity<>(createdTicket, HttpStatus.OK);
        } else {
            logger.error("[RestAPI] - Failed to create ticket");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/currency")
    public ResponseEntity<Double> changeCurrency(@RequestBody String currency) {
        logger.info("[RestAPI] - Changing currency to '{}'", currency);
        Optional<Currency> curCurrency = service.getCurrency();
        if (curCurrency.isEmpty()) {
            Currency cur = new Currency();
            cur.setCurrency(currency);
            service.changeCurrency(cur); //set default currency values associated to user
        } else {
            Currency cur = curCurrency.get();
            cur.setCurrency(currency);
            service.changeCurrency(cur); //changing currency values associated to user
        }
        return new ResponseEntity<>(exchange.getExchangeRate(currency), HttpStatus.OK);
    }
}
