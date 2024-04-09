package tqs.hw1.fs_webApp.functional.bdd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BuyOneWayTripTest {

    @LocalServerPort
    int randomServerPort;

    private ChromeDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void buyOneWayTripTest() {
        driver.get("http://localhost:" + randomServerPort + "/");
        driver.manage().window().setSize(new Dimension(910, 1033));
        driver.findElement(By.id("departureDropdown")).click();
        driver.findElement(By.linkText("Zurich")).click();
        driver.findElement(By.id("arrivalDropdown")).click();
        driver.findElement(By.linkText("Vienna")).click();
        //Successfully found JS intersection
        driver.get("http://localhost:" + randomServerPort + "/results?origin=Zurich&destination=Vienna&fromDate=&toDate=&findReturn=false");
        driver.findElement(By.linkText("Buy Ticket")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Ricardo");
        driver.findElement(By.id("address")).sendKeys("Mendes Street");
        driver.findElement(By.id("city")).sendKeys("Porto");
        driver.findElement(By.id("state")).sendKeys("Cal II");
        driver.findElement(By.id("zipCode")).sendKeys("4444-111");
        driver.findElement(By.id("creditCardNumber")).sendKeys("213123123");
        driver.findElement(By.cssSelector(".btn")).click();
        driver.findElement(By.id("thanksMessage"));
    }
}
