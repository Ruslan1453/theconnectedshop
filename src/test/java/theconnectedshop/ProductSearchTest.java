package theconnectedshop.tests;

import theconnectedshop.pages.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

         
@TestMethodOrder(OrderAnnotation.class)
public class ProductSearchTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private ProductPage productPage;

    @BeforeAll
   public  void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
      
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        productPage = new ProductPage(driver, wait);
    }

    @AfterAll
   public  void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    @Order(1)
   public  void pageTitleContainsBrandOrProduct() {
        driver.get("https://theconnectedshop.ca/products/smart-door-lock-slim");
        String actualTitle = driver.getTitle();
        Assertions.assertTrue(
            actualTitle.toLowerCase().contains("connected") || actualTitle.toLowerCase().contains("smart"),
            "Expected the page title to contain 'connected' or 'smart', but was: " + actualTitle
        );
    }

    @Test
    @Order(2)
   public  void productTitleVisible() {
        driver.get("https://theconnectedshop.ca/products/smart-door-lock-slim");
        String titleText = productPage.getTitleText();          // есть в ProductPage
        Assertions.assertFalse(titleText.isBlank(), "Product title should not be blank");
    }

    @Test
    @Order(3)
   public  void productPriceVisibleAndNotEmpty() {
        driver.get("https://theconnectedshop.ca/products/smart-door-lock-slim");
        String price = productPage.getPriceText();             
        Assertions.assertFalse(price.isBlank(), "Product price should be visible and not empty");
    }

    @Test
    @Order(4)
  public  void addToCartButtonClickable() {
        driver.get("https://theconnectedshop.ca/products/smart-door-lock-slim");
        Assertions.assertTrue(productPage.getAddToCartButton().isDisplayed(), "Add to Cart button should be visible");
    }

    @Test
    @Order(5)
   public  void mainImageVisible() {
        driver.get("https://theconnectedshop.ca/products/smart-door-lock-slim");
        Assertions.assertTrue(productPage.getMainImage().isDisplayed(), "Main product image should be visible");
    }

    @Test
    @Order(6)   
   public  void hasDescriptionSection() {
        driver.get("https://theconnectedshop.ca/products/smart-door-lock-slim");
        Assertions.assertTrue(productPage.hasDescriptionSection(), "Description/tabs section should be present");
    }
}
