package by.chitatel.ui.modals;

import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorModal extends BaseModal {
    private final By title = By.xpath("//div[@class='sweet-alert showSweetAlert visible']//h2");
    private final By errorMessage = By.xpath("//div[@class='alert alert-danger']/ul");
    private final By buttonOk = By.xpath("//button[@class='confirm']");

    public ErrorModal() {
    }

    public String getTitle(){
        return Waiters.waitForElementPresence(driver, title).getText();
    }

    public String getErrorMessage(){
        return Waiters.waitForElementPresence(driver, errorMessage).getText();
    }

    public void clickButtonOk(){
        Waiters.waitForElementBeingClickable(driver, buttonOk).click();
    }
}
