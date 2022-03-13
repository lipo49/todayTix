package utils;


import org.testng.Assert;
import org.xml.sax.SAXException;
import testCases.BaseTest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ExtendedAssert extends BaseTest {

    private Report report = new Report();


    // cover assert function for clear report system
    public void assertTrue(boolean condition) throws ParserConfigurationException, SAXException, IOException, InterruptedException {
        try {
            Assert.assertTrue(condition);
            report.pass("Assertion pass");
        } catch (Exception e) {
            report.fail("Test FAIL: " + e.getMessage());
            report.screenShot();
            browser.fail(e.getMessage());
        } catch (AssertionError error) {
            report.fail("Assert FAIL: " + error.getMessage());
            report.screenShot();
            browser.fail(error.getMessage());
        }
    }

    // cover assert function for clear report system
    public void assertStringEqual(String expected, String actual) throws InterruptedException, IOException, SAXException, ParserConfigurationException {

        String expectedHTML = "<b style=\"margin: 0px 10px 0px 10px;color:blue;font-size:15px;text-decoration: underline;\">" + expected + "</b>";
        String actualHTML = "<b style=\"margin: 0px 10px 0px 10px;color:blue;font-size:15px;text-decoration: underline;\">" + actual + "</b>";
        report.info("Comparing strings Expected: " + expectedHTML + " Actual: " + actualHTML);
        actual = actual.replace("\n", "").replace("\r", "");
        expected = expected.replace("\n", "").replace("\r", "");

        try {
            Assert.assertEquals(actual, expected);
            report.pass("The text is correct with value: " + actualHTML);
            report.screenShot();
        } catch (Exception e) {
            report.fail(e.getMessage());
            System.out.println(e.getMessage());
            report.screenShot();
            browser.fail(e.getMessage());
        } catch (AssertionError error) {
            report.fail("Compare FAIL: " + error.getMessage());
            System.out.println(error.getMessage());
            report.screenShot();
            browser.fail(error.getMessage());
        }
    }
}
