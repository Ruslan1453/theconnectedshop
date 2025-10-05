package theconnectedshop.pages;
 
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class ProductPage {
 
    private final WebDriver driver;
    private final WebDriverWait wait;
 
    // Локатори
    private final By title = By.cssSelector("h1[class='font-heading-extra-bold margin0 h2']");
    private final By price = By.cssSelector("div[id='price-template--19784308457713__main'] div:nth-child(1) div:nth-child(1) div:nth-child(1) span:nth-child(2)");
    private final By addToCartButton = By.cssSelector("button[id='card-submit-button-template--19784308457713__main'] span");
    private final By mainImage = By.cssSelector("img.product__image");
    private final By descriptionSection = By.cssSelector("div.product__description");
    
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

    public By getPrice() {
        return price;
    }
}

