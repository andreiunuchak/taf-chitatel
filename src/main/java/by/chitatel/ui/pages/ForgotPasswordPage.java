package by.chitatel.ui.pages;

import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends AbstractBasePage {
    private final String BASE_URL = "https://chitatel.by/password/reset";

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    By inputFieldEmailBy = By.xpath("");
    By buttonSendBy = By.xpath("");

    public ForgotPasswordPage inputEmail(String email) {
        Waiters.waitForElementPresence(driver, inputFieldEmailBy).sendKeys(email);
        return this;
    }

    public void clickSendButton() {
        Waiters.waitForElementPresence(driver, buttonSendBy).click();
    }

    @Override
    public ForgotPasswordPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
