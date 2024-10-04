package by.chitatel.ui.pages;

import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;

public class ContactsPage extends BasePage {
    private final String CONTACTS_URI = BASE_URL + "/contact";
    private final By inputFieldNameBy = By.xpath("//input[@name='message_name']");
    private final By inputFieldPhoneBy = By.xpath("//input[@name='message_phone']");
    private final By inputFieldThemeBy = By.xpath("//input[@name='message_theme']");
    private final By inputFieldMessageBy = By.xpath("//textarea[@name='message_note']");
    private final By buttonSendBy = By.xpath("//input[@id='send-message']");

    public ContactsPage inputName(String name) {
        Waiters.waitForElementPresence(driver, inputFieldNameBy).sendKeys(name);
        return this;
    }

    public ContactsPage inputPhoneNumber(String phone) {
        Waiters.waitForElementPresence(driver, inputFieldPhoneBy).sendKeys(phone);
        return this;
    }

    public ContactsPage inputTheme(String theme) {
        Waiters.waitForElementPresence(driver, inputFieldThemeBy).sendKeys(theme);
        return this;
    }

    public ContactsPage inputMessage(String message) {
        Waiters.waitForElementPresence(driver, inputFieldMessageBy).sendKeys(message);
        return this;
    }

    public void clickSendButton() {
        Waiters.waitForElementPresence(driver, buttonSendBy).click();
    }


    @Override
    public ContactsPage openPage() {
        driver.navigate().to(CONTACTS_URI);
        return this;
    }


}
