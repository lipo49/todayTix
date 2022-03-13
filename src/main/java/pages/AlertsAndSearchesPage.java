package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Browser;

public class AlertsAndSearchesPage extends BasePage {




    // Zoopla's Locators

    private By dailyEmailsRadioButton = By.id("frequency_1");
    private By saveSearchButton = By.className(".btn.btn-primary");
    private By alertFrequencyDropdown = By.className("js-myaccount-alert-frequency");
    private By alertsResults = By.id("tab-residential-to-rent");



    private WebDriverWait wait;

    // Constructor
    public AlertsAndSearchesPage(Browser browser) {
        super(browser);
        wait = new WebDriverWait(browser.getDriver(), 10);
    }

    public String selectAndRefreshAlertFrequency(String value) throws InterruptedException
    {
        WebElement selectBox = browser.getDriver().findElement(alertFrequencyDropdown);
        Select alertDropDown = new Select(selectBox);
        alertDropDown.selectByValue(value);
        String formID = selectBox.getAttribute("data-form-id");

        browser.getDriver().navigate().refresh();

        selectBox = browser.getDriver().findElement(By.xpath("//select[@data-form-id='" + formID + "']"));
        alertDropDown = new Select(selectBox);
        String changedValue = alertDropDown.getFirstSelectedOption().getAttribute("value");

        return changedValue;

    }

    public boolean areSearchesCanBeRetrieved(){
        WebElement results = browser.getDriver().findElement(alertsResults);
        //TODO can't save this with By annotation. No IsDisplayed elements
        if (results.isDisplayed()){
            return true;
        } else {
            return false;
        }
    }



}
