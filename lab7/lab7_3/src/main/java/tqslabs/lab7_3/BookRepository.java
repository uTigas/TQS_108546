package tqslabs.lab7_3;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAll();
    List<Book> findByTitle(String title);
}
