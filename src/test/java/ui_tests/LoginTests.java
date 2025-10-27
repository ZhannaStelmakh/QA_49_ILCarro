package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends ApplicationManager {
    @Test
    public void LoginPositiveTest() {
        User user = User.builder()
                .username("bilbo_baggins_12345@mail.com")
                .password("password123!")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isLoggedDisplayed());
    }


    @Test
    public void loginNegativeTest_wrongPassword(){
        User user = User.builder()
                .username("bilbo_baggins_12345@mail.com")
                .password("wrong password")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isLoggedIncorrect());
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
