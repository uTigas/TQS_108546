package tqslabs.lab7_3;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Lab73ApplicationTests {

  @Container
  public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
    .withUsername("user")
    .withPassword("password")
    .withDatabaseName("test");

  @Autowired
  private BookRepository bookRepository;

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", container::getJdbcUrl);
    registry.add("spring.datasource.password", container::getPassword);
    registry.add("spring.datasource.username", container::getUsername);
  }

  @Test
  @Order(1)
  void persistenceTest() {

    Book book = new Book();
    book.setTitle("Smurf Recipes");
    book.setAuthor("Gargamel");
    book.setPubYear(1800);
    bookRepository.save(book);

    assertTrue(bookRepository.findByTitle("Smurf Recipes").size() == 1);
  }
  
  @Test
  @Order(2)
  void getAllTest() { //flyweight should insert 3 entries
    assertTrue(bookRepository.findAll().size() == 4 );
  }
}
