package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "a[href='/login']")
    WebElement btnLoginHeader;

    //WebElement getBtnLoginHeader = driver.findElement(By.cssSelector("a[href='/login']"));

    public void clickBtnLoginHeder(){
        btnLoginHeader.click();
    }

}
