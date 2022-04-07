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
    private ShowPage showPage;
    private TicketsPage ticketsPage;
    private ExtendedAssert extendedAssert;

    // Init all pages and reports
    @BeforeMethod
    public void initPages() {
        homePage = new HomePage(browser);
        showPage = new ShowPage(browser);
        ticketsPage = new TicketsPage(browser);
        extendedAssert = new ExtendedAssert();
    }

    // Perform Successful Login to for running the scenario
    @Test(retryAnalyzer = Retry.class, groups = "ChromeAndFireFox")
    public void test_01_scenarioTest() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        extendedAssert.softAssertTrue(homePage.performPositiveLogin()); // Asserts successful login process
        homePage.getLocationLocator(LOCATION); // determine the location dynamically, which can be changed through LOCATION in utils.AppStrings
        homePage.selectLocation(); // select the determined location
        homePage.getShowLocatorInDropDown(SHOW_SEARCH); // determine show title
        homePage.searchForAShow(); // search for that show and select it in AutoComplete accordingly
        extendedAssert.softStringContain(SHOW_SEARCH,showPage.getShowTitle()); // Asserts initial show title on show page
        showPage.clickGetTicketsButton(); // click Get Tickets button
        extendedAssert.softAssertTrue(ticketsPage.ticketsPageIndication()); // Assert calendar on screen via calendar element
        ticketsPage.clickCertainDateInCalendar(); // select certain date in calendar
        extendedAssert.softAssertTrue(ticketsPage.showTimeFormDisplayed()); // Assert show time & price appears at the bottom
        ticketsPage.clickShowPriceElement(); // click on time & price element
        extendedAssert.softAssertTrue(ticketsPage.selectSectionElementAppear()); // Asserts select section appears on screen
        ticketsPage.clickCertainSection(); // select certain section
        extendedAssert.softAssertTrue(ticketsPage.creditCardsAppear()); // Asserts 2 payment methods appears on screen
        ticketsPage.typePhoneNumber(); // select country code and type phone number
        ticketsPage.typeNameAndEmail(); // type name & email
        ticketsPage.clickCreditCard(); // click on Credit Card option
        extendedAssert.softAssertTrue(ticketsPage.creditCardFormAppears()); // Assert that Credit Card fields displayed and ready to be filled
        extendedAssert.softAssertAll();
    }

}
