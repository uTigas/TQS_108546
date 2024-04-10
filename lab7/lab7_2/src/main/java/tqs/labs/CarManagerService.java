package tqs.labs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car save(Car car){
        return car;
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    } 

    public Optional<Car> getCarDetails(Long carId){
        return carRepository.findById(carId);
    }
    
}
