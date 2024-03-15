package lab5.lab5_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tqs.lab5.lab5_2.Book;
import tqs.lab5.lab5_2.Library;

public class LibrarySteps {
    private Library library;

    @DataTableType
    public Book bookEntry(Map<String,String> entry){
        return new Book(entry.get("title"), entry.get("author"), entry.get("publisher"), entry.get("tag"), Integer.parseInt(entry.get("year")));
    }
    @Given("the following books")
    public void setup(List<Book> books) {
        library = new Library(books);
    }

    @When("I search by author {string}")
    public void searchByAuthor(String author){
        library.booksByAuthor(author);

    }

    @When("I search by tag {string}")
    public void searchByTag(String tags){
        library.booksByTag(tags);
    }

    @When("I search books published between {int} and {int}")
    public void searchByInterval(int y1, int y2){
        library.booksBetweenYears(y1,y2);
    }

    @Then("I get {int} books")
    public void size_response(int expected){
        assertEquals(expected, library.response().size());
    }

    @Then("I get the Book with title {string}")
    public void I_get_the_Book_with_title(String s) {
        assertTrue(library.response().size()>0);
        assertEquals(s, library.response().get(0).getTitle());
    }


}