package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;
import utils.Browser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static utils.AppStrings.EXPLICIT_WAIT;

public class CartPage extends BasePage {

    // Cart Page Locators

    private String addQuantityIdPrefix = "cart_quantity_up_";


    // Constructor
    public CartPage(Browser browser) {
        super(browser);
    }


    private WebDriverWait wait = new WebDriverWait(browser.getDriver(), EXPLICIT_WAIT);

    // Delete product according to dynamic element which built in productPage.getProductId()
    public void deleteProductFromCart(String productId) throws InterruptedException, IOException, ParserConfigurationException, SAXException {
        WebElement productDeleteLink = browser.getDriver().findElement(By.id(productId));
        browser.click(productDeleteLink,"Delete product");
    }

    // Verify dynamic element which built in productPage.getProductId() is not in Dom anymore
    public boolean productIdIsNotInDom(String productId){
        WebElement productDeleteLink = browser.getDriver().findElement(By.id(productId));
        wait.until(ExpectedConditions.invisibilityOf(productDeleteLink));
        return browser.getDriver().findElements(By.id(productId)).isEmpty();

    }

    // Adds quantity
    public void addQuantityForProductId(String productId) throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        String addQuantityId = addQuantityIdPrefix + productId;
        WebElement addQuantityWebElement = browser.getDriver().findElement(By.id(addQuantityId));
        browser.click(addQuantityWebElement,"Add Quantity for a product button");
    }

    public boolean verifyProductQuantityByProductId(String productId, Integer desiredQuantity) throws InterruptedException {
        Thread.sleep(5000);
        WebElement addedQuantityProduct = browser.getDriver().findElement(By.cssSelector("input[name=quantity_" + productId + "]"));
        String currentQuantity = addedQuantityProduct.getAttribute("value");
        return currentQuantity.equals(desiredQuantity.toString());
    }

    public boolean compareProductTotals() {
        // Create a List of WebElement of a general item in Cart
        List<WebElement> productsInCart = browser.getDriver().findElements(By.cssSelector("tr.cart_item"));

        // Run for enhanced loop to get actual Unit Price, Unit Quantity and calculated price of an item in cart
        for (WebElement product: productsInCart) {
            String unitPrice = product.findElement(By.cssSelector("td.cart_unit span.price span.price")).getText().substring(1); //remove $ sign
            String unitQuantity = product.findElement(By.cssSelector("td.cart_quantity input.cart_quantity_input")).getAttribute("value");
            String totalPrice = product.findElement(By.cssSelector("td.cart_total span.price")).getText().substring(1); //remove $ sign

            // Convert all Strings to Double in order to make a calculation of a total price
            Double unitPriceDouble = Double.parseDouble(unitPrice);
            Double unitQuantityDouble = Double.parseDouble(unitQuantity);
            Double totalPriceDouble = Double.parseDouble(totalPrice);

            // Verifying that 1 Unit price * Unit Quantity equals Total price
            if (!(unitPriceDouble*unitQuantityDouble == totalPriceDouble)) {
                return false;
            }
        }
        return true;
    }

    public String validateCartTotal() {
        // Creating a List of WebElement
        List<WebElement> productsInCart = browser.getDriver().findElements(By.cssSelector("tr.cart_item"));
        Double calculatedTotal = 0.0;

        // Running through all items in cart and updates the value of calculatedTotal (2 Faded Tshirts + 1 Printed Summer Dress)
        for (WebElement product: productsInCart) {
            String totalPrice = product.findElement(By.cssSelector("td.cart_total span.price")).getText().substring(1); //remove $ sign
            Double totalPriceDouble = Double.parseDouble(totalPrice);
            calculatedTotal += totalPriceDouble; // Should be $64 after all iterations
        }

        // Getting Total Shipping value and saving it to totalShipping double
        String totalShippingString = browser.getDriver().findElement(By.id("total_shipping")).getText().substring(1); //remove $ sign
        Double totalShipping = Double.parseDouble(totalShippingString);

        // Getting Tax value and saving it to totalTax double
        String totalTaxString = browser.getDriver().findElement(By.id("total_tax")).getText().substring(1); //remove $ sign
        Double totalTax = Double.parseDouble(totalTaxString);

        calculatedTotal += totalShipping + totalTax;

        // Getting Cart Total, including item total, shipping & tax total, which is $64
        String actualTotalString = browser.getDriver().findElement(By.id("total_price")).getText().substring(1); //remove $ sign
        Double actualTotal = Double.parseDouble(actualTotalString);

        // Verify actual total equals to calculatedTotal
        if (!actualTotal.equals(calculatedTotal))
            return "CALCULATED AND ACTUAL DONT MATCH - STOP";

        // After all calculations with double, I convert it back to String for the assertion in Tests clas
        return actualTotal.toString();


    }
















}
