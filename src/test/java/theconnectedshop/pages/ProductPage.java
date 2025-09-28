package theconnectedshop.pages;
 
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class ProductPage {
 
    private WebDriver driver;
    private WebDriverWait wait;
 
    // Локатори
    private By title = By.cssSelector("h1.product__title");
    private By price = By.cssSelector("span.product__price");
    private By addToCartButton = By.cssSelector("button.product__add-to-cart");
    private By mainImage = By.cssSelector("img.product__image");
    private By descriptionSection = By.cssSelector("div.product__description");
 
    public ProductPage(WebDriver driver, WebDriverWait wait1) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
 
    // ----- Геттери -----
    public String getTitleText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }
 
    public String getPriceText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(price)).getText();
    }
   public WebElement getAddToCartButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
    }
 
    public WebElement getMainImage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mainImage));
    }
 
    public boolean hasDescriptionSection() {
        return !driver.findElements(descriptionSection).isEmpty();
    }
 // Методы
    
    public void clickAddToCart() {
        getAddToCartButton().click();
    }
 
    public void openMainImage() {
        getMainImage().click();
    }
 
    public boolean isProductVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).isDisplayed();
    }
 
    public boolean isPriceDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(price)).isDisplayed();
    }
 
    public boolean isAddToCartEnabled() {
        return getAddToCartButton().isEnabled();
    }
}

