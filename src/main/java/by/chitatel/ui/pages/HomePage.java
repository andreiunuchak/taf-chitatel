package by.chitatel.ui.pages;

import by.chitatel.ui.modals.LoginPhoneDialogPage;
import by.chitatel.ui.utils.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private final By loginButton = By.xpath("//a[@class='block__link login__link login_popup']/div[2]");

    @Step("Click on the 'Login' button")
    public LoginPhoneDialogPage clickLoginButton() {
        Waiters.waitForElementBeingClickable(loginButton).click();
        return new LoginPhoneDialogPage();
    }

    @Override
    @Step("Open page " + BASE_URL)
    public HomePage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
