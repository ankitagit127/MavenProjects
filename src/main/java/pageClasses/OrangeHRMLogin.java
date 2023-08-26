package pageClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class OrangeHRMLogin {

    WebDriver driver;

    ExtentReports extent;
    ExtentTest test;
    @FindBy(xpath = "//input[@name='username']")
    WebElement UserName;

    @FindBy(xpath = "//input[@name='password']")
    WebElement Password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement LoginButton;

    @FindBy(xpath = "//img[@class='oxd-userdropdown-img']")
    WebElement UserDropdown;

    @FindBy(xpath = "//li//a[text()=\"Logout\"]")
    WebElement Logout;

    public OrangeHRMLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String username) {
        UserName.sendKeys(username);
    }

    public void enterPassword(String password) {
        Password.sendKeys(password);
    }

    public void clickLogin() {
        LoginButton.click();
    }

    public void clickLogout() {
        Logout.click();
    }

    public void clickUserdropdown() {
        UserDropdown.click();
    }

    public boolean VerifyLandingPage() {
        boolean userDropdownDisplayed = UserDropdown.isDisplayed();
        return userDropdownDisplayed;
    }

    public void attachScreenshot(String filename) throws IOException {
        String path = takeScreenshot(filename);

    }

    public String takeScreenshot(String filename) throws IOException {
        filename = filename + ".png";
        String directory = "./ExtentReports/Screenshots//";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(directory + filename));
        return directory + filename;


    }

}
