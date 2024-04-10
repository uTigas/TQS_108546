package tqs.labs;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CarController.class)
public class RestAssuredControllerTest {

    @Autowired 
    private MockMvc mockMvc;

    @MockBean 
    private CarManagerService service; 
    
    private Car ferrari;
    private Car vw;
    private Car volvo;
    private List<Car> allCars;
    
    @BeforeEach
    void setUp() {
        ferrari = new Car("Ferrari", "458");
        vw = new Car("VW", "Golf");
        volvo = new Car("Volvo", "V40");
        allCars = Arrays.asList(ferrari, vw, volvo);

        when(service.save(Mockito.any())).thenReturn(ferrari);
        when(service.getAllCars()).thenReturn(allCars);
    }

    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        given()
            .mockMvc(mockMvc)
            .contentType("application/json")
            .body(ferrari)
        .when()
            .post("/api/cars")
        .then()
            .statusCode(201)
            .body("maker", equalTo("Ferrari"));
        
        verify(service, times(1)).save(Mockito.any());
    }

    @Test
    void whenGetAllCar_ThenGetAllCars() throws Exception {
        given()
            .mockMvc(mockMvc)
        .when()
            .get("/api/cars")
        .then()
            .statusCode(200)
            .body("size()", is(allCars.size()))
            .body("[0].maker", equalTo("Ferrari"))
            .body("[1].maker", equalTo("VW"))
            .body("[2].maker", equalTo("Volvo"));
        
        verify(service, times(1)).getAllCars();
    }
}
