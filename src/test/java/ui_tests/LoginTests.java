package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyzer;
import utils.TestNGListener;

import java.lang.reflect.Method;

@Listeners(TestNGListener.class)
public class LoginTests extends ApplicationManager {

    @Test(groups = "smoke")//(retryAnalyzer = RetryAnalyzer.class)
    public void loginPositiveTest(Method method) {
        User user = User.builder()
                .username("cherry@gmail.com")
                .password("Ch12345$")
                .build();
        logger.info("start test " + method.getName() +" with date " + user);
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        //logger.error("Example error");
        Assert.assertTrue(loginPage.isLoggedDisplayed());
    }

    @Test
    public void loginNegativeTest_wrongPassword(){
        User user = User.builder()
                .username("cherry@gmail.com")
                .password("wrong password")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isLoggedIncorrect());
    }

    @Test
    public void loginNegativeTest_emptyPassword(){
        User user = User.builder()
                .username("cherry@gmail.com")
                .password("")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isTextInErrorPresent("Password is required"));
    }

    @Test
    public void loginNegativeTest_emailWOAt(){
        User user = User.builder()
                .username("cherrygmail.com")
                .password("Ch12345$")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isTextInErrorPresent("It'snot look like email"));
    }
//    @Test
//    public void LoginNegativeTest_WrongPassword(){
//        User user = new User("cherry@gmail.com", "ch12345$");
//        HomePage homePage = new HomePage(getDriver());
//        homePage.clickBtnLoginHeader();
//        LoginPage loginPage = new LoginPage(getDriver());
//        loginPage.typeLoginFormWithUser(user);
//    }
}
