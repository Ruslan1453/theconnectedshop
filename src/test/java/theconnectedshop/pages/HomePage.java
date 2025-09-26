package theconnectedshop.pages;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class HomePage {

    private final WebDriver driver;

 
    private final String url = "https://theconnectedshop.com/";
 
    
 
    public HomePage(WebDriver driver) {

        this.driver = driver;

        

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

    public void setWait(WebDriverWait wait) {
    }
    
        
 
    

}


 