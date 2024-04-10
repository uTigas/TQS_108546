import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

public class toDoTest {
    private String uri = "https://jsonplaceholder.typicode.com/";
    @Test 
    public void testAvailability(){
        
        when().
            get(uri + "todos").
        then().
            statusCode(200);

    }

    @Test 
    public void testId4(){
        
        when().
            get(uri + "todos/4").
        then().
            statusCode(200).
            body("title",equalTo("et porro tempora"));

    }

    @Test 
    public void testListing(){
        
        when().
            get(uri + "todos").
        then().
            statusCode(200).
            body("id",hasItems(198,199));
    }

    @Test 
    public void testLatency(){
        
        when().
            get(uri + "todos").
        then().
            statusCode(200).
            time(lessThan(2000l));
    }
}
