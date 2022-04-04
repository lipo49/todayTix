package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class Browser {

    private Report report;
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    // Constructor
    public Browser(WebDriver driver) {
        this.driver = driver;
        report = new Report();
    }

    public void quit() {
        report.info("Quitting Driver");
        driver.quit();
    }


    public void fail(String error) {
        Assert.fail(error);
    }

    // cover click with try catch end report system
    public void click(By by, String clickOn) throws IOException, SAXException, ParserConfigurationException, InterruptedException {
        clickOn = "<b style=\"margin: 0px 10px 0px 10px;color:blue;font-size:15px;\">" + clickOn + "</b>";
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(by));
            this.getDriver().findElement(by).click();
            report.info("Click on: " + clickOn);
        } catch (Exception e) {
            report.screenShot();
            report.fail(e.getMessage());
        }
    }

    public void click(WebElement webElement, String clickOn) throws IOException, SAXException, ParserConfigurationException, InterruptedException {
        clickOn = "<b style=\"margin: 0px 10px 0px 10px;color:blue;font-size:15px;\">" + clickOn + "</b>";
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 25);
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            report.info("Click on: " + clickOn);
        } catch (Exception e) {
            report.screenShot();
            report.fail(e.getMessage());
        }
    }


    // cover sendKeys with try catch end report system
    public void type(By by, String value, String description) throws ParserConfigurationException, IOException, SAXException {
        String s = "<b style=\"margin: 0px 5px 0px 5px;color:blue;font-size:15px;\">" + value + "</b>";
        try {
            this.getDriver().findElement(by).clear();
            report.info("Type:'" + s + "' to " + description + " field.");
            this.getDriver().findElement(by).sendKeys(value);
        } catch (Exception e) {
            report.screenShot();
            report.fail(e.getMessage());
        }
    }

    // cover getText with try catch
    public String getText(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return driver.findElement(by).getText();
        } catch (Exception e) {
            return "";
        }
    }


}
