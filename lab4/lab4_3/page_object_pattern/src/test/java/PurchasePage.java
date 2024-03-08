import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PurchasePage {
   private WebDriver driver;

   //Page URL
   private static String PAGE_URL="https://blazedemo.com/purchase.php";

   @FindBy(name = "inputName") 
   private WebElement inputName;

   @FindBy(name = "address") 
   private WebElement address;

   @FindBy(name = "city") 
   private WebElement city;

   @FindBy(name = "zipCode") 
   private WebElement zipCode;

   @FindBy(name = "creditCardNumber") 
   private WebElement creditCardNumber;

   @FindBy(name = "nameOnCard") 
   private WebElement nameOnCard;

   @FindBy(css = ".btn-primary") 
   private WebElement purchaseBtn;

   //Constructor
   public PurchasePage(WebDriver driver){
       this.driver=driver;
       driver.get(PAGE_URL);
       //Initialise Elements
       PageFactory.initElements(driver, this);
   }

   public void enterFullName(String fname){
    inputName.sendKeys(fname);
   }

   public void enterAddress(String address){
    this.address.sendKeys(address);
   }

   public void enterCity(String city){
    this.city.sendKeys(city);
   }

   public void enterZipCode(String zip){
    this.zipCode.sendKeys(zip);
   }

   public void enterCreditCardNumber(String creditCardNumber){
    this.creditCardNumber.sendKeys(creditCardNumber);
   }

   public void enterNameOnCard(String name){
    this.nameOnCard.sendKeys(name);
   }

   public void clickPurchaseBtn(){
    this.purchaseBtn.click();
   }
}