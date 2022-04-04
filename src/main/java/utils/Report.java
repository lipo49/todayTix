package utils;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;
import testCases.BaseTest;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


// Extent reports class cover
public class Report extends BaseTest {

    public Report() {
    }

    public void info(String description) {
        test.log(Status.INFO, description);
    }

    public void pass(String description) {
        test.log(Status.PASS, description);
    }

    public void fail(String description) {
        test.log(Status.FAIL, description);
    }


    public void screenShot() throws ParserConfigurationException, SAXException, IOException {
        test.log(Status.INFO, "Screen shot attached: ");
        test.addScreenCaptureFromPath(getScreenshot(browser.getDriver()));
    }

    public static String getScreenshot(WebDriver driver) throws IOException, ParserConfigurationException, SAXException {

        String fileName = "screenshot_" + getRandomNumber() + ".png";
        String sSpath = getData("ReportFilePath") + "/" + timeStamp + "/" + fileName;
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = sSpath;
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return fileName;
    }
}
