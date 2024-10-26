package by.chitatel.ui.pages;

import by.chitatel.ui.utils.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ForgotPasswordPage extends BasePage {
    private final String FORGOT_PASSWORD_PAGE_URL = BASE_URL + "/password/reset";
    private final By inputFieldEmail = By.xpath("//input[@id='email']");
    private final By buttonSend = By.xpath("//input[@id='send-reset']");

    @Step("Input email")
    public ForgotPasswordPage inputEmail(String email) {
        Waiters.waitForElementPresence(inputFieldEmail).sendKeys(email);
        return this;
    }

    @Step("Click on the 'Send' button")
    public void clickButtonSend() {
        Waiters.waitForElementPresence(buttonSend).click();
    }

    @Override
    @Step("Open page " + FORGOT_PASSWORD_PAGE_URL)
    public ForgotPasswordPage openPage() {
        driver.navigate().to(FORGOT_PASSWORD_PAGE_URL);
        return this;
    }
}
