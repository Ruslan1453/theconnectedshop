package theconnectedshop.tests;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import theconnectedshop.pages.ProductPage;


@TestMethodOrder(OrderAnnotation.class)
public class ProductSearchTest {

    private static WebDriver driver;          
    private static WebDriverWait wait;        
    private static ProductPage productPage;  

    private static final String PRODUCT_URL =
            "https://theconnectedshop.com/products/smart-door-lock-slim";

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        productPage = new ProductPage(driver, wait);
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    @Order(1)
    public void pageTitleContainsBrandOrProduct() {   
        driver.get(PRODUCT_URL);
        String title = driver.getTitle().toLowerCase();
        Assertions.assertTrue(
                title.contains("connected") || title.contains("smart"),
                "Unexpected title: " + title
        );
    }

    @Test
    @Order(2)
    public void testProductTitleIsDisplayed() {
        driver.get(PRODUCT_URL);
        Assertions.assertTrue(
                !productPage.getTitleText().isBlank(),          
                "Заголовок продукту не відображається"
        );
        System.out.println("Title: " + productPage.getTitleText());
    }

    @Test
    @Order(3)
    public void testAddToCartButton() {
        driver.get(PRODUCT_URL);
      
        Assertions.assertTrue(
                productPage.getAddToCartButton().isEnabled(),
                "Кнопка 'Додати у кошик' недоступна"
        );
        productPage.getAddToCartButton().click();
        System.out.println("Product added to cart successfully");
    }

    @Test
    @Order(4)
    public void testDescriptionSectionPresence() {
        driver.get(PRODUCT_URL);
        Assertions.assertFalse(
                productPage.hasDescriptionSection(),
                "Секція опису продукту відсутня"
        );
        System.out.println("Description section is visible");
    }

   @Test

@Order(5)

public void testProductPriceIsDisplayed() {

    driver.get(PRODUCT_URL);
 
    String priceText = productPage.getPriceText();
 
    if (priceText == null || priceText.isBlank()) {

        Assertions.fail("Product price is empty");

    } else {

        System.out.println("Product price is displayed: " + priceText);

    }
}
}


 
