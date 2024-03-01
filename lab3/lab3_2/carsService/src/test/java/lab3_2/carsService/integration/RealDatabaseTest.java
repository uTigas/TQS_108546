package lab3_2.carsService.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import lab3_2.carsService.Car;
import lab3_2.carsService.CarRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource( locations = "application-integrationtest.properties")
public class RealDatabaseTest{

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;
   
    @Test
    void testAPI(){
        createTestCar("vw", "golfao");
        createTestCar("papa", "reformas");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("vw", "papa");   
    
    }

        private void createTestCar(String maker, String model) {
            Car car = new Car(maker, model);
            repository.saveAndFlush(car);
        }
    

}