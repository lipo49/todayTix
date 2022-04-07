package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.AppStrings.*;

public class ShowPage extends BasePage {

    // Show Locators

    private By showTitle = By.id("product-h1");
    private By getTicketsButton = By.cssSelector("span[class='MuiButton-label']");



    // Constructor
    public ShowPage(Browser browser) {
        super(browser);
    }


    private WebDriverWait wait = new WebDriverWait(browser.getDriver(), EXPLICIT_WAIT);

    public String getShowTitle() {
        String showTitleString = browser.getText(showTitle).toLowerCase();
        System.out.println(showTitleString);
        return showTitleString;
    }

    public void clickGetTicketsButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        wait.until(ExpectedConditions.elementToBeClickable(getTicketsButton));
        browser.click(getTicketsButton,"Get Tickets button");
    }

}
