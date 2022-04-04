package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import testCases.BaseTest;

    // Retry functionality which lets the test run 3 more times after initial failure
public class Retry extends BaseTest implements IRetryAnalyzer
{
    int counter = 0;
    int retryLimit = 0; // this Int represents the number of retries every test will do until it fails

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (counter < retryLimit){
            counter++;
            return true;
        }
        return false;
    }
}
