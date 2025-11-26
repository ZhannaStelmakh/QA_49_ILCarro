package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WDListener;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ApplicationManager {

    public final static Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    private WebDriver driver;

    public WebDriver getDriver() {

        return driver;
    }

    static String browser = System.getProperty("browser", "chrom");

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        logger.info("Start testing" + LocalDate.now() +" : " +LocalTime.now());
        if (browser.equalsIgnoreCase("browser")){
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        //chromeOptions.addExtensions()
        driver = new ChromeDriver(chromeOptions);
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }else{
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverListener webDriverListener = new WDListener();
        driver = new EventFiringDecorator<>(webDriverListener).decorate(driver);
    }

    @AfterMethod(enabled = true, alwaysRun = true)       // false
    public void tearDown(){
        logger.info("Stop testing" + LocalDate.now() +" : " +LocalTime.now());
        if (driver != null)
            driver.quit();
    }

//    public WebDriver getDriver(){
//        return driver;
//    }
}
