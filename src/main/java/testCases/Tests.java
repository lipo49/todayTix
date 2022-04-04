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
    private ProductPage productPage;
    private CartPage cartPage;
    private ExtendedAssert extendedAssert;

    private String productIdFadedShortSleeve;
    private String productIdEveningDress;

    // Init all pages and reports
    @BeforeMethod
    public void initPages() {
        homePage = new HomePage(browser);
        productPage = new ProductPage(browser);
        cartPage = new CartPage(browser);
        extendedAssert = new ExtendedAssert();
    }


    // Add Faded Short Sleeve T-Shirt to the cart
    @Test(retryAnalyzer = Retry.class, groups = "ChromeAndFireFox")
    public void test_01_addFadedShortSleeveShirtToCart() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        homePage.enterSearchTextFieldValues(FADED_SHORT_SLEEVE_TSHIRT);
        extendedAssert.softStringContain(FADED_SHORT_SLEEVE_TSHIRT, productPage.getProductTitle(), productPage.getProductDescriptions());
        productPage.selectBlueColor();
        productPage.sizeSelect(MEDIUM);
        productPage.getProductPrice();
        // Saving global value to use productIdFadedShortSleeve in test No.5
        productIdFadedShortSleeve = productPage.getProductId();
        productPage.clickAddToCartButton();
        extendedAssert.softAssertEquals(productPage.getProductPriceInCard(), productPage.getProductPrice());
        productPage.clickContinueShoppingButton();
        extendedAssert.softAssertAll();
    }

    // Add Evening Dress to the cart
    @Test(retryAnalyzer = Retry.class, groups = "ChromeAndFireFox")
    public void test_02_addEveningDressToCart() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        homePage.enterSearchTextFieldValues(EVENING_DRESS);
        extendedAssert.softStringContain(EVENING_DRESS, productPage.getProductTitle(), productPage.getProductDescriptions());
        productPage.selectBeigeColor();
        productPage.sizeSelect(SMALL);
        productPage.getProductPrice();
        // Saving global value to use productIdEveningDress in test No.4
        productIdEveningDress = productPage.getProductId();
        productPage.clickAddToCartButton();
        extendedAssert.softAssertEquals(productPage.getProductPriceInCard(), productPage.getProductPrice());
        productPage.clickContinueShoppingButton();
        extendedAssert.softAssertAll();
    }

    // Add Printed Summer Dress to the cart
    @Test(retryAnalyzer = Retry.class, groups = "ChromeAndFireFox")
    public void test_03_addPrintedSummerDressToCart() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        homePage.enterSearchTextFieldValues(PRINTED_SUMMER_DRESS);
        extendedAssert.softStringContain(PRINTED_SUMMER_DRESS, productPage.getProductTitle(), productPage.getProductDescriptions());
        extendedAssert.softAssertAll();
        productPage.selectOrangeColor();
        productPage.sizeSelect(MEDIUM);
        productPage.getProductPrice();
        productPage.clickAddToCartButton();
        extendedAssert.softAssertEquals(productPage.getProductPriceInCard(), productPage.getProductPrice());
        productPage.clickContinueShoppingButton();
        extendedAssert.softAssertAll();
    }

    // Delete Evening Dress item from cart
    @Test(retryAnalyzer = Retry.class, groups = "ChromeAndFireFox")
    public void test_04_deleteEveningDress() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        homePage.clickOnCart();
        cartPage.deleteProductFromCart(productIdEveningDress);
        extendedAssert.softAssertTrue(cartPage.productIdIsNotInDom(productIdEveningDress));
        extendedAssert.softAssertAll();
    }

    // Add 1 item to Faded Short Sleeve T-Shirt
    @Test(retryAnalyzer = Retry.class, groups = "ChromeAndFireFox")
    public void test_05_addQuantityForFadedShortSleeveTshirt() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        homePage.clickOnCart();
        cartPage.addQuantityForProductId(productIdFadedShortSleeve);
        extendedAssert.softAssertTrue(cartPage.verifyProductQuantityByProductId(productIdFadedShortSleeve, 2));
        extendedAssert.softAssertAll();

    }

    // Verify total price for each item after quantity modification
    @Test(retryAnalyzer = Retry.class, groups = "ChromeAndFireFox")
    public void test_06_verifyTotalPriceForEachLineInCart() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        homePage.clickOnCart();
        extendedAssert.softAssertTrue(cartPage.compareProductTotals());
        extendedAssert.softAssertAll();

    }

    // Test fails coz $65.53 is not including the discount of 5% for Printed Summer Dance
    @Test(retryAnalyzer = Retry.class, groups = "ChromeAndFireFox")
    public void test_07_verifyCartTotalPrice() throws IOException, ParserConfigurationException, InterruptedException, SAXException {
        homePage.clickOnCart();
        extendedAssert.softAssertEquals(cartPage.validateCartTotal(), "65.53");
        extendedAssert.softAssertAll();

    }
}
