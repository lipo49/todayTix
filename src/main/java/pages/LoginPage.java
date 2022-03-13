package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import static utils.AppStrings.EMAIL;
import static utils.AppStrings.PASSWORD;

public class LoginPage extends BasePage {

    // Zoopla's Locators

    private By signInTopRightButton = By.cssSelector("a[data-testid='header-profile-sign-in']");
    private By emailTextField = By.id("input-email-address");
    private By passwordTextField = By.id("input-password");
    private By loginButton = By.xpath("//button[@data-testid='signin-button']");



    private WebDriverWait wait;

    // Constructor
    public LoginPage(Browser browser) {
        super(browser);
        wait = new WebDriverWait(browser.getDriver(), 10);
    }

    public void performLogin() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(signInTopRightButton, "Sign In button via Top Right corner");
        browser.type(emailTextField, EMAIL, "type email");
        browser.type(passwordTextField,PASSWORD, "type password");
        browser.click(loginButton, "Sign In button");
    }


}
