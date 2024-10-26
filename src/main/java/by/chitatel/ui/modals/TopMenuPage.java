package by.chitatel.ui.modals;

import by.chitatel.ui.pages.ContactsPage;
import by.chitatel.ui.utils.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class TopMenuPage extends BaseDialogPage {
    private final By menuMainButtonBy = By.xpath("//ul[@class='main-menu']/li[1]");
    private final By menuDeliveryButtonBy = By.xpath("//ul[@class='main-menu']/li[2]");
    private final By menuPaymentsButtonBy = By.xpath("//ul[@class='main-menu']/li[3]");
    private final By menuHowToOrderButtonBy = By.xpath("//ul[@class='main-menu']/li[4]");
    private final By menuDiscountsButtonBy = By.xpath("//ul[@class='main-menu']/li[5]");
    private final By menuNewsButtonBy = By.xpath("//ul[@class='main-menu']/li[6]");
    private final By menuContactsButtonBy = By.xpath("//ul[@class='main-menu']/li[7]");

    @Step("Click on the 'Main' menu button")
    public void clickMenuMainButton() {
        Waiters.waitForElementBeingClickable(menuMainButtonBy).click();
    }

    @Step("Click on the 'Delivery' menu button")
    public void clickMenuDeliveryButton() {
        Waiters.waitForElementBeingClickable(menuDeliveryButtonBy).click();
    }

    @Step("Click on the 'Payments' menu button")
    public void clickMenuPaymentsButton() {
        Waiters.waitForElementBeingClickable(menuPaymentsButtonBy).click();
    }

    @Step("Click on the 'How to Order' menu button")
    public void clickMenuHowToOrderButton() {
        Waiters.waitForElementBeingClickable(menuHowToOrderButtonBy).click();
    }

    @Step("Click on the 'Discounts' menu button")
    public void clickMenuDiscountsButton() {
        Waiters.waitForElementBeingClickable(menuDiscountsButtonBy).click();
    }

    @Step("Click on the 'News' menu button")
    public void clickMenuNewsButton() {
        Waiters.waitForElementBeingClickable(menuNewsButtonBy).click();
    }

    @Step("Click on the 'Contacts' menu button")
    public ContactsPage clickMenuContactsButton() {
        Waiters.waitForElementBeingClickable(menuContactsButtonBy).click();
        return new ContactsPage();
    }
}
