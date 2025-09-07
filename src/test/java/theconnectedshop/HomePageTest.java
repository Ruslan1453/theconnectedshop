package theconnectedshop;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        Assertions.assertEquals(
            "The Connected Shop - Smart Locks, Smart Sensors, Smart Home & Office",
            driver.getTitle(),
            "Title should match"
        );
    }

    @Test
    @Order(2)
    public void verifyLogoLink() {
        driver.get("https://theconnectedshop.com/");

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
    }

    @Test
    @Order(3)
    public void verifySearchInputField() {
        driver.get("https://theconnectedshop.com/");

        WebElement searchInput = driver.findElement(By.cssSelector("input[name='q'"));
        Assertions.assertTrue(searchInput.isDisplayed(), "Search input should be visible");
        Assertions.assertEquals(
            "Search",
            searchInput.getAttribute("placeholder"),
            "Search placeholder should match 'Search'"
        );

        searchInput.clear();
        searchInput.sendKeys("lock");
        Assertions.assertEquals(
            "lock",
            searchInput.getAttribute("value"),
            "Search input should contain entered text"
        );
    }

    @Test
    @Order(4)
    public void verifySearchSuggestionAndClick() {
        driver.get("https://theconnectedshop.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        
        WebElement searchInput = driver.findElement(By.name("Search-In-Inline"));
        searchInput.clear();
        searchInput.sendKeys("lock");

       
        WebElement suggestion = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a.predictive-search__header")
            )
        );

        String suggestionText = suggestion.getText().trim();
        
        Assertions.assertTrue(
            suggestionText.toLowerCase().contains("lock"),
            "Suggestion should contain searched word 'lock', but was: " + suggestionText
        );

       
        suggestion.click();

       
        wait.until(ExpectedConditions.urlContains("search"));
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertTrue(
            currentUrl.toLowerCase().contains("lock"),
            "URL should contain the search query 'lock', but was: " + currentUrl
        );

        

 @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit(); {
        
    }
}
    }