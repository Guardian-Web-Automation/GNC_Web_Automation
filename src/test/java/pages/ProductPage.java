package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentLogger;

import java.time.Duration;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver){
        super(driver);
    }

    public void selectSeries(String seriesName){
        By seriesLocator = By.xpath("//a[contains(@class,'block-swatch') and @data-value='"+seriesName+"']");
        safeClick(seriesLocator);
        ExtentLogger.info("Selected series: " + seriesName);
    }

    public void selectSize(String sizeText){
        // size labels may have 'option-lbs' or plain text
        By sizeLocator = By.xpath("//label[(contains(@class,'option-lbs') or contains(@class,'block-swatch')) and @data-value= '"+sizeText+" lbs']");
        safeClick(sizeLocator);
        ExtentLogger.info("Selected size: " + sizeText+" lbs");
    }

    public void selectFlavour(String flavour){
        By flavourLocator = By.xpath("//span[@class='variant-options' and text()='"+flavour+"']");
        safeClick(flavourLocator);
        ExtentLogger.info("Selected flavour: " + flavour);
    }

    public void setQuantity(int targetQty) {

        By qtyInput = By.xpath("(//input[@class='quantity-selector__input' and @name='quantity'])[2]");
        By plusBtn  = By.xpath("(//button[@aria-label='Increase quantity'])[2]");
        By minusBtn = By.xpath("(//button[@aria-label='Decrease quantity'])[2]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement qtyElement =
                wait.until(ExpectedConditions.visibilityOfElementLocated(qtyInput));
        WebElement plusElement =
                wait.until(ExpectedConditions.elementToBeClickable(plusBtn));
        WebElement minusElement =
                wait.until(ExpectedConditions.elementToBeClickable(minusBtn));

        int currentQty = Integer.parseInt(qtyElement.getAttribute("value"));

        if (targetQty < 1) {
            throw new IllegalArgumentException("Quantity must be >= 1");
        }

        LOGGER.info("Current Qty: " + currentQty + " | Target Qty: " + targetQty);

        while (currentQty != targetQty) {

            if (currentQty < targetQty) {
                scrollToElement(driver,plusElement);
                wait.until(ExpectedConditions.elementToBeClickable(plusBtn)).click();
                currentQty++;
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(minusBtn)).click();
                currentQty--;
            }
        }

        LOGGER.info("Final Quantity set to: " + currentQty);
    }


    public void clickAddToCart(){
        By addToCart = By.xpath("(//button[@type='submit']//div[@class='buybutton-text'])[2]");
        safeClick(addToCart);
        ExtentLogger.info("Clicked Add to Cart");
    }

}
