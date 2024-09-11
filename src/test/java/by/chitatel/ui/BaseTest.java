package by.chitatel.ui;

import by.chitatel.ui.driver.DriverSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    WebDriver driver;

    @AfterEach
    public void closeDriver(){
        DriverSingleton.closeDriver();
    }
}
