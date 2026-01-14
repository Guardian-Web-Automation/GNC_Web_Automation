package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductListingPage;
import pages.ProductPage;
import utils.ExtentLogger;
import utils.TestListener;


@Listeners(TestListener.class)
public class HomePageTests extends BaseTest {

    @Test(priority = 0, enabled = true)
    public void userJourney(){
        HomePage hp = new HomePage(driver);
        // 1. go to Protein section
        hp.clickOnHomeCategory("Protein");

        // 2. On PLP click product by name
        ProductListingPage plp = new ProductListingPage(driver);
        String productName = "100% Whey Protein"; // adjust if needed
        plp.clickProductByName(productName);

        // 3. On PDP select series, size, flavour
        ProductPage pdp = new ProductPage(driver);
        String series = "Pro Performance Whey"; // sample series text from screenshot
        String size = "2"; // sample size text
        String flavour = "Mawa Kulfi"; // sample flavour
        int qty = 2;

        pdp.selectSeries(series);
        pdp.selectSize(size);
        pdp.selectFlavour(flavour);

        // 4. add quantity
        pdp.setQuantity(qty);

//        // 5. click add to cart
        pdp.clickAddToCart();

//        // 6. verify cart
        CartPage cart = new CartPage(driver);
        String cartName = cart.getProductNameInCart();
        int cartQty = cart.getProductQuantityInCart();

        Assert.assertTrue(cartName.contains(productName) || cartName.toLowerCase().contains("whey protein"), "Product name in cart does not match expected. Found: "+cartName);
        Assert.assertEquals(cartQty, qty, "Cart quantity mismatch");

//        // 7. click checkout and assert on checkout page
        cart.clickCheckout();
//
    }
}
