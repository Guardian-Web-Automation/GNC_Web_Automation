package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ExtentLogger;

public class ProductListingPage extends BasePage {

    public ProductListingPage(WebDriver driver){
        super(driver);
    }

    // Clicks product by visible name (substring match)
    public void clickProductByName(String productName){
        // try to locate product tile/title by text
        By productAnchor = By.xpath("//product-list[@class='product-list']//a[text()='"+productName+"']");
        try{
            WebElement el = waitForVisibility(productAnchor);
            scrollToElement(driver, el);
            Thread.sleep(1000); // small wait before click
            safeClick(productAnchor);
           // waitForPageLoad();
            ExtentLogger.info("Clicked product on PLP: " + productName);
        }catch (Exception e){
            // fallback: try to find by link text contains
            By alt = By.xpath("//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), '" + productName.toLowerCase() + "')]");
            safeClick(alt);
            waitForPageLoad();
            ExtentLogger.info("Clicked product (fallback) on PLP: " + productName);
        }
    }
}
