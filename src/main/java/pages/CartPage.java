package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static utils.AppStrings.*;

public class ProductPage extends BasePage {

    // Login Page Locators

    private By emailTextField = By.cssSelector("input[type='email']");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By orangeColor = By.id("color_13");
    private By blueColor = By.id("color_14");
    private By beigeColor = By.id("color_7");
    private By sizeDropDown = By.id("group_1");
    private By productDescription = By.id("short_description_content");
//    private By productTitle = By.cssSelector("div > h1[itemprop='name']");
    private By productTitle = By.tagName("h1");
//    private By productTitle = RelativeLocator.with(By.tagName("h1")).above(By.id("product_reference"));
//    private By addToCartButton = By.id("add_to_cart");
    private By productPrice = By.id("our_price_display");
    private By productPriceTwo = By.cssSelector("span[itemprop='price']");
    private By productPriceInCard = By.id("layer_cart_product_price");
    private By continueShoppingButton = By.cssSelector("span[title='Continue shopping']");

    @FindBy(css = "p[id='add_to_cart'] > button[name='Submit']")
    WebElement addToCartButton;



    // Constructor
    public ProductPage(Browser browser) {
        super(browser);
    }


    private WebDriverWait wait = new WebDriverWait(browser.getDriver(), EXPLICIT_WAIT);


    public void sizeSelect(String productSize) throws InterruptedException {
        WebElement selectBox = browser.getDriver().findElement(sizeDropDown);
        Select productSizeDropDown = new Select(selectBox);
        productSizeDropDown.selectByVisibleText(productSize);
    }

    public String getProductDescriptions(){
        String productDesc = browser.getText(productDescription).toLowerCase();
        return productDesc;
    }

    public String getProductTitle(){
        String productTitl = browser.getText(productTitle).toLowerCase();
        return productTitl;
    }


   public void selectBlueColor() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(blueColor,"Blue color");
   }

    public void selectOrangeColor() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(orangeColor,"Orange color");
    }

    public void selectBeigeColor() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(beigeColor,"Beige color");
    }

    // Support also when Add To Cart Button is visible
    public void clickAddToCartButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        JavascriptExecutor jse = ((JavascriptExecutor) browser.getDriver());
        jse.executeScript("arguments[0].scrollIntoView(true);", browser.getDriver().findElement(By.id("add_to_cart")));
        browser.click(browser.getDriver().findElement(By.id("add_to_cart")),"Add To Cart button");
    }

    public String getProductPrice(){
        System.out.println(browser.getText(productPrice));
        System.out.println(browser.getText(productPriceTwo));
        return browser.getText(productPrice);
    }

    public String getProductPriceInCard(){
        System.out.println(browser.getText(productPriceInCard));
        return browser.getText(productPriceInCard);
    }

    public void clickContinueShoppingButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(continueShoppingButton,"Continue Shopping");
    }

    public void clickCheckoutButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        browser.click(checkoutButton,"Continue Shopping");
    }
















}
