package by.chitatel.ui.pages;

import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;

public class ForgotPasswordPage extends BasePage {
    private final String FORGOT_PASSWORD_PAGE_URL = BASE_URL + "/password/reset";
    private final By inputFieldEmail = By.xpath("//input[@id='email']");
    private final By buttonSend = By.xpath("//input[@id='send-reset']");

    public ForgotPasswordPage inputEmail(String email) {
        Waiters.waitForElementPresence(driver, inputFieldEmail).sendKeys(email);
        return this;
    }

    public void clickButtonSend() {
        Waiters.waitForElementPresence(driver, buttonSend).click();
    }

    @Override
    public ForgotPasswordPage openPage() {
        driver.navigate().to(FORGOT_PASSWORD_PAGE_URL);
        return this;
    }
}
