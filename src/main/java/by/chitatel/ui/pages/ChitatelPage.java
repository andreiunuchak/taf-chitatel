package by.chitatel.ui.pages;

import by.chitatel.ui.modals.LoginPhoneModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChitatelPage extends AbstractBasePage {
    private final String BASE_URL = "https://chitatel.by/";

    public ChitatelPage(WebDriver driver) {
        super(driver);
    }

    By loginButtonBy = By.xpath("//a[@class='block__link login__link login_popup']/div[2]");


    public LoginPhoneModal clickLoginButton() {
        driver.findElement(loginButtonBy).click();
        return new LoginPhoneModal(driver);
    }

    @Override
    public ChitatelPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
