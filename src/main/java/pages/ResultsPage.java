package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static utils.AppStrings.*;

public class ResultsPage extends BasePage
{


    // Zoopla's Locators

    private By createEmailAlertButton = By.cssSelector("a[data-testid='create-email-alert-button']");
    private By saveThisSearchButton = By.cssSelector("a[data-testid='save-search-button']");
    private By manageMyEmailAlertsButton = By.xpath("a[href='/myaccount/alerts-searches/']");
    private By postCodeInSavedSearches = By.xpath("//h4[text()='SE1 2LH']");
    private By filtersButton = By.xpath("//button[@data-testid='search-results-header_filters-button']");
    private By keywordsSearchField = By.id("modal-keywords-filter");
    private By updateResultsButton = By.xpath("//button[@data-testid='update-results-button']");
    private By ZooplaProfileButton = By.xpath("//a[@data-testid='header-profile-account']");
    private By alertsAndSearches = By.xpath("//*[contains(text(),'Alerts & searches')]");
    private By closeSuccessAlert = By.xpath("//button[@data-testid='modal-close']");
    private By firstSearchResult = By.xpath("//div[@data-testid='search-result']");
    private By garageEle = By.className("css-qvispy-FeaturesContainer");
    private By fifteenMinElem = By.cssSelector("button[label='Travel time'] p[data-testid='text']");


    private WebDriverWait wait;

    // Constructor
    public ResultsPage(Browser browser)
    {
        super(browser);
        wait = new WebDriverWait(browser.getDriver(), 10);
    }

    public void clickSaveThisSearchButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(saveThisSearchButton, "Save This Search Button");
    }


    public void clickOnFilterButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(filtersButton, "Filters Button");
    }

    public void typeKeyword() throws ParserConfigurationException, IOException, SAXException
    {
        browser.type(keywordsSearchField, FILTER_KEYWORD, "type garage");
    }

    public void clickUpdateResultsButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(updateResultsButton, "Update Results");
    }


    public void clickZooplaProfileButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(ZooplaProfileButton, "My Profile button");

    }

    public void clickAlertAndSearches() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(alertsAndSearches, "Alerts & Searches");
    }

    public boolean isPostCodeAppearsInSavedSearches(){
        String postCodeElement = browser.getText(postCodeInSavedSearches);
        if(postCodeElement.equals(TRAVEL_TIME_POSTCODE)){
            return true;
        } else {
            System.out.println(FIFTEEN_MIN_TRAVEL + " from " + TRAVEL_TIME_POSTCODE + " was NOT saved");
            return false;
        }

    }

    public void closeAlertSuccess() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(closeSuccessAlert, "Close Success Popup");
    }


    public void createEmailAlertButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(createEmailAlertButton, "Create Email Alert Button");
    }

    public String getFirstElementText(){
        WebElement firstElement = browser.getDriver().findElement(By.cssSelector("ul[data-testid='result-list'] li:first-child h2"));
        return firstElement.getText();
    }

    public void click1stResult() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(firstSearchResult,"First result");
    }

    public boolean isResultContainGarage(){
        String garage = browser.getText(garageEle).toLowerCase();
        if(garage.contains(FILTER_KEYWORD)){
            return true;
        } else {
            return false;
        }
    }

    public void getTravelDurationText() {
        String fifteenMinTxt = browser.getText(fifteenMinElem);
        try {
            fifteenMinTxt.equals(FIFTEEN_MIN_TRAVEL);
            report.info("This search is " + FIFTEEN_MIN_TRAVEL + " from " + TRAVEL_TIME_POSTCODE);
        } catch (Exception e){
            report.info("This search is NOT a " + FIFTEEN_MIN_TRAVEL + " from " + TRAVEL_TIME_POSTCODE);
        }
    }

}
