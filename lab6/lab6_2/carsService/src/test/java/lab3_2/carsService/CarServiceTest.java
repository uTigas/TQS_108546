package lab3_2.carsService;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CarServiceTest {
    @Mock
    CarRepository repository;

    @InjectMocks
    CarManagerService service = new CarManagerService();

    @Test
    void whenSaveCar_GetSavedCar(){
        Car car = new Car("Ferrari", "Golf");

        assertEquals(service.save(car),car);

    }

}
