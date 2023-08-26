package testClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageClasses.AdminPage;
import pageClasses.DirectoryPage;
import pageClasses.OrangeHRMLogin;

import java.io.IOException;

import static java.time.Duration.ofSeconds;

public class FinalProject {

    static WebDriver driver;
    static String baseurl;

    static OrangeHRMLogin orangeHRMLogin;
    static AdminPage adminpage;
    static DirectoryPage directoryPage;
    ExtentReports extent;
    ExtentTest test;


    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        baseurl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        orangeHRMLogin =new OrangeHRMLogin(driver);
        adminpage = new AdminPage(driver);
        directoryPage = new DirectoryPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(5));
        driver.get(baseurl);

    }

    @Test
    @Parameters({"userRole","status","Employee_name","username","password","confirmpassword"})
    public void test1(String userRole,String status,String Employee_name,String username,String password,String confirmpassword) throws IOException, InterruptedException {

        //Reporting
        extent = new ExtentReports();
        extent.setSystemInfo("Project Name", "Final Project Automation");
        extent.setSystemInfo("Website Automated", "Orange HRM");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReports/ExtentReportTest1.html");
        extent.attachReporter(sparkReporter);

        test = extent.createTest("Verify logged in User is able to add new \"Admin\" user");
        loginApplication();
        adminpage.clickAdminSection();
        boolean adminPageDisplay = adminpage.verifyAdminPageDisplayed();
        boolean expectedAdminPageDisplay = true;
        Assert.assertEquals(adminPageDisplay,expectedAdminPageDisplay);
        test.log(Status.INFO, "Admin Page is Displayed");
        orangeHRMLogin.attachScreenshot("Admin Page");
        adminpage.clickAddButton();
        adminpage.selectUserRole(userRole);
        adminpage.enterEmployee(Employee_name);
        adminpage.selectStatus(status);
        adminpage.enterUsername(username + generateRandomString(5));
        adminpage.enterPassword(password);
        adminpage.enterConfirmPassword(confirmpassword);
        orangeHRMLogin.attachScreenshot("Add user details page");
        adminpage.clickSaveButton();

        boolean VerificationMessage = adminpage.verifyVerificationMessageDisplayed();
        boolean expectedMessage = true;
        Assert.assertEquals(VerificationMessage,expectedMessage);
        test.log(Status.INFO, "User details saved message is displayed");
        Thread.sleep(1000);
        JavascriptExecutor scrolldown = (JavascriptExecutor)driver;
        scrolldown.executeScript("window.scrollBy(0,300)","");
        orangeHRMLogin.attachScreenshot("Save user details page");

        logoutApplication();

    }

    @Test
    @Parameters({"employeename"})
    public void test2(String employeename) throws IOException, InterruptedException {


        //Reporting
        extent = new ExtentReports();
        extent.setSystemInfo("Project Name", "Final Project Automation");
        extent.setSystemInfo("Website Automated", "Orange HRM");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("ExtentReports/ExtentReportTest2.html");
        extent.attachReporter(sparkReporter);

        test = extent.createTest("Verify logged in User is able to search Employee record in Directory");


        loginApplication();
        directoryPage.clickDirectorySection();

        boolean directoryPageDisplay = directoryPage.verifyDirectoryPageDisplayed();
        boolean expectedDirectoryPageDisplay = true;
        Assert.assertEquals(directoryPageDisplay,expectedDirectoryPageDisplay);
        test.log(Status.INFO, "Directory Page is Displayed");
        orangeHRMLogin.attachScreenshot("Directory Page");
        directoryPage.enterEmployeeName(employeename);
        directoryPage.clickSearchButton();
        orangeHRMLogin.attachScreenshot("Search Result");

        boolean profileDisplay = directoryPage.verifyProfileDisplayed();
        boolean expectedProfileDisplay = true;
        Assert.assertEquals(profileDisplay,expectedProfileDisplay);
        test.log(Status.INFO, "Profile is Displayed");

        JavascriptExecutor scrolldown = (JavascriptExecutor)driver;
        scrolldown.executeScript("window.scrollBy(0,300)","");
        orangeHRMLogin.attachScreenshot("Profile Display");
        logoutApplication();

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
        extent.flush();
    }

    public void loginApplication() throws IOException {
        WebElement LoginButton = new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));


        orangeHRMLogin.enterUserName("Admin");
        orangeHRMLogin.enterPassword("admin123");
        LoginButton.click();
        WebElement userdetails = new WebDriverWait(driver, ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='oxd-userdropdown-img']")));
        userdetails.click();
        boolean userinfo = orangeHRMLogin.VerifyLandingPage();
        boolean expectedUserInfo = true;
        Assert.assertEquals(userinfo,expectedUserInfo);
        test.log(Status.INFO, "User Logged in successfully");
        orangeHRMLogin.attachScreenshot("Dashboard Page");


    }

    public void logoutApplication(){
       orangeHRMLogin.clickUserdropdown();
       orangeHRMLogin.clickLogout();

    }

    private static String generateRandomString(int size) {
        int length = size;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);

    }


}
