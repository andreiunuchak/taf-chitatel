package by.chitatel.ui;

import by.chitatel.ui.driver.DriverSingleton;
import org.testng.annotations.AfterTest;


public class BaseTest {

    @AfterTest
    public void closeDriver() {
        DriverSingleton.closeWebDriver();
    }
}
