package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ExtentLogger;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver){
        super(driver);
    }

    public String getProductNameInCart(){
        By nameLocator = By.xpath("//*[@id='cart-drawer']/div[2]/div[2]/div[1]/div/line-item/div[2]/div[1]/a/span");
        try{
            WebElement el = waitForVisibility(nameLocator);
            String txt = el.getText();
            ExtentLogger.info("Found product name in cart: " + txt);
            return txt;
        }catch (Exception e){
            ExtentLogger.fail("Unable to read product name from cart: " + e.getMessage());
            throw e;
        }
    }

    public int getProductQuantityInCart(){
        By qtyLocator = By.xpath("//*[@id='cart-drawer']/div[2]/div[2]/div[1]/div/line-item/div[2]/div[2]/div/line-item-quantity/quantity-selector/input");
        try{
            WebElement el = waitForVisibility(qtyLocator);
            String val = el.getAttribute("value");
            int qty = Integer.parseInt(val);
            ExtentLogger.info("Found product qty in cart: " + qty);
            return qty;
        }catch (Exception e){
            ExtentLogger.fail("Unable to read product quantity from cart: " + e.getMessage());
            throw e;
        }
    }

    public void clickCheckout(){
        By checkout = By.xpath("//*[@id='cart-drawer']/div[3]/div[3]/div[2]/form/div/a");
        safeClick(checkout);
        waitForPageLoad();
        ExtentLogger.info("Clicked Checkout from Cart");
    }
}
