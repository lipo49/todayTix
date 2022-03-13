package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    private static WebDriver driver;

    public static WebDriver init(BrowserType browserType) throws Exception {
        switch (browserType){
            case CHROME:
                return initChrome();

            case FIRE_FOX:
                return initFireFox();

                default:
                    throw new Exception("Default");
        }
    }

    private static WebDriver initChrome(){
        System.setProperty("webdriver.chrome.driver","/Users/igorlipovetsky/Downloads/sampleproject/chromeDriver/chromedriver");

//        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

    private static WebDriver initFireFox(){
        System.setProperty("",""); //change the property for firefox
        driver = new FirefoxDriver();
        return driver;
    }
}
