package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;


public class WebDriverFactory {
    private static WebDriver driver;

    public static WebDriver init(BrowserType browserType) throws Exception {
        switch (browserType){
            case CHROME:
                return initChrome();

            case FIREFOX:
                return initFireFox();

                default:
                    throw new Exception("Default");
        }
    }

    private static WebDriver initChrome(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-logging");
        options.addArguments("--disable-extensions");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        return driver;
    }



    private static WebDriver initFireFox(){
        System.setProperty("webdriver.gecko.driver","drivers/geckodriver");
        System.setProperty("applicationCacheEnabled","true");
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"drivers/firefoxLog.txt");
        driver = new FirefoxDriver();
        return driver;
    }


}
