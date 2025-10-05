package theconnectedshop;
 
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
 
public abstract class BasePage {

    protected static WebDriver driver;
 
   @BeforeAll
public static void setup() {
    WebDriverManager.chromedriver().setup();
 
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless=new"); // важно для CI
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-gpu");
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--window-size=1920,1080");
    options.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());
 
    driver = new ChromeDriver(options);
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

 