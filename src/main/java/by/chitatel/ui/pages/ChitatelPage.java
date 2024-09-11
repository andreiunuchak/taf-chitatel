package by.chitatel.ui.pages;

import by.chitatel.ui.modals.LoginPhoneModal;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChitatelPage extends BasePage {
    private final String BASE_URL = "https://chitatel.by/";
    private final By loginButton = By.xpath("//a[@class='block__link login__link login_popup']/div[2]");

//    public ChitatelPage(WebDriver driver) {
//        super(driver);
//    }

    public LoginPhoneModal clickLoginButton() {
        Waiters.waitForElementBeingClickable(driver, loginButton).click();
        return new LoginPhoneModal();
    }

    @Override
    public ChitatelPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
