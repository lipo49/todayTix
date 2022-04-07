package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.AppStrings.EXPLICIT_WAIT;
import static utils.AppStrings.PHONE_COUNTRY_CODE;

public class TicketsPage extends BasePage {

    // Cart Page Locators
    private By ticketsPageIndication = By.className("tt-cal-week");
    private By dateElement = By.cssSelector(".tt-cal-week:nth-of-type(4) > .tt-cal-day");
    private By showTimePrice = By.id("showtime-0-price");
    private By selectSectionIndication = By.cssSelector("div[name='SHOW_DETAILS_SELECT_SECTION_JUMP_REF']");
    private By selectSectionPrice = By.id("section-0-price");
    private By googlePayElement = By.xpath("//button//span[text()='Google Pay']");
    private By creditCardElement = By.xpath("//button//span[text()='Credit Card']");
    private By nameTextField = By.cssSelector("input[type='text']");
    private By emailTextField = By.cssSelector("input[type='email']");
    private By countryCodeElement = By.cssSelector("select[type='tt-country-code-select']");
    private By phoneNumberTextField = By.cssSelector("input[name='pickup.phone']");
    private By creditCardForm = By.cssSelector("input[name='payment.creditCard']");



    // Constructor
    public TicketsPage(Browser browser) {
        super(browser);
    }


    private WebDriverWait wait = new WebDriverWait(browser.getDriver(), EXPLICIT_WAIT);

    public boolean ticketsPageIndication(){
        wait.until(ExpectedConditions.elementToBeClickable(ticketsPageIndication));
        if(!browser.getDriver().findElement(ticketsPageIndication).isDisplayed()){
            return false;
        }
        return true;
    }

    public void clickCertainDateInCalendar() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(dateElement,"Select date");
    }

    public boolean showTimeFormDisplayed(){
        wait.until(ExpectedConditions.elementToBeClickable(showTimePrice));
        if(!browser.getDriver().findElement(showTimePrice).isDisplayed()){
            return false;
        }
        return true;
    }

    public void clickShowPriceElement() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(showTimePrice,"Select Show Price element");
    }

    public boolean selectSectionElementAppear(){
        wait.until(ExpectedConditions.elementToBeClickable(selectSectionIndication));
        if(!browser.getDriver().findElement(selectSectionIndication).isDisplayed()){
            return false;
        }
        return true;
    }

    public void clickCertainSection() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(selectSectionPrice,"Select Section");
    }

    public boolean creditCardsAppear(){
        wait.until(ExpectedConditions.elementToBeClickable(creditCardElement));
        if((!browser.getDriver().findElement(creditCardElement).isDisplayed()) || (!browser.getDriver().findElement(googlePayElement).isDisplayed()) ){
            return false;
        }
        return true;
    }

    public void typeNameAndEmail() throws ParserConfigurationException, IOException, SAXException {
        browser.type(nameTextField,"Test User","Full Name");
        browser.type(emailTextField,"randomEmail@gmail.com","Email address");
    }

    public void typePhoneNumber() throws ParserConfigurationException, IOException, SAXException {
        wait.until(ExpectedConditions.elementToBeClickable(countryCodeElement));
        WebElement selectBox = browser.getDriver().findElement(countryCodeElement);
        Select countryCodeDropDown = new Select(selectBox);
        countryCodeDropDown.selectByValue(PHONE_COUNTRY_CODE);
        browser.type(phoneNumberTextField,"7412345678","Phone number");
    }

    public void clickCreditCard() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(creditCardElement,"Credit Card payment");
    }

    public boolean creditCardFormAppears(){
        wait.until(ExpectedConditions.elementToBeClickable(creditCardForm));
        if((!browser.getDriver().findElement(creditCardForm).isDisplayed())){
            return false;
        }
        return true;
    }

}
