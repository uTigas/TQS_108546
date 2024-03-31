package tqs.hw1.fs_webApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.hw1.fs_webApp.data.entity.City;

@RestController
@RequestMapping("/api/v1")
public class TicketsController {

    @Autowired
    private TicketManagerService service;

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities(){
        return new ResponseEntity<>(service.getAllCities(),HttpStatus.OK);
    }
}
