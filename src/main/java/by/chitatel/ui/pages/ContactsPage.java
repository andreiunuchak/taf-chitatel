package by.chitatel.ui.pages;

import by.chitatel.ui.utils.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ContactsPage extends BasePage {
    private final String CONTACTS_URL = BASE_URL + "/contact";
    private final By inputFieldNameBy = By.xpath("//input[@name='message_name']");
    private final By inputFieldPhoneBy = By.xpath("//input[@name='message_phone']");
    private final By inputFieldThemeBy = By.xpath("//input[@name='message_theme']");
    private final By inputFieldMessageBy = By.xpath("//textarea[@name='message_note']");
    private final By buttonSendBy = By.xpath("//input[@id='send-message']");

    @Step("Input name")
    public ContactsPage inputName(String name) {
        Waiters.waitForElementPresence(inputFieldNameBy).sendKeys(name);
        return this;
    }

    @Step("Input phone number")
    public ContactsPage inputPhoneNumber(String phone) {
        Waiters.waitForElementPresence(inputFieldPhoneBy).sendKeys(phone);
        return this;
    }

    @Step("Input theme")
    public ContactsPage inputTheme(String theme) {
        Waiters.waitForElementPresence(inputFieldThemeBy).sendKeys(theme);
        return this;
    }

    @Step("Input message")
    public ContactsPage inputMessage(String message) {
        Waiters.waitForElementPresence(inputFieldMessageBy).sendKeys(message);
        return this;
    }

    @Step("Click on the 'Send' button")
    public void clickSendButton() {
        Waiters.waitForElementPresence(buttonSendBy).click();
    }

    @Override
    @Step("Open page " + CONTACTS_URL)
    public ContactsPage openPage() {
        driver.navigate().to(CONTACTS_URL);
        return this;
    }


}
