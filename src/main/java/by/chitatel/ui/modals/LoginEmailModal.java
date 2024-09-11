package by.chitatel.ui.modals;

import by.chitatel.ui.pages.ChitatelPage;
import by.chitatel.ui.pages.ForgotPasswordPage;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginEmailModal extends BaseModal {
    private final By tabButtonEmail = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[1]");
    private final By tabButtonPhone = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[2]");
    private final By inputFieldEmail = By.xpath("//input[@name='email']");
    private final By inputFieldPassword = By.xpath("//input[@name='password']");
    private final By buttonForgotPassword = By.xpath("//a[@href='https://chitatel.by/password/reset']");
    private final By checkboxRememberMe = By.xpath("//div[@class='w-tab-content _js-input-tab-content _active']//div[@class='input checkbox']/div/div");
    private final By buttonLogin = By.xpath("//input[@id='send-login']");
    private final By buttonClose = By.xpath("//img[@class='close']");

    public LoginEmailModal() {
    }
    
    public LoginEmailModal clickTabEmail() {
        Waiters.waitForElementBeingClickable(driver, tabButtonEmail).click();
        return this;
    }

    public LoginEmailModal clickTabPhone() {
        Waiters.waitForElementBeingClickable(driver, tabButtonPhone).click();
        return this;
    }

    public LoginEmailModal inputEmail(String email) {
        Waiters.waitForElementPresence(driver, inputFieldEmail).sendKeys(email);
        return this;
    }

    public LoginEmailModal inputPassword(String password) {
        Waiters.waitForElementPresence(driver, inputFieldPassword).sendKeys(password);
        return this;
    }

    public ForgotPasswordPage clickForgotPasswordButton() {
        Waiters.waitForElementBeingClickable(driver, buttonForgotPassword).click();
        return new ForgotPasswordPage();
    }

    public LoginEmailModal clickRememberMeCheckbox() {
        Waiters.waitForElementPresence(driver, checkboxRememberMe).click();
        return this;
    }

    public ChitatelPage clickCloseButton() {
        Waiters.waitForElementBeingClickable(driver, buttonClose).click();
        return new ChitatelPage();
    }

    public void clickLoginButton() {
        Waiters.waitForElementBeingClickable(driver, buttonLogin).click();
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
