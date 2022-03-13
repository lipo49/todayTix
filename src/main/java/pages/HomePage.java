package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.AppStrings.*;

public class HomePage extends BasePage {



    // Zoopla's Locators

    private By zooplaLogo = By.xpath("//a[@data-testid='header-logo']");
    private By acceptCoockies = By.id("save");
    private By searchArea = By.id("header-location");
    private By searchAreaDropDownSelect = By.xpath("//button[@data-testid='London Fields, London']");
    private By bedrooms = By.id("AnyBeds_testId");
    private By minBeds = By.id("beds_min");
    private By maxBeds = By.id("beds_max");
    private By priceRange = By.xpath("//button[@title='Any price']");
    private By priceMin = By.id("price_min_monthly");
    private By priceMax = By.id("price_max_monthly");
    private By toRentButton = By.xpath("//a[@data-testid='to-rent-tab-desktop-homepage']");
    private By housePricesButton = By.xpath("//a[@data-testid='house-prices-tab-desktop-homepage']");
    private By saleOrToRentSearchButton = By.xpath("//button[@data-testid='search-button']");
    private By housesPriceSearchButton = By.cssSelector("button[data-testid='house-prices-search-button']");
    private By travelTimeSubMenu1 = By.xpath("//a[@href='/for-sale/'][@data-testid='header-primarynav-link']");
    private By forSaleTravelTime = By.cssSelector("li[data-testid='header-primarynav-mn-buy'] div[data-testid='header-primarynav-items'] a[href='/travel-time/']");




    private WebDriverWait wait = new WebDriverWait(browser.getDriver(),EXPLICIT_WAIT);
    Actions action = new Actions(browser.getDriver());

    // Constructor
    public HomePage(Browser browser) {
        super(browser);
    }

    public void acceptCookies() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.getDriver().switchTo().frame(IFRAME_ACCEPT_COOKIES);
        browser.click(acceptCoockies, "Accept all cookies");
        browser.getDriver().switchTo().parentFrame();
    }

    public void clickZooplaLogo() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(zooplaLogo, "Zoopla Logo");
    }

    public void tapOnToRent() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(toRentButton, "To Rent button");
    }

    public void tapOnHousePrices() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(housePricesButton,"House prices button");
    }

    public void searchForLocation() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.type(searchArea, TO_RENT_SEARCH, "search for London");

    }

    public void searchForPostCode() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.type(searchArea, SEARCH_POSTCODE, "search for London");

    }

    public void dropDownSelect() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(searchAreaDropDownSelect, "London");
    }

    public void selectNumberOfBeds() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(bedrooms, "Bedrooms");
//        browser.click(minBeds, "Min Beds");
        Select bedsNumberMin = new Select(browser.getDriver().findElement(minBeds));
        bedsNumberMin.selectByVisibleText(NUMBER_OF_ROOMS_MIN);

        Select bedsNumberMax = new Select(browser.getDriver().findElement(maxBeds));
        bedsNumberMax.selectByVisibleText(NUMBER_OF_ROOMS_MIN);
    }

    public void selectPrice() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(priceRange, "Price Range");
        Select selectPriceMin = new Select(browser.getDriver().findElement(priceMin));
        selectPriceMin.selectByVisibleText(RENT_MIN_PRICE);

        Select selectPriceMax = new Select(browser.getDriver().findElement(priceMax));
        selectPriceMax.selectByVisibleText(RENT_MAX_PRICE);
    }

    public void clickOnSearchButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(saleOrToRentSearchButton,"Search Button");
    }

    public void clickHousePricesSearchButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException
    {
        browser.click(housesPriceSearchButton,"Search Button");
    }


    public void clickToTravelTimeButton() throws ParserConfigurationException, IOException, InterruptedException, SAXException
    {
        wait.until(ExpectedConditions.elementToBeClickable(travelTimeSubMenu1));
        action.moveToElement(browser.getDriver().findElement(travelTimeSubMenu1)).perform();
        wait.until(ExpectedConditions.elementToBeClickable(forSaleTravelTime));
        browser.click(forSaleTravelTime,"Travel Time button");
    }




}
