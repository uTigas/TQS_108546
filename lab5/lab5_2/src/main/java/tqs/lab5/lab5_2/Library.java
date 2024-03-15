package tqs.lab5.lab5_2;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Library {

    List<Book> books;
    List<Book> lastSearch;

    public Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> response(){
        return lastSearch;
    }

    public void booksByTitle(String title) {
        lastSearch = books.stream()
          .filter(book -> Objects.equals(title, book.getTitle()))
          .collect(Collectors.toList());
    }

    public void booksByAuthor(String author) {
        lastSearch = books.stream()
          .filter(book -> Objects.equals(author, book.getAuthor()))
          .collect(Collectors.toList());
    }

    public void booksByPublisher(String publisher) {
        lastSearch = books.stream()
          .filter(book -> Objects.equals(publisher, book.getPublisher()))
          .collect(Collectors.toList());
    }

    public void booksByTag(String tag) {
        lastSearch = books.stream()
          .filter(book -> book.getTags().contains(tag))
          .collect(Collectors.toList());
    }

    public void booksByYear(int year) {
        lastSearch = books.stream()
          .filter(book -> Objects.equals(year, book.getYear()))
          .collect(Collectors.toList());
    }

    public void booksBetweenYears(int year1, int year2) {
        lastSearch = books.stream()
          .filter(book -> book.getYear() >= year1 && book.getYear() <= year2)
          .collect(Collectors.toList());
    }
        
}