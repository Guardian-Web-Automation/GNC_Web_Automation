package data;

import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider(name = "CategoryData")
    public Object[][] getCategoryData() {
        return new Object[][] {
                {"Crazy Deals", "crazydeals"}
        };
    }


}
