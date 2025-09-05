package theconnectedshop;
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest {
 
    static WebDriver driver;
 
    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }
   


 

 
    @Test

    @Order(1)

    public void openHomePage() {

        driver.get("https://theconnectedshop.com/");

        Assertions.assertEquals("The Connected Shop - Smart Locks, Smart Sensors, Smart Home & Office", driver.getTitle(), "Title should match");

    }
     @Test

    @Order(3)

    public void verifyLogoLink() {

        driver.get("https://theconnectedshop.com/");
 
        // Находим <a> с ссылкой на главную страницу

        WebElement logoLink = driver.findElement(By.cssSelector("a.header__heading-link"));
 
        // Проверяем, что элемент отображается

        Assertions.assertTrue(logoLink.isDisplayed(), "Logo link should be visible");
 
        // Проверяем атрибут href

        String hrefValue = logoLink.getAttribute("href");

        Assertions.assertTrue(hrefValue.endsWith("/"), "Logo link should point to home page");
 
        // Проверяем, что внутри <a> есть <img> с логотипом

        WebElement logoImage = logoLink.findElement(By.tagName("img"));

        Assertions.assertTrue(logoImage.isDisplayed(), "Logo image should be visible");

        Assertions.assertEquals("The Connected Shop", logoImage.getAttribute("alt"),

                "Logo alt text should match 'The Connected Shop'");

    }


        
 
@AfterAll

    public static void teardown() {

        if (driver != null) {

            driver.quit();

        }

    }

}
 