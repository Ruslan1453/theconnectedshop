package theconnectedshop.tests;
 
import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("The Connected Shop UI Tests")
@Feature("Home Page and Search")
public class HomePageAllureTest  {
 
    static WebDriver driver;
 
    //@BeforeAll
   // public static void setup() {
  //      WebDriverManager.chromedriver().setup();
   //     driver = new ChromeDriver();
  //      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   //     driver.manage().window().maximize();
   // }
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
 
    @Test
    @Order(1)
    @Story("Open Home Page")
    @Description("Открываем главную страницу и проверяем title")
    @Severity(SeverityLevel.CRITICAL)
    public void openHomePage() {
        Allure.step("Открываем https://theconnectedshop.com/", () ->
            driver.get("https://theconnectedshop.com/")
        );
 
        Allure.step("Проверяем title", () ->
            Assertions.assertEquals(
                "The Connected Shop - Smart Locks, Smart Sensors, Smart Home & Office",
                driver.getTitle(),
                "Title should match"
            )
        );
    }
 
    @Test
    @Order(2)
    @Story("Header Logo")
    @Description("Проверяем, что логотип ведет на главную и alt совпадает")
    public void verifyLogoLink() {
        driver.get("https://theconnectedshop.com/");
 
        Allure.step("Находим ссылку-логотип", () -> {
            WebElement logoLink = driver.findElement(By.cssSelector("a.header__heading-link"));
            Assertions.assertTrue(logoLink.isDisplayed(), "Logo link should be visible");
 
            String hrefValue = logoLink.getAttribute("href");
            Assertions.assertTrue(hrefValue.endsWith("/"), "Logo link should point to home page");
 
            WebElement logoImage = logoLink.findElement(By.tagName("img"));
            Assertions.assertTrue(logoImage.isDisplayed(), "Logo image should be visible");
 
            Assertions.assertEquals(
                "The Connected Shop",
                logoImage.getAttribute("alt").trim(),
                "Logo alt text should match 'The Connected Shop'"
            );
        });
    }
 
    @Test
    @Order(3)
    @Story("Search Input")
    @Description("Проверка отображения и ввода текста в поле поиска")
    public void verifySearchInputField() {
        driver.get("https://theconnectedshop.com/");
 
        WebElement searchInput = driver.findElement(By.cssSelector("#Search-In-Inline"));
        Assertions.assertTrue(searchInput.isDisplayed(), "Search input should be visible");
 
        Allure.step("Проверяем placeholder", () ->
            Assertions.assertEquals("Search", searchInput.getAttribute("placeholder"))
        );
 
        searchInput.clear();
        searchInput.sendKeys("lock");
        Assertions.assertEquals("lock", searchInput.getAttribute("value"));
    }
 
  
 
    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}