package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ExtentLogger;
import utils.TestListener;


@Listeners(TestListener.class)
public class HomePageTests extends BaseTest {

    @Test(priority = 0,enabled = true)
    public void titleValidationtest(){
        HomePage Hp = new HomePage(driver);


    }
}
