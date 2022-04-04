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

    private By orangeColor = By.id("color_13");
    private By blueColor = By.id("color_14");
    private By beigeColor = By.id("color_7");
    private By sizeDropDown = By.id("group_1");
    private By productPrice = By.id("our_price_display");
    private By productPriceInCard = By.id("layer_cart_product_price");
    private By continueShoppingButton = By.cssSelector("span[title='Continue shopping']");
    private By productPageProductId = By.id("product_page_product_id");
    private By idCombination = By.id("idCombination");
    private By addToCartButton = By.id("add_to_cart");
    private By productDescription = By.id("short_description_content");
    private By productTitle = By.tagName("h1");


    // Constructor
    public ProductPage(Browser browser) {
        super(browser);
    }


    private WebDriverWait wait = new WebDriverWait(browser.getDriver(), EXPLICIT_WAIT);

    public String getProductDescriptions() {
        String productDesc = browser.getText(productDescription).toLowerCase();
        return productDesc;
    }

    public String getProductTitle() {
        String productTitl = browser.getText(productTitle).toLowerCase();
        return productTitl;
    }

    public void sizeSelect(String productSize) {
        WebElement selectBox = browser.getDriver().findElement(sizeDropDown);
        Select productSizeDropDown = new Select(selectBox);
        productSizeDropDown.selectByVisibleText(productSize);
    }


    public void selectBlueColor() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        wait.until(ExpectedConditions.elementToBeClickable(blueColor));
        browser.click(blueColor, "Blue color");
    }

    public void selectOrangeColor() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        wait.until(ExpectedConditions.elementToBeClickable(orangeColor));
        browser.click(orangeColor, "Orange color");
    }

    public void selectBeigeColor() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        wait.until(ExpectedConditions.elementToBeClickable(beigeColor));
        browser.click(beigeColor, "Beige color");
    }

    // Added support also when Add To Cart Button is not visible
    public void clickAddToCartButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        JavascriptExecutor jse = ((JavascriptExecutor) browser.getDriver()); //
        jse.executeScript("arguments[0].scrollIntoView(true);", browser.getDriver().findElement(addToCartButton));
        browser.click(browser.getDriver().findElement(addToCartButton), "Add To Cart button");
    }

    public String getProductPrice() {
        wait.until(ExpectedConditions.elementToBeClickable(productPrice));
        return browser.getText(productPrice);
    }

    public String getProductPriceInCard() {
        wait.until(ExpectedConditions.elementToBeClickable(productPriceInCard));
        return browser.getText(productPriceInCard);
    }

    public void clickContinueShoppingButton() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        browser.click(continueShoppingButton, "Continue Shopping");
    }


    // Getting the selector String concated by 2 ids (combination of Size and Color) from productPage + "_0_0" string, which will be passed to the test
    public String getProductId() {
        String productIdString = browser.getDriver().findElement(productPageProductId).getAttribute("value")
                + "_" + browser.getDriver().findElement(idCombination).getAttribute("value")
                + "_0_0";
        return productIdString;
    }


}
