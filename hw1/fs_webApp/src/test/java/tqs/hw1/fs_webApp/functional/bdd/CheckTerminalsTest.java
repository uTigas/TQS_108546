package tqs.hw1.fs_webApp.functional.bdd;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.github.bonigarcia.seljup.BrowserType.FIREFOX;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import tqs.hw1.fs_webApp.functional.bdd.PageObjects.HomePage;
import tqs.hw1.fs_webApp.functional.bdd.PageObjects.TerminalPage;

@ExtendWith(SeleniumJupiter.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CheckTerminalsTest {
    
    @LocalServerPort
    int randomServerPort;
    
    HomePage homePage;
    TerminalPage terminalPage;

    @Test
    void checkTerminals(@DockerBrowser(type = FIREFOX) WebDriver driver)  {
        homePage = new HomePage(driver, randomServerPort);
            
        homePage.resizeScreen();
        homePage.clickFindAdventures();

        TerminalPage terminalPage =new TerminalPage(driver, randomServerPort);
        int numberConnections = terminalPage.countNumberTerminals();
        //Each card represents a Terminal
        assertTrue(numberConnections > 0);
    }
      
}
