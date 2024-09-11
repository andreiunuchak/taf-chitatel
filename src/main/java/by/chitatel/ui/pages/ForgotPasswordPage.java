package by.chitatel.ui.pages;

import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage {
    private final By inputFieldEmail = By.xpath("//input[@id='email']");
    private final By buttonSend = By.xpath("//input[@id='send-reset']");

//    public ForgotPasswordPage(WebDriver driver) {
//        super(driver);
//    }

    public ForgotPasswordPage inputEmail(String email) {
        Waiters.waitForElementPresence(driver, inputFieldEmail).sendKeys(email);
        return this;
    }

    public void clickButtonSend() {
        Waiters.waitForElementPresence(driver, buttonSend).click();
    }

    @Override
    public ForgotPasswordPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
