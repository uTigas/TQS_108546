package tqs.hw1.fs_webApp.functional.bdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Buy2WayTicketTest {
    
  @LocalServerPort
  int randomServerPort;
  
  private ChromeDriver driver;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
  }

  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void untitled() throws InterruptedException {
    driver.get("http://localhost:" + randomServerPort + "/");
    
    driver.manage().window().setSize(new Dimension(1850, 1053));
    driver.findElement(By.id("departureDropdown")).click();
    driver.findElement(By.linkText("London")).click();
    driver.findElement(By.id("arrivalDropdown")).click();
    driver.findElement(By.linkText("Paris")).click();
    driver.findElement(By.id("datepicker-input-from")).click();
    driver.findElement(By.cssSelector(".datepicker-days .datepicker-switch")).click();
    driver.findElement(By.cssSelector(".datepicker-months .prev")).click();
    driver.findElement(By.cssSelector(".datepicker-months .prev")).click();
    {
      WebElement element = driver.findElement(By.cssSelector(".datepicker-months .prev"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.cssSelector(".month:nth-child(1)")).click();
    driver.findElement(By.id("datepicker-input-to")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > .day:nth-child(2)")).click();
    driver.findElement(By.id("datepicker-input-from")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > .day:nth-child(7)")).click();
    driver.findElement(By.id("findReturn")).click();
    //Successfully arrived to JS intersection
    driver.get( "http://localhost:" + randomServerPort + "/" + "results?origin=London&destination=Paris&fromDate=&toDate=&findReturn=true");
    driver.findElement(By.linkText("Buy Ticket")).click();
    driver.findElement(By.linkText("Buy Ticket")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Tiago");
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("Fernado Street");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("Aveiro");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("Cal");
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("3333-111");
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("123123123");
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).click();
    {
      WebElement element = driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
  }
}
