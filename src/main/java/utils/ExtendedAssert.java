package utils;


import org.testng.asserts.SoftAssert;
import org.xml.sax.SAXException;
import testCases.BaseTest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ExtendedAssert extends BaseTest {

    private Report report = new Report();
    public SoftAssert softAssert = new SoftAssert();



    public void softAssertTrue(boolean condition)  {
        String assertionPassed = "<b style=\"margin: 0px 10px 0px 10px;color:blue;font-size:15px;text-decoration: underline;\">" + "Assertion Passed!" + "</b>";
        try {
            softAssert.assertTrue(condition);
            report.info(assertionPassed);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void softStringContain(String expected, String actual)  {
        expected = expected.toLowerCase();
        actual = actual.toLowerCase();
        try {
            if ((actual.toLowerCase().contains(expected))) {
                softAssertTrue(true);
            }else {
                softAssertTrue(false);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void softAssertAll() throws ParserConfigurationException, SAXException, IOException {
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            softAssert = new SoftAssert();
            report.fail("Test FAIL: " + e.getMessage());
            report.screenShot();
            browser.fail(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
