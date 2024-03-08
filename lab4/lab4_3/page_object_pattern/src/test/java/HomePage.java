import org.objectweb.asm.Label;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
   private WebDriver driver;

   //Page URL
   private static String PAGE_URL="https://blazedemo.com/";

   //Locators

   @FindBy(name = "toPort") 
   private WebElement destinationBox;

   @FindBy(name = "fromPort") 
   private WebElement departureBox;

   //Apply Find Flights Btn
   @FindBy(how = How.LINK_TEXT, using = "Find Flights")
   private WebElement findFlightsButton;

   //Constructor
   public HomePage(WebDriver driver){
       this.driver=driver;
       driver.get(PAGE_URL);
       //Initialise Elements
       PageFactory.initElements(driver, this);
   }

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

   public void clickOnFindFlights(){
   }
}