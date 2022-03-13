package testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import utils.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import static utils.AppStrings.URL;

public class BaseTest  {

    public static String timeStamp = new SimpleDateFormat("dd-MM-yyy_HH-mm-ss").format(Calendar.getInstance().getTime());
    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentHtmlReporter htmlReporter;
    private static Report report = new Report();
    public static Browser browser;
    public static String path;


    // function that getting data from xml file
    public static String getData(String nodeName) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File("params.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    // report inits
    @BeforeSuite()
    public static void startSuite() throws Exception {
        path = getData("ReportFilePath") + "/" + timeStamp + "/" + "report" + ".html";
        htmlReporter = new ExtentHtmlReporter(path);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    @AfterSuite
    public void shutDown() {
        finalizeExtentReport();
        browser.quit();
    }

    @BeforeTest
    public static void start() throws Exception {

    }

    @AfterTest
    public void close() {
        finalizeExtentReport();

    }

    @BeforeMethod
    public void doBeforeTest(Method method) {
        try {
            browser = new Browser(WebDriverFactory.init(BrowserType.CHROME));
            String testName = testNameChanging(method.getName());
            initReportTest(testName);
        } catch (Exception e) {
            browser.fail("Fail: " + e.getMessage());
        }
        browser.getDriver().get(URL);
        browser.getDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void doAfterTest() {
        try {
            extent.flush();
            browser.getDriver().close();
        } catch (Exception e) {
            report.fail("Fail: " + e.getMessage());
            browser.fail("Fail: " + e.getMessage());
        }
    }

    public static void initReportTest(String testDescription) {
        try {
            test = extent.createTest(testDescription);
        } catch (Exception e) {
            System.out.println("Test not created");
        }
    }

    public static void finalizeExtentReport() {
        extent.flush();
    }

    // Getting random number for image file name
    public static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(999999) + 1000;
    }

    // changing the test name for clear report
    private String testNameChanging(String oldName) {
        String s = "";
        char ch;
        char[] chars = oldName.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        s += chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                ch = Character.toLowerCase(chars[i]);
                s += " ";
                s += ch;
            } else {
                s += chars[i];
            }
        }
        System.out.println("*********Test name is: " + s);
        return s;
    }
}
