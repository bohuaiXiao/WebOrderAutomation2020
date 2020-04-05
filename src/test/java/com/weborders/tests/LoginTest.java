package com.weborders.tests;

import com.weborders.pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.Test;


/**
 * @author:
 * @create:
 * @date:
 */
public class LoginTest extends AbstractBaseTest {

    @Test
    public void login(){
        extentTest = extentReports.createTest("Verify page logo");
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        assertEquals(loginPage.getPageLogoText(),"Web Orders");
        extentTest.pass("Logo verified");
    }


}
