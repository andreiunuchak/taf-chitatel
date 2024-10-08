package by.chitatel.ui.pages;

import by.chitatel.ui.modals.LoginPhoneDialogPage;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private final By loginButton = By.xpath("//a[@class='block__link login__link login_popup']/div[2]");

    public LoginPhoneDialogPage clickLoginButton() {
        Waiters.waitForElementBeingClickable(loginButton).click();
        return new LoginPhoneDialogPage();
    }

    @Override
    public HomePage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
