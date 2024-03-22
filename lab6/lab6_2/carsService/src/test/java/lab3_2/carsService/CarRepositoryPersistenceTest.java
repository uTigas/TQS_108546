package lab3_2.carsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CarRepositoryPersistenceTest {

    @Autowired
    CarRepository repository ;

    @BeforeEach
    void setUp(){
        repository.deleteAll();
    }

    @Test 
    void whenSaved_CanQuery(){
        Car car = new Car("Ferrari", "Golf");
        assertThat(repository.findByCarId(Long.valueOf(1))).isEqualTo(null);
        
        car = repository.save(car);

        assertThat(repository.findByCarId(car.getCarId()).getMaker()).isEqualTo("Ferrari");

    }
}
