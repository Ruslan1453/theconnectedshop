package theconnectedshop.tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit5.AllureJunit5;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("The Connected Shop UI Tests")
@Feature("Home Page and Search")
@ExtendWith(AllureJunit5.class)
public class HomePageAllureTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ZERO); // use explicit waits only
    }

    private WebDriverWait waitFor() {
        return new WebDriverWait(driver, Duration.ofSeconds(12));
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
            WebElement logoLink = waitFor().until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.header__heading-link"))
            );
            Assertions.assertTrue(logoLink.isDisplayed(), "Logo link should be visible");

            String hrefValue = logoLink.getAttribute("href");
            Assertions.assertTrue(hrefValue != null && hrefValue.endsWith("/"), "Logo link should point to home page");

            WebElement logoImage = logoLink.findElement(By.tagName("img"));
            waitFor().until(ExpectedConditions.visibilityOf(logoImage));
            Assertions.assertTrue(logoImage.isDisplayed(), "Logo image should be visible");

            Assertions.assertEquals(
                "The Connected Shop",
                String.valueOf(logoImage.getAttribute("alt")).trim(),
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

        WebElement searchInput = waitFor().until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='q']"))
        );
        Assertions.assertTrue(searchInput.isDisplayed(), "Search input should be visible");

        Allure.step("Проверяем placeholder", () -> {
            String placeholder = searchInput.getAttribute("placeholder");
            Assertions.assertTrue(
                placeholder != null && placeholder.toLowerCase().contains("search"),
                "Placeholder should contain 'Search', but was: " + placeholder
            );
        });

        searchInput.clear();
        searchInput.sendKeys("lock");
        Assertions.assertEquals(
            "lock",
            searchInput.getAttribute("value"),
            "Search input should contain entered text"
        );
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


