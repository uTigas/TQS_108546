package tqs.hw1.fs_webApp.functional.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import tqs.hw1.fs_webApp.functional.bdd.PageObjects.HomePage;
import tqs.hw1.fs_webApp.functional.bdd.PageObjects.TerminalPage;


@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TerminalSteps {
    
    @LocalServerPort
    int randomServerPort;
    
    ChromeDriver driver;
    HomePage homePage;
    TerminalPage terminalPage;

    @Given("Im in the main page")
    public void setup() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver, randomServerPort);
    }

    @When("I click Look For Adventures")
    public void lookForAdventures(){
        homePage.resizeScreen();
        homePage.clickFindAdventures();
    }

    @Then("Im presented with multiple terminal options")
    public void Im_presented_with_multiple_terminal_options() {
        terminalPage = new TerminalPage(driver, randomServerPort);
        int numberTerminals = terminalPage.countNumberTerminals();
        Assertions.assertTrue(numberTerminals > 0);
        driver.quit();
    }


}
