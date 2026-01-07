package pages;

import base.BasePage;
import org.openqa.selenium.*;
import utils.ExtentLogger;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
    }

    // Navigate to the Protein category/section on the homepage
    public void clickOnHomeCategory(String categoryName) {
        By proteinLink = By.xpath("//h4[text()='"+categoryName+"']/ancestor::div[contains(@class,'image-link-blocks__item')]//a");
        try{
            WebElement el = waitForVisibility(proteinLink);
            scrollToElement(driver, el);
            safeClick(proteinLink);
            waitForPageLoad();
            ExtentLogger.info("Navigated to "+categoryName+" section At HomePage");
        }catch (Exception e){
            ExtentLogger.fail("Unable to navigate to "+categoryName+" section: " + e.getMessage());
            throw e;
        }
    }

}
