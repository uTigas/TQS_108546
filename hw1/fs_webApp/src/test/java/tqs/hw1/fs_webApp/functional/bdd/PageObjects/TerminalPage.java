package tqs.hw1.fs_webApp.functional.bdd.PageObjects;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TerminalPage {
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="http://localhost:";

   //Locators
/*
    @FindBy(name = "toTerminal") 
    private WebElement destinationBox;

    @FindBy(name = "fromTerminal") 
    private WebElement departureBox;

    //Constructor
    public TerminalPage(WebDriver driver){
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
    
    public TerminalPage(WebDriver driver, int port) {
        this.driver = driver;
        PAGE_URL += port + "/terminals";
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public int countNumberTerminals(){
        // Create a WebDriverWait instance with a timeout of 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Wait for the presence of elements with the specified class name
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("card-body")));

        List<WebElement> cards = this.driver.findElements(By.className("card-body"));
        return cards.size();
    }
}