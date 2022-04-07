package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.AppStrings.*;

public class HomePage extends BasePage {


    // Homepage Locators  //

            // Login Locators //
    private By loginButton = By.id("navBarLoginButton");
    private By emailSelection = By.cssSelector("img[alt='Email']");
    private By emailTextField = By.id("sign-in-email-field");
    private By passwordTextField = By.id("sign-in-password-field");
    private By submitLogin = By.id("sign-in-submit");
    private By connectedIndication = By.className("jss53");
    private By locationsButton = By.id("locations-select");
    private By locationsContainer = By.cssSelector("ul[role='listbox']");
    private By searchTextField = By.id("topBarSearch");



    // Constructor
    public HomePage(Browser browser) {
        super(browser);
    }

    private WebDriverWait wait = new WebDriverWait(browser.getDriver(), EXPLICIT_WAIT);

    private String locationString;
    private String showNameString;

    public boolean performPositiveLogin() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(loginButton,"Login button");
        browser.click(emailSelection,"Connect with Email");
        browser.type(emailTextField,EMAIL,"Email address");
        browser.type(passwordTextField,PASSWORD,"Password");
        browser.click(submitLogin,"Login button");
        String userConnected = browser.getText(connectedIndication);
        if(!userConnected.contains(CONNECTED_INDICATION)){
            return false;
        }
        return true;
    }


    // Getting the selector String concated by 3 different parts, which will be passed to the test
    public String getLocationLocator(String location) {
        String locationLocator = LOCATION_PREFIX + location + LOCATION_SUFFIX;
        System.out.println(locationString);
        locationString = locationLocator;
        return locationLocator;
    }

    public void selectLocation() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(locationsButton,"Locations button");
        wait.until(ExpectedConditions.elementToBeClickable(locationsContainer));
        browser.getDriver().findElement(By.xpath(locationString)).click();
    }

    public String getShowLocatorInDropDown(String showName){
        String showLocator = SHOW_NAME_PREFIX + showName + SHOW_NAME_SUFFIX;
        System.out.println(showLocator);
        showNameString = showLocator;
        return showLocator;
    }

    public void searchForAShow() throws ParserConfigurationException, IOException, SAXException {
        browser.type(searchTextField,SHOW_SEARCH,"search for " + SHOW_SEARCH + " show");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(showNameString)));
        browser.getDriver().findElement(By.xpath(showNameString)).click();
    }








}
