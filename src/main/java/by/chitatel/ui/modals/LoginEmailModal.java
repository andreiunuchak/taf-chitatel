package by.chitatel.ui.modals;

import by.chitatel.ui.pages.ChitatelPage;
import by.chitatel.ui.pages.ForgotPasswordPage;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginEmailModal extends AbstractBaseModal {

    public LoginEmailModal(WebDriver driver) {
        super(driver);
    }
    
    By tabButtonEmailBy = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[1]");
    By tabButtonPhoneBy = By.xpath("//div[@class='w-swith-tabs margin-bottom-5 _js-bouble-tabs']/a[2]");
    By inputFieldEmailBy = By.xpath("//input[@name='email']");
    By inputFieldPasswordBy = By.xpath("//input[@name='password']");
    By buttonForgotPassword = By.xpath("//a[@href='https://chitatel.by/password/reset']");
    By checkboxRememberMeBy = By.xpath("//div[@class='w-tab-content _js-input-tab-content _active']//div[@class='input checkbox']/div/div");
    By buttonLoginBy = By.xpath("//input[@id='send-login']");
    By buttonCloseBy = By.xpath("//img[@class='close']");

    public void clickTabEmail() {
        Waiters.waitForElementBeingClickable(driver, tabButtonEmailBy).click();
        return;
    }

    public LoginEmailModal clickTabPhone() {
        Waiters.waitForElementBeingClickable(driver, tabButtonPhoneBy).click();
        return this;
    }

    public LoginEmailModal inputEmail(String email) {
        Waiters.waitForElementPresence(driver, inputFieldEmailBy).sendKeys(email);
        return this;
    }

    public LoginEmailModal inputPassword(String password) {
        Waiters.waitForElementPresence(driver, inputFieldPasswordBy).sendKeys(password);
        return this;
    }

    public ForgotPasswordPage clickForgotPasswordButton() {
        Waiters.waitForElementBeingClickable(driver, buttonForgotPassword).click();
        return new ForgotPasswordPage(driver);
    }

    public LoginEmailModal clickRememberMeCheckbox() {
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

    public void performLogin(String email, String password, boolean toggleRememberMe) {
        inputEmail(email);
        inputPassword(password);
        if (toggleRememberMe) {
            clickRememberMeCheckbox();
        }
        clickLoginButton();
    }
}
