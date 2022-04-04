package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.AppStrings.*;

public class LoginOrganizationPage extends BasePage {

    // Login Page Locators

    private By emailTextField = By.cssSelector("input[type='email']");
    private By loginButton = By.cssSelector("button[type='submit']");



    // Constructor
    public LoginOrganizationPage(Browser browser) {
        super(browser);
    }


    private WebDriverWait wait = new WebDriverWait(browser.getDriver(), EXPLICIT_WAIT);













}
