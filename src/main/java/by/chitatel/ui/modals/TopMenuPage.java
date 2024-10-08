package by.chitatel.ui.modals;

import by.chitatel.ui.pages.ContactsPage;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;

public class TopMenuPage extends BaseDialogPage {
    private final By menuMainButtonBy = By.xpath("//ul[@class='main-menu']/li[1]");
    private final By menuDeliveryButtonBy = By.xpath("//ul[@class='main-menu']/li[2]");
    private final By menuPaymentsButtonBy = By.xpath("//ul[@class='main-menu']/li[3]");
    private final By menuHowToOrderButtonBy = By.xpath("//ul[@class='main-menu']/li[4]");
    private final By menuDiscountsButtonBy = By.xpath("//ul[@class='main-menu']/li[5]");
    private final By menuNewsButtonBy = By.xpath("//ul[@class='main-menu']/li[6]");
    private final By menuContactsButtonBy = By.xpath("//ul[@class='main-menu']/li[7]");

    public void clickMenuMainButton() {
        Waiters.waitForElementBeingClickable(menuMainButtonBy).click();
    }

    public void clickMenuDeliveryButton() {
        Waiters.waitForElementBeingClickable(menuDeliveryButtonBy).click();
    }

    public void clickMenuPaymentsButton() {
        Waiters.waitForElementBeingClickable(menuPaymentsButtonBy).click();
    }

    public void clickMenuHowToOrderButton() {
        Waiters.waitForElementBeingClickable(menuHowToOrderButtonBy).click();
    }

    public void clickMenuDiscountsButton() {
        Waiters.waitForElementBeingClickable(menuDiscountsButtonBy).click();
    }

    public void clickMenuNewsButton() {
        Waiters.waitForElementBeingClickable(menuNewsButtonBy).click();
    }

    public ContactsPage clickMenuContactsButton() {
        Waiters.waitForElementBeingClickable(menuContactsButtonBy).click();
        return new ContactsPage();
    }
}
