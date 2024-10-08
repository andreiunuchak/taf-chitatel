package by.chitatel.ui.modals;

import by.chitatel.ui.pages.HomePage;
import by.chitatel.ui.pages.ForgotPasswordPage;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;

public class LoginEmailDialogPage extends BaseDialogPage {
    private final By tabButtonEmail = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[1]");
    private final By tabButtonPhone = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[2]");
    private final By inputFieldEmail = By.xpath("//input[@name='email']");
    private final By inputFieldPassword = By.xpath("//input[@name='password']");
    private final By buttonForgotPassword = By.xpath("//a[@href='https://chitatel.by/password/reset']");
    private final By checkboxRememberMe = By.xpath("//div[@class='w-tab-content _js-input-tab-content _active']//div[@class='input checkbox']/div/div");
    private final By buttonLogin = By.xpath("//input[@id='send-login']");
    private final By buttonClose = By.xpath("//img[@class='close']");

    public LoginEmailDialogPage clickTabEmail() {
        Waiters.waitForElementBeingClickable(tabButtonEmail).click();
        return this;
    }

    public LoginEmailDialogPage clickTabPhone() {
        Waiters.waitForElementBeingClickable(tabButtonPhone).click();
        return this;
    }

    public LoginEmailDialogPage inputEmail(String email) {
        Waiters.waitForElementPresence(inputFieldEmail).sendKeys(email);
        return this;
    }

    public LoginEmailDialogPage inputPassword(String password) {
        Waiters.waitForElementPresence(inputFieldPassword).sendKeys(password);
        return this;
    }

    public ForgotPasswordPage clickForgotPasswordButton() {
        Waiters.waitForElementBeingClickable(buttonForgotPassword).click();
        return new ForgotPasswordPage();
    }

    public LoginEmailDialogPage clickRememberMeCheckbox() {
        Waiters.waitForElementPresence(checkboxRememberMe).click();
        return this;
    }

    public HomePage clickCloseButton() {
        Waiters.waitForElementBeingClickable(buttonClose).click();
        return new HomePage();
    }

    public void clickLoginButton() {
        Waiters.waitForElementBeingClickable(buttonLogin).click();
    }

    public void performLogin(String email, String password, boolean toggleRememberMe) {
        inputEmail(email);
        inputPassword(password);
        if (toggleRememberMe) {
            clickRememberMeCheckbox();
        }
        clickLoginButton();
    }
}
