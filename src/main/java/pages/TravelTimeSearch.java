package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import static utils.AppStrings.*;

public class TravelTimeSearch extends BasePage {



    // Zoopla's Locators

    private By searchArea = By.cssSelector("input[id='search-input-location']");
    private By travelTimeSearchButton = By.cssSelector("button[type='submit']");


    private WebDriverWait wait = new WebDriverWait(browser.getDriver(),EXPLICIT_WAIT);
    Actions action = new Actions(browser.getDriver());

    // Constructor
    public TravelTimeSearch(Browser browser) {
        super(browser);
    }

    public void typeSearchLocation(String postCode) throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        wait.until(ExpectedConditions.elementToBeClickable(searchArea));
        browser.type(searchArea,postCode,"Type PostCode");
    }

    public void clickSearchButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(travelTimeSearchButton,"Search button");
    }


}















