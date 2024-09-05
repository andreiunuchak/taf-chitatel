package by.chitatel.ui.modals;

import by.chitatel.ui.pages.ChitatelPage;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPhoneModal extends AbstractBaseModal {
    public LoginPhoneModal(WebDriver driver) {
        super(driver);
    }

    By tabButtonEmailBy = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[1]");
    By tabButtonPhoneBy = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[2]");
    By inputFieldPhoneBy = By.xpath("//input[@name='tel']");
    By butonSendPasswordBy = By.xpath("//input[@id='send-code-login']");
    By inputFieldPasswordBy = By.xpath("//input[@name='password_phone']");
    By checkboxRememberMeBy = By.xpath("//div[@class='w-tab-content _js-input-tab-content _active']//div[@class='input checkbox']/div/div");
    By buttonLoginBy = By.xpath("//input[@id='send-login-by-phone']");
    By buttonCloseBy = By.xpath("//img[@class='close']");

    public LoginEmailModal clickTabEmail() {
        Waiters.waitForElementBeingClickable(driver, tabButtonEmailBy).click();
        return new LoginEmailModal(driver);
    }

    public LoginPhoneModal clickTabPhone() {
        Waiters.waitForElementBeingClickable(driver, tabButtonPhoneBy).click();
        return this;
    }

    public LoginPhoneModal inputPhoneNumber(String number) {
        Waiters.waitForElementPresence(driver, inputFieldPhoneBy).sendKeys(number);
        return this;
    }

    public LoginPhoneModal clickSendCodeButton() {
        Waiters.waitForElementBeingClickable(driver, butonSendPasswordBy).click();
        return this;
    }

    public LoginPhoneModal inputPassword(String password) {
        Waiters.waitForElementPresence(driver, inputFieldPasswordBy).sendKeys(password);
        return this;
    }

    public LoginPhoneModal clickRememberMeCheckbox() {
        Waiters.waitForElementPresence(driver, checkboxRememberMeBy).click();
        return this;
    }

    public ChitatelPage clickCloseButton() {
        Waiters.waitForElementBeingClickable(driver, buttonCloseBy).click();
        return new ChitatelPage(driver);
    }

    public void clickLoginButton() {
        Waiters.waitForElementBeingClickable(driver, buttonLoginBy).click();
    }

    public void performLogin(String phoneNumber, String password, boolean toggleRememberMe) {
        inputPhoneNumber(phoneNumber);
        inputPassword(password);
        if (toggleRememberMe) {
            clickRememberMeCheckbox();
        }
        clickLoginButton();
    }
}
