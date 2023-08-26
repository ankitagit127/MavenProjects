package pageClasses;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DirectoryPage {

    WebDriver driver;

    @FindBy(xpath = "//span[text() =\"Directory\"]")
    WebElement DirectorySection;

    @FindBy (xpath = "//h5[text()=\"Directory\"]")
    WebElement DirectoryPage;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement EmployeeName;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement SearchButton;

    @FindBy(xpath = "//div[@class=\"orangehrm-profile-picture\"]")
    WebElement ProfilePicture;

    public DirectoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickDirectorySection(){DirectorySection.click();}

    public void enterEmployeeName(String employeename) throws InterruptedException {
        EmployeeName.sendKeys(employeename);
        Thread.sleep(2000);
        Actions action = new Actions(driver);
        action.sendKeys(EmployeeName, Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();}

    public void clickSearchButton(){SearchButton.click();}

    public WebElement profilePicture(){ProfilePicture.isEnabled();
        return null;
    }

    public boolean verifyDirectoryPageDisplayed() {
        boolean DirectoryPageDisplayed = DirectoryPage.isDisplayed();
        return DirectoryPageDisplayed;

    }

    public boolean verifyProfileDisplayed() {
        boolean ProfilePicDisplayed = ProfilePicture.isDisplayed();
        return ProfilePicDisplayed;

    }


    }
