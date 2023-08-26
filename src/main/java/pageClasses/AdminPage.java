package pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {

    WebDriver driver;

    @FindBy (xpath = "//a[@href=\"/web/index.php/admin/viewAdminModule\"]")
    WebElement AdminSection;

    @FindBy (xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    WebElement AddButton;

    @FindBy (xpath = "//label[text()=\"User Role\"]//parent::div//following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement UserRole;

    @FindBy (xpath = "//input[@placeholder='Type for hints...']")
    WebElement EmployeeName;

    @FindBy (xpath = "//label[text()=\"Status\"]//parent::div//following-sibling::div//div[@class='oxd-select-text-input']")
    WebElement Status;

    @FindBy (xpath = "//label[text()=\"Username\"]//parent::div//following-sibling::div//input[@class='oxd-input oxd-input--active']")
    WebElement Username;

    @FindBy (xpath = "//label[text()=\"Password\"]//parent::div//following-sibling::div//input[@type=\"password\"]")
    WebElement Password;

    @FindBy (xpath = "//label[text()=\"Confirm Password\"]//parent::div//following-sibling::div//input[@type=\"password\"]")
    WebElement ConfirmPassword;

    @FindBy (xpath = "//h6[text()=\"Admin\"]")
    WebElement AdminPage;

    @FindBy (xpath = "//button[@type='submit']")
    WebElement SaveButton;

    @FindBy (xpath = "//p[text()=\"Successfully Saved\"]")
    WebElement VerificationMessage;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickAdminSection(){AdminSection.click();}
    public void clickAddButton(){AddButton.click();}

    public void clickSaveButton(){SaveButton.click();}

    public void selectUserRole(String userRole){
        UserRole.click();
        System.out.println(userRole);
        WebElement selectOption = driver.findElement(By.xpath("//div[@role='option']//span[text()=\"" + userRole + "\"]"));
        //div[@role='option']//span[text()=\"Admin\"]"
        selectOption.click();

    }

    public void selectStatus(String status){
        Status.click();
        System.out.println(status);
        WebElement selectOption = driver.findElement(By.xpath("//div[@role='option']//span[text()=\"" + status + "\"]"));
        selectOption.click();

    }

    public void enterEmployee(String Employee_Name) throws InterruptedException {
        EmployeeName.sendKeys(Employee_Name);
        Thread.sleep(2000);
        Actions action = new Actions(driver);
        action.sendKeys(EmployeeName, Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
    }


    public boolean verifyAdminPageDisplayed(){
        boolean AdminPageDisplayed = AdminPage.isDisplayed();
        return AdminPageDisplayed;
    }

    public boolean verifyVerificationMessageDisplayed(){
        boolean VerificationDisplayed = VerificationMessage.isDisplayed();
        return VerificationDisplayed;
    }

    public void enterUsername(String username){Username.sendKeys(username);}

    public void enterPassword(String password){Password.sendKeys(password);}

    public void enterConfirmPassword(String confirmpassword){ConfirmPassword.sendKeys(confirmpassword);}


}
