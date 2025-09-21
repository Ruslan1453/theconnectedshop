package theconnectedshop.pages;
 
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
 
public class Header {
    private WebDriver driver;
    private WebDriverWait wait;
 
 
 
    // Locators
    private By logoLink = By.cssSelector("a.header__heading-link");
    private By logoImage = By.cssSelector("a.header__heading-link img");

 
    public Header(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
 
  
 
    public WebElement getLogoLink() {
        return driver.findElement(logoLink);
    }
 
    public WebElement getLogoImage() {
        return driver.findElement(logoImage);
    }
 
    
}