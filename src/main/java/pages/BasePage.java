package pages;
import testCases.BaseTest;
import utils.Browser;
import utils.Report;


public abstract class BasePage extends BaseTest {

    public Browser browser;
    public Report report = new Report();

    public BasePage(Browser browser) {
        this.browser = browser;
    }


}
