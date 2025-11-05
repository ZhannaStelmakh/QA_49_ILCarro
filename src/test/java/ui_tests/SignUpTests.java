package ui_tests;

import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignUpPage;

import static utils.UserFactory.*;



public class SignUpTests extends ApplicationManager {

    SignUpPage signUpPage;

    @BeforeMethod
    public void gotoSignUpPage(){
       new HomePage(getDriver()).clickBtnSignUpHeader();
       signUpPage = new SignUpPage(getDriver());
    }

    @Test
    public void registrationPositiveTest(){
        User user = positiveUser();
        signUpPage.typeLoginForm(user);
        signUpPage.clickCheckBoxWithActions();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isTextDialogContainerPresent());
    }

    @Test
    public void SignUpPositiveTest() {
        User user = User.builder()
                .firstName("Vishenka")
                .lastName("Stelmakh")
                .username("cherry@gmail.com")
                .password("Ch12345$")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isLoggedDisplayed());
    }

    @Test
    public void registrationNegativeTest_emptyName(){
        User user = positiveUser();
        user.setFirstName("");
        signUpPage.typeLoginForm(user);
        signUpPage.clickCheckBoxWithActions();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isTextInErrorPresent("Name is required"));
    }

    @Test
    public void registrationNegativeTest_emptyPassword(){
        User user = positiveUser();
        user.setPassword("");
        signUpPage.typeLoginForm(user);
        signUpPage.clickCheckBoxWithActions();
        signUpPage.clickBtnYalla();
        Assert.assertTrue(signUpPage.isTextInErrorPresent("Password is required"));
    }

    @Test
    public void SignUpNegativeTest_emptyPassword(){
        User user = User.builder()
                .username("cherry@gmail.com")
                .password("")
                .build();
        new HomePage(getDriver()).clickBtnLoginHeader();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.isTextInErrorPresent("Password is required"));
    }

}
