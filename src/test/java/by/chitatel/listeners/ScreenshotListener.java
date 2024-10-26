package by.chitatel.listeners;

import by.chitatel.ui.driver.DriverSingleton;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ScreenshotListener implements ITestListener {
    private WebDriver driver;

    public ScreenshotListener() {
        this.driver = DriverSingleton.getWebDriver();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.addAttachment("Test failed", saveScreenshotOnFailure());
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public InputStream saveScreenshotOnFailure() {
        return new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
    }
}
