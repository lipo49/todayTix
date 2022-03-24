package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.AppStrings.*;

public class LoginPage extends BasePage {

    // Login Page Locators

    private By backButton = By.cssSelector("div > a[class='icon-back']");
    private By hudlLogo = By.cssSelector("form > div > a[class='icon-logomark']");
    private By signUpButton = By.className("sign-up-trial");
    private By emailTitle = By.cssSelector("form > div > div > label[for='email']");
    private By emailTextField = By.id("email");
    private By passwordTitle = By.cssSelector("form > div > div > label[for='password']");
    private By passwordTextField = By.id("password");
    private By loginError = By.className("login-error-container");
    private By loginButton = By.id("logIn");
    private By rememberMeTitle = By.className("form__label--custom");
    private By rememberMeCheckbox = By.id("remember-me");
    private By needHelpButton = By.id("forgot-password-link");
    private By loginWithOrganization = By.id("logInWithOrganization");



    // Constructor
    public LoginPage(Browser browser) {
        super(browser);
    }

    // Login workflow

    private boolean isElementExist(By element, String text) throws Exception {
        return browser.isElementVisible(element,text);
    }
    
    public boolean isAllNecessaryElementsDisplayed() throws Exception {
        elementExist = true;
        isElementExist(backButton,"Login Back button");
        isElementExist(hudlLogo,"Hudl Logo");
        isElementExist(signUpButton,"Sign Up button");
        isElementExist(emailTitle,"Email title");
        isElementExist(emailTextField,"Email text field");
        isElementExist(passwordTitle,"Password title");
        isElementExist(passwordTextField,"Password field");
        isElementExist(loginButton,"Perform Login button");
        isElementExist(rememberMeTitle,"Remember Me title");
        isElementExist(rememberMeCheckbox,"Remember Me Checkbox");
        isElementExist(needHelpButton,"Need Help button");
        isElementExist(loginWithOrganization,"Login With Organization");
        browser.click(loginButton, "Sign In button");
        isElementExist(loginError,"Email text field");
        return elementExist;

    }
    public void positivePerformLogin() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.type(emailTextField, EMAIL, "type email");
        browser.type(passwordTextField, RIGHT_PASSWORD, "type password");
        browser.click(loginButton, "Sign In button");
    }

    public void wrongPassPerformLogin() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.type(emailTextField, EMAIL, "type email");
        browser.type(passwordTextField, WRONG_PASSWORD, "type password");
        browser.click(loginButton, "Sign In button");
    }

    public void wrongEmailPerformLogin() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.type(emailTextField, EMAIL, "type email");
        browser.type(passwordTextField, WRONG_PASSWORD, "type password");
        browser.click(loginButton, "Sign In button");
    }

    public void wrongEmailWrongPassPerformLogin() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.type(emailTextField, EMAIL, "type email");
        browser.type(passwordTextField, WRONG_PASSWORD, "type password");
        browser.click(loginButton, "Sign In button");
    }




}
