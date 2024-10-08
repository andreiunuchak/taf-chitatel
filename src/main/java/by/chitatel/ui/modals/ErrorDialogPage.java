package by.chitatel.ui.modals;

import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;

public class ErrorDialogPage extends BaseDialogPage {
    private final By title = By.xpath("//div[@class='sweet-alert showSweetAlert visible']//h2");
    private final By errorMessage = By.xpath("//div[@class='alert alert-danger']/ul");
    private final By buttonOk = By.xpath("//button[@class='confirm']");

    public String getTitle() {
        return Waiters.waitForElementPresence(title).getText();
    }

    public String getErrorMessage() {
        return Waiters.waitForElementPresence(errorMessage).getText();
    }

    public void clickButtonOk() {
        Waiters.waitForElementBeingClickable(buttonOk).click();
    }
}
