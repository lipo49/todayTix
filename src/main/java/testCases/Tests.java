package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import pages.*;
import utils.ExtendedAssert;
import utils.Retry;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.AppStrings.*;

public class Tests extends BaseTest {

    private HomePage homePage;
    private ResultsPage resultsPage;
    private AlertsAndSearchesPage alertsAndSearchPage;
    private LoginPage loginPage;
    private TravelTimeSearch travelTimeSearch;
    private ExtendedAssert extendedAssert;

    @BeforeMethod
    public void methodInit() {
        homePage = new HomePage(browser);
        resultsPage = new ResultsPage(browser);
        alertsAndSearchPage = new AlertsAndSearchesPage(browser);
        loginPage = new LoginPage(browser);
        travelTimeSearch = new TravelTimeSearch(browser);
        extendedAssert = new ExtendedAssert();
    }

    @Test(retryAnalyzer = Retry.class)
    public void test_01_dailyEmailUpdates() throws InterruptedException, IOException, SAXException, ParserConfigurationException {
        homePage.acceptCookies();
        loginPage.performLogin();
        homePage.clickZooplaLogo();
        homePage.tapOnToRent();
        homePage.searchForLocation();
        homePage.selectNumberOfBeds();
        homePage.selectPrice();
        homePage.clickOnSearchButton();
        resultsPage.createEmailAlertButton();
        resultsPage.closeAlertSuccess();
        resultsPage.clickZooplaProfileButton();
        resultsPage.clickAlertAndSearches();
        String newValue = alertsAndSearchPage.selectAndRefreshAlertFrequency(DAILY_EMAIL_ALERT);
        extendedAssert.assertStringEqual(newValue,DAILY_EMAIL_ALERT);

    }

    @Test(retryAnalyzer = Retry.class)
    public void test_02_emailUpdateFrequency() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        homePage.acceptCookies();
        loginPage.performLogin();
        resultsPage.clickZooplaProfileButton();
        resultsPage.clickAlertAndSearches();
        String newValue = alertsAndSearchPage.selectAndRefreshAlertFrequency(THREE_DAY_EMAIL_ALERT);
        extendedAssert.assertStringEqual(newValue,THREE_DAY_EMAIL_ALERT);
    }

    @Test(retryAnalyzer = Retry.class)
    public void test_03_housePrices1stResult() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        homePage.acceptCookies();
        homePage.clickZooplaLogo();
        homePage.tapOnHousePrices();
        homePage.searchForPostCode();
        homePage.clickHousePricesSearchButton();
        extendedAssert.assertTrue(resultsPage.getFirstElementText().contains(SEARCH_POSTCODE));
    }

    @Test(retryAnalyzer = Retry.class)
    public void test_04_housesWithGarage() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        homePage.acceptCookies();
        homePage.searchForLocation();
        homePage.clickOnSearchButton();
        resultsPage.clickOnFilterButton();
        resultsPage.typeKeyword();
        resultsPage.clickUpdateResultsButton();
        resultsPage.click1stResult();
        extendedAssert.assertTrue(resultsPage.isResultContainGarage());
    }

    @Test(retryAnalyzer = Retry.class)
    public void test_05_saveProperty15MinDrive() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        homePage.acceptCookies();
        loginPage.performLogin();
        homePage.clickToTravelTimeButton();
        travelTimeSearch.typeSearchLocation(TRAVEL_TIME_POSTCODE);
        travelTimeSearch.clickSearchButton();
        resultsPage.getTravelDurationText();
        resultsPage.clickSaveThisSearchButton();
        resultsPage.closeAlertSuccess();
        resultsPage.clickZooplaProfileButton();
        resultsPage.clickAlertAndSearches();
        extendedAssert.assertTrue(resultsPage.isPostCodeAppearsInSavedSearches());
    }

    @Test(retryAnalyzer = Retry.class)
    public void test_06_savedSearchesRetrieved() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        homePage.acceptCookies();
        loginPage.performLogin();
        resultsPage.clickZooplaProfileButton();
        resultsPage.clickAlertAndSearches();
        extendedAssert.assertTrue(alertsAndSearchPage.areSearchesCanBeRetrieved());
    }

}
