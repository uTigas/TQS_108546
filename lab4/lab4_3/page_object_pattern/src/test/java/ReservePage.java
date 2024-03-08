import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ReservePage {
   private WebDriver driver;

   //Page URL
   private static String PAGE_URL="https://blazedemo.com/reserve.php";

   //Apply Find Flights Btn
   @FindBy(css = "tr:nth-child(1) .btn")
   private WebElement chooseBtn;

   //Constructor
   public ReservePage(WebDriver driver){
       this.driver=driver;
       driver.get(PAGE_URL);
       //Initialise Elements
       PageFactory.initElements(driver, this);
   }

   public void choseFirstFlight(){
        this.chooseBtn.click();
   }
}