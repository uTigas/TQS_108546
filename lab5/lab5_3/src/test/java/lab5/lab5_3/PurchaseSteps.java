package lab5.lab5_3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PurchaseSteps {

    private WebDriver driver;

    @Given("we enter blazedemo main page")
    public void setup(){
        driver = new ChromeDriver();
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(1850, 1053));
  
    }

    @When("I enter the main Page and Choose to go from {string} to {string}")
    public void choosePath(String from, String to) {
        driver.findElement(By.name("fromPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("fromPort"));
            dropdown.findElement(By.xpath("//option[. = '" + from + "']")).click();
        }
        driver.findElement(By.name("toPort")).click();
        {
            WebElement dropdown = driver.findElement(By.name("toPort"));
            dropdown.findElement(By.xpath("//option[. = '" + to + "']")).click();
        }
        driver.findElement(By.cssSelector(".btn-primary")).click();

    }
    
    @When("I chose the first flight")
    public void chooseFirstFlight(){
        driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).click();
    }

    @When("fill in my personal data")
    public void fillData(){
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Tiago");
        driver.findElement(By.id("address")).sendKeys("Rua2");
        driver.findElement(By.id("city")).sendKeys("Aveiro");
        driver.findElement(By.id("zipCode")).sendKeys("1233-123");
        driver.findElement(By.id("creditCardNumber")).sendKeys("123123123");
        driver.findElement(By.id("nameOnCard")).sendKeys("Tiago Smth");
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I am presented with a page with the title {string}")
    public void finalMessage(String s){
        assertEquals(driver.getTitle(),s);
        driver.quit();
    }


}