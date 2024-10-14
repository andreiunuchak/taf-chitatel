package by.chitatel.listeners;

import by.chitatel.ui.driver.DriverSingleton;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ScreenshotListener implements ITestListener {


    public ScreenshotListener() {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.addAttachment("Any text", saveScreenshotOnFailure());
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    public InputStream saveScreenshotOnFailure() {
        return new ByteArrayInputStream(((TakesScreenshot) DriverSingleton.getWebDriver()).getScreenshotAs(OutputType.BYTES));
    }
}
