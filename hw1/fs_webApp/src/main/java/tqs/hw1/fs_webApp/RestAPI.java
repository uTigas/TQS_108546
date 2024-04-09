package tqs.hw1.fs_webApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tqs.hw1.fs_webApp.data.entity.Connection;
import tqs.hw1.fs_webApp.data.entity.Currency;
import tqs.hw1.fs_webApp.data.entity.Terminal;
import tqs.hw1.fs_webApp.service.ExchangeRateService;
import tqs.hw1.fs_webApp.service.TicketManagerService;
import tqs.hw1.fs_webApp.data.entity.Reservation;
import tqs.hw1.fs_webApp.support.PurchaseForm;

@RestController
@RequestMapping("/api/v1")
public class RestAPI {

    @Autowired
    private TicketManagerService service;

    @Autowired
    private ExchangeRateService exchange;

    @GetMapping("/origins")
    public ResponseEntity<List<String>> getOriginCities(){
        return new ResponseEntity<>(service.getOrigins(),HttpStatus.OK);
    }

    @GetMapping("/destinations")
    public ResponseEntity<List<String>> getDestinationCities(){
        return new ResponseEntity<>(service.getDestinations(),HttpStatus.OK);
    }
    
    @GetMapping("/terminals")
    public ResponseEntity<List<Terminal>> getTerminals(){
        return new ResponseEntity<>(service.getAllTerminals(),HttpStatus.OK);
    }

    @GetMapping("/connections")
    public ResponseEntity<List<Connection>> getConnections(@RequestParam String origin, @RequestParam String destination, @RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate){
        if (toDate == null && fromDate == null){
            return new ResponseEntity<>(service.getConnectionsFromTo(origin, destination),HttpStatus.OK);
        }
        else if(toDate != null && fromDate == null){
            return new ResponseEntity<>(service.getConnectionsFromToDateTo(origin, destination, toDate),HttpStatus.OK);
        }
        else if (toDate == null && fromDate != null){
            return new ResponseEntity<>(service.getConnectionsFromToDateFrom(origin, destination, fromDate),HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(service.getConnectionsFromToDateFromTo(origin, destination, fromDate, toDate),HttpStatus.OK);

    }

    @GetMapping("/tickets")
    public ResponseEntity<Reservation> getTicket(@RequestParam String token){
        Optional<Reservation> ticket = service.getTicket(token);
        if (ticket.isPresent())
            return new ResponseEntity<>(ticket.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/tickets")
    public ResponseEntity<Reservation> createTicket(@RequestBody PurchaseForm form){
        List<Integer> ids = new ArrayList<>();
        ids.add(form.getConnectionId());

        return new ResponseEntity<>(service.createTicket(ids), HttpStatus.OK);
    }

    @PostMapping("/currency")
    public ResponseEntity<Double> changeCurrency(@RequestBody String currency){
        Optional<Currency> curCurrency = service.getCurrency();
        if (curCurrency.isEmpty()){
            Currency cur = new Currency();
            cur.setCurrency(currency);
            service.changeCurrency(cur); //set default currency values associated to user
        }
        else {
            Currency cur = curCurrency.get();
            cur.setCurrency(currency);
            service.changeCurrency(cur); //changing currency values associated to user
        }
        return new ResponseEntity<>(exchange.getExchangeRate(currency), HttpStatus.OK);
    }
}
