package tqs.hw1.fs_webApp.functional.bdd.PageObjects;

import java.time.Duration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="http://localhost:";

   //Locators
/*
    @FindBy(name = "toTerminal") 
    private WebElement destinationBox;

    @FindBy(name = "fromTerminal") 
    private WebElement departureBox;

    */
    //Apply Find Flights Btn
    @FindBy(how = How.ID, using = "adventuresBtn")
    private WebElement listTerminalsbtn;

    //Constructor
    public HomePage(WebDriver driver, int port){
        HomePage.PAGE_URL += port + "/";
        this.driver=driver;
        this.driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }
    /* 
    public void clickChooseDeparture(){
     this.departureBox.click();
    }
    
    public void clickChooseDestination(){
     this.destinationBox.click();
    }
 
    public void choseDeparture(String dep){
     clickChooseDeparture();
     Select selectDropdown = new Select(this.departureBox);
     selectDropdown.selectByVisibleText(dep);
    }

    public void choseDestination(String dest){
     clickChooseDestination();
     Select selectDropdown = new Select(this.destinationBox);
     selectDropdown.selectByVisibleText(dest);
    }
    */
    public void clickFindAdventures(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(listTerminalsbtn));
        element.click();
    }

    public void resizeScreen(){
        driver.manage().window().setSize(new Dimension(1850, 1053));
    }

}