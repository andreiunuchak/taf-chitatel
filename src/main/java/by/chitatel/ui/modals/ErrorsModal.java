package by.chitatel.ui.modals;

import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ErrorsModal extends AbstractBaseModal {
    public ErrorsModal(WebDriver driver) {
        super(driver);
    }

    By textTitleBy = By.xpath("//div[@class='sweet-alert showSweetAlert visible']//h2");
    By textMessageBy = By.xpath("//div[@class='alert alert-danger']/ul");
    By buttonOkBy = By.xpath("//button[@class='confirm']");

    public String getTitle(){
        return Waiters.waitForElementPresence(driver, textTitleBy).getText();
    }

    public String getErrorMessage(){
        return Waiters.waitForElementPresence(driver, textMessageBy).getText();
    }

    public void clickOkButton(){
        Waiters.waitForElementBeingClickable(driver, buttonOkBy).click();
    }
}
