package tqslabs.lab7_4.integration;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import tqslabs.lab7_4.Car;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Lab74ApplicationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  @Container
  public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
    .withUsername("user")
    .withPassword("password")
    .withDatabaseName("test");

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
    registry.add("spring.datasource.password", container::getPassword);
    registry.add("spring.datasource.username", container::getUsername);
  }

  @Test
  @Order(1)
  void persistenceTest() {
    String uri = "http://localhost:" + port + "/api/cars";

    Car car = new Car();
    car.setMaker("Tesla");
    car.setModel("Model S");
    RestAssured
    .given()
      .contentType("application/json")
      .body(car)
    .when()
      .post(uri)
    .then()
      .statusCode(201)
      .body("maker", equalTo("Tesla"))
      .body("model", equalTo("Model S"));

    RestAssured
    .given()
    .when()
      .get(uri)
    .then()
      .statusCode(200)
      .body("size()",  is(11));
    
    
  }
  
  @Test
  @Order(2)
  void getAllCars() { //flyweight should insert 10 entries
    String uri = "http://localhost:" + port + "/api/cars";

    RestAssured
    .given()
    .when()
      .get(uri)
    .then()
      .statusCode(200)
      .body("size()",  is(11));
  }
}
