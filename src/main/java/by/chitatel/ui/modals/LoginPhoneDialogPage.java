package by.chitatel.ui.modals;

import by.chitatel.ui.pages.HomePage;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;

public class LoginPhoneDialogPage extends BaseDialogPage {
    private final By tabButtonEmail = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[1]");
    private final By tabButtonPhone = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[2]");
    private final By inputFieldPhone = By.xpath("//input[@name='tel']");
    private final By butonSendPassword = By.xpath("//input[@id='send-code-login']");
    private final By inputFieldPassword = By.xpath("//input[@name='password_phone']");
    private final By checkboxRememberMe = By.xpath("//div[@class='w-tab-content _js-input-tab-content _active']//div[@class='input checkbox']/div/div");
    private final By buttonLogin = By.xpath("//input[@id='send-login-by-phone']");
    private final By buttonClose = By.xpath("//img[@class='close']");

    public LoginEmailDialogPage clickTabEmail() {
        Waiters.waitForElementBeingClickable(tabButtonEmail).click();
        return new LoginEmailDialogPage();
    }

    public LoginPhoneDialogPage clickTabPhone() {
        Waiters.waitForElementBeingClickable(tabButtonPhone).click();
        return this;
    }

    public LoginPhoneDialogPage inputPhoneNumber(String number) {
        Waiters.waitForElementPresence(inputFieldPhone).sendKeys(number);
        return this;
    }

    public LoginPhoneDialogPage clickSendCodeButton() {
        Waiters.waitForElementBeingClickable(butonSendPassword).click();
        return this;
    }

    public LoginPhoneDialogPage inputPassword(String password) {
        Waiters.waitForElementPresence(inputFieldPassword).sendKeys(password);
        return this;
    }

    public LoginPhoneDialogPage clickRememberMeCheckbox() {
        Waiters.waitForElementBeingClickable(checkboxRememberMe).click();
        return this;
    }

    public HomePage clickCloseButton() {
        Waiters.waitForElementBeingClickable(buttonClose).click();
        return new HomePage();
    }

    public void clickLoginButton() {
        Waiters.waitForElementBeingClickable(buttonLogin).click();
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
