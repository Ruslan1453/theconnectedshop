package theconnectedshop;
 
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
 
public abstract class BasePage {

    protected static WebDriver driver;
 
    @BeforeAll

    public static void setup() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();

    }
 
    @AfterAll

    public static void teardown() {

        if (driver != null) {

            driver.quit();

        }

    }

}

 