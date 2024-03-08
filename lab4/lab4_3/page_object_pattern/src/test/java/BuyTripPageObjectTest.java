import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class BuyTripPageObjectTest {
    HomePage homePage;
    ReservePage reservePage;
    PurchasePage purchasePage;

    @Test
    void buyTrip(ChromeDriver driver) {
        homePage = new HomePage(driver);

        homePage.choseDeparture("Boston");
        homePage.choseDestination("London");
        homePage.clickOnFindFlights();

        reservePage = new ReservePage(driver);
        reservePage.choseFirstFlight();

        purchasePage = new PurchasePage(driver);
        purchasePage.enterFullName("Tiago Camelo");
        purchasePage.enterAddress("Rua");
        purchasePage.enterCity("Aveiro");
        purchasePage.enterZipCode("1231-123");
        purchasePage.enterCreditCardNumber("123123123");
        purchasePage.enterNameOnCard("Tiago Smth");

        purchasePage.clickPurchaseBtn();

        assertEquals(driver.getTitle(), "BlazeDemo Confirmation");

    }

}
