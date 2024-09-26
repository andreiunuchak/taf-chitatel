package by.chitatel.ui.modals;

import by.chitatel.ui.pages.HomePage;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;

public class LoginPhoneModal extends BaseModal {
    private final By tabButtonEmail = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[1]");
    private final By tabButtonPhone = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[2]");
    private final By inputFieldPhone = By.xpath("//input[@name='tel']");
    private final By butonSendPassword = By.xpath("//input[@id='send-code-login']");
    private final By inputFieldPassword = By.xpath("//input[@name='password_phone']");
    private final By checkboxRememberMe = By.xpath("//div[@class='w-tab-content _js-input-tab-content _active']//div[@class='input checkbox']/div/div");
    private final By buttonLogin = By.xpath("//input[@id='send-login-by-phone']");
    private final By buttonClose = By.xpath("//img[@class='close']");

    public LoginEmailModal clickTabEmail() {
        Waiters.waitForElementBeingClickable(driver, tabButtonEmail).click();
        return new LoginEmailModal();
    }

    public LoginPhoneModal clickTabPhone() {
        Waiters.waitForElementBeingClickable(driver, tabButtonPhone).click();
        return this;
    }

    public LoginPhoneModal inputPhoneNumber(String number) {
        Waiters.waitForElementPresence(driver, inputFieldPhone).sendKeys(number);
        return this;
    }

    public LoginPhoneModal clickSendCodeButton() {
        Waiters.waitForElementBeingClickable(driver, butonSendPassword).click();
        return this;
    }

    public LoginPhoneModal inputPassword(String password) {
        Waiters.waitForElementPresence(driver, inputFieldPassword).sendKeys(password);
        return this;
    }

    public LoginPhoneModal clickRememberMeCheckbox() {
        Waiters.waitForElementBeingClickable(driver, checkboxRememberMe).click();
        return this;
    }

    public HomePage clickCloseButton() {
        Waiters.waitForElementBeingClickable(driver, buttonClose).click();
        return new HomePage();
    }

    public void clickLoginButton() {
        Waiters.waitForElementBeingClickable(driver, buttonLogin).click();
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
