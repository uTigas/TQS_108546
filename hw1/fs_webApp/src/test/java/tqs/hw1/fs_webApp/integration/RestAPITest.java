package tqs.hw1.fs_webApp.integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAPITest {

    @LocalServerPort
    int randomServerPort;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = randomServerPort;
    }

    @Test
    public void testGetOriginCities() {
        given()
            .when()
            .get("/api/v1/origins")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("", hasSize(greaterThan(0)));
    }

    @Test
    public void testGetDestinationCities() {
        given()
            .when()
            .get("/api/v1/destinations")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("", hasSize(greaterThan(0)));
    }

    @Test
    public void testGetTerminals() {
        given()
            .when()
            .get("/api/v1/terminals")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("", hasSize(greaterThan(0)));
    }

    @Test
    public void testGetConnections() {
        given()
            .param("origin", "London")
            .param("destination", "Paris")
            .when()
            .get("/api/v1/connections")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("", hasSize(greaterThan(1)));
    }

    @Test
    public void testGetTicket() {
        given()
            .param("token", "abc123")
            .when()
            .get("/api/v1/tickets")
            .then()
            .statusCode(200);
    }
    @Test
    public void testCreateTicketInvalidId() {
        given()
            .contentType(ContentType.JSON)
            .body("{ \"connectionId\": 123, \"returnId\": 456 }")
            .when()
            .post("/api/v1/tickets")
            .then()
            .statusCode(500)
            .contentType(ContentType.JSON);
    }

    @Test
    public void testChangeCurrency() {
        given()
            .contentType(ContentType.JSON)
            .body("USD")
            .when()
            .post("/api/v1/currency")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON);
    }
}
