package by.chitatel.ui.modals;

import by.chitatel.ui.pages.ProductPage;
import by.chitatel.ui.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchModal extends BaseModal{
    private final By searchResultItemBy = By.xpath("//div[@class='products ']");
    private final By searchResultItemTitleBy = By.xpath("./a/div/div[2]/div[1]/span");
    private final By searchResultItemAuthorBy = By.xpath("./a/div/div[2]/div[2]");
    private final By searchResultItemPriceBy = By.xpath("./a/div/div[2]/div[3]/div/div[1]");

    public List<WebElement> getSearchResults(){
        return Waiters.waitForElementsPresence(driver, searchResultItemBy);
    }

    public ProductPage clickOnSearchResultItem(int index){
        Waiters.waitForElementsPresence(driver, searchResultItemBy).get(index).click();
        return new ProductPage();
    }

    public String getSearchResultTitle(int index){
        return getSearchResults().get(index).findElement(searchResultItemTitleBy).getText();
    }

    public String getSearchResultAuthor(int index){
        return getSearchResults().get(index).findElement(searchResultItemAuthorBy).getText();
    }

    public String getSearchResultPrice(int index){
        return getSearchResults().get(index).findElement(searchResultItemPriceBy).getText();
    }
}
