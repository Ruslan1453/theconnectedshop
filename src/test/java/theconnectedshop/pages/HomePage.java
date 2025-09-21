package theconnectedshop.pages;
 
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class HomePage {

    private WebDriver driver;

    private WebDriverWait wait;
 
    private String url = "https://theconnectedshop.com/";
 
    
 
    public HomePage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
 
    public void open() {

        driver.get(url);

    }
 
    public String getTitle() {

        return driver.getTitle();

    }
    public String getcurrentUrl() {
        return driver.getCurrentUrl();

    }
    
        
 
    

}


 