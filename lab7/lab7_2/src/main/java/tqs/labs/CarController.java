package tqs.labs;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CarController {

    private CarManagerService carManagerService;

    public CarController(CarManagerService service){
        this.carManagerService = service;
    }

    @PostMapping("/cars" )
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        Car res = carManagerService.save(car);
        return new ResponseEntity<Car>(res, HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity<>(carManagerService.getAllCars(),HttpStatus.OK);
    }
}
