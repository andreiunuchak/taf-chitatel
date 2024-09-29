package by.chitatel.ui;

import by.chitatel.ui.driver.DriverSingleton;
import org.junit.jupiter.api.AfterEach;

public class BaseTest {

    @AfterEach
    public void closeDriver() {
        DriverSingleton.closeDriver();
    }
}
