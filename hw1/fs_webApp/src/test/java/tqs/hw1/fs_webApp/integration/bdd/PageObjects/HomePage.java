package tqs.hw1.fs_webApp.integration.bdd.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL="https://localhost:8080/";

   //Locators
/*
    @FindBy(name = "toTerminal") 
    private WebElement destinationBox;

    @FindBy(name = "fromTerminal") 
    private WebElement departureBox;

    */
    //Apply Find Flights Btn
    @FindBy(how = How.LINK_TEXT, using = "Start looking for new Adventures")
    private WebElement listTerminalsbtn;

    //Constructor
    public HomePage(WebDriver driver){
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
        listTerminalsbtn.click();
    }

    public void resizeScreen(){
        driver.manage().window().setSize(new Dimension(1850, 1053));
    }

    public int countNumberTerminals(){
        List<WebElement> cards = driver.findElements(By.className(".card"));
        return cards.size();
    }
}