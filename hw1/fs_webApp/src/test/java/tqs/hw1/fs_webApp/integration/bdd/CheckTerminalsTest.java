package tqs.hw1.fs_webApp.integration.bdd;

import static io.github.bonigarcia.seljup.BrowserType.FIREFOX;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import tqs.hw1.fs_webApp.integration.bdd.PageObjects.HomePage;
import tqs.hw1.fs_webApp.integration.bdd.PageObjects.TerminalPage;


@ExtendWith(SeleniumJupiter.class)
public class CheckTerminalsTest {
    HomePage homePage;
    TerminalPage terminalPage;

    @Test
    void checkTerminals(@DockerBrowser(type = FIREFOX) WebDriver driver)  {
        homePage = new HomePage(driver);
            
        homePage.resizeScreen();
        homePage.clickFindAdventures();

        int numberConnections = homePage.countNumberTerminals();
        //Each card represents a Terminal
        assertTrue(numberConnections > 0);
    }
      
}
