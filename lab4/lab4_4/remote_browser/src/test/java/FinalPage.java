import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FinalPage {
   private WebDriver driver;

   //Page URL
   private static String PAGE_URL="https://blazedemo.com/confirmation.php";
   
   //Constructor
   public FinalPage(WebDriver driver){
       this.driver=driver;
       driver.get(PAGE_URL);
       //Initialise Elements
       PageFactory.initElements(driver, this);
   }

}