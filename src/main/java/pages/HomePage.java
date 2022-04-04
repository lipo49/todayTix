package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static utils.AppStrings.*;

public class HomePage extends BasePage {


    // Homepage Locators

    private By searchTextField = By.id("search_query_top");
    private By dropDownList = By.cssSelector("div[class='ac_results'] > ul > li");
    private By checkoutButton = By.cssSelector("a[title='View my shopping cart']");


    // Constructor
    public HomePage(Browser browser) {
        super(browser);
    }

    private WebDriverWait wait = new WebDriverWait(browser.getDriver(), EXPLICIT_WAIT);

    public void enterSearchTextFieldValues(String productName) throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        wait.until(ExpectedConditions.elementToBeClickable(searchTextField));
        browser.type(searchTextField, productName, "" + productName + " in search text field");
        wait.until(ExpectedConditions.elementToBeClickable(dropDownList));
        List<WebElement> dropDownItems = browser.getDriver().findElements(dropDownList);
        WebElement firstItem = dropDownItems.get(0);
        browser.click(firstItem, "1st result in AutoComplete");
    }

    public void clickOnCart() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        browser.click(checkoutButton, "Cart");
    }


}
