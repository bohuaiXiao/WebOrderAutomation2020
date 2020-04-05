package com.weborders.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.weborders.utilities.BrowserUtils;
import com.weborders.utilities.ConfigurationReader;
import com.weborders.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

/**
 * @author:
 * @create:
 * @date:
 *
 * It meant to be extended
 */
public abstract class AbstractBasePage {
    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver,20);

    protected static ExtentReports extentReports;
    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentTest extentTest;

    public AbstractBasePage(){
        PageFactory.initElements(driver,this);
    }
    @BeforeMethod
    public void setup(){
        //open browser url
        driver.get(ConfigurationReader.getProperty("url"));
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void teardown(ITestResult testResult){
        if (testResult.getStatus()==ITestResult.FAILURE){
            String screenshotLocation = BrowserUtils.getScreenshot(testResult.getName());
        }
        Driver.closeDriver();
    }
    @BeforeTest
    public void beforeTest(){
    extentReports = new ExtentReports();
    String reportPath = "";
    if (System.getProperty("os.name").toLowerCase().contains("win")){
        reportPath = System.getProperty("user.dir")+"\\test-output\\report.html";
    }else {
        reportPath = System.getProperty("user.dir")+"/test-output/report.html";
    }
    extentHtmlReporter = new ExtentHtmlReporter(reportPath);
    extentReports.attachReporter(extentHtmlReporter);
    extentHtmlReporter.config().setReportName("WebOrders Automation");
    }

    @AfterTest
    public void afterTest() {
        extentReports.flush();
    }

    }

