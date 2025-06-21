package ui_tests;

import dto.UserLombok;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static utils.RandomUtils.generateEmail;

public class LoginTests extends ApplicationManager {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        homePage = new HomePage(getDriver());
        homePage.clickBtnHeaderLogin();
        loginPage = new LoginPage(getDriver());
    }

//    old variant
//    @Test
//    public void loginPositiveTest() {
//        User user = new User("elenam@gmail.com", "Password$1");
//        HomePage homePage = new HomePage(getDriver());
//        homePage.clickBtnHeaderLogin();
//        LoginPage loginPage = new LoginPage(getDriver());
//        loginPage.typeLoginForm(user);
//    }

    @Test
    public void loginPositiveTest_lombok() {
        UserLombok user = UserLombok.builder()
                .username("elenam@gmail.com")
                .password("Password$1")
                .build();
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.validatePopUpMessage("Logged in success"), "loginPositiveTest_lombok");
    }

    @Test
    public void loginNegativeTest_unregUser() {
        UserLombok user = UserLombok.builder()
                .username(generateEmail(5))
                .password("Password$1")
                .build();
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.validatePopUpMessage("Login or Password incorrect"), "loginNegativeTest_unregUser");
    }

    @Test
    public void loginNegativeTest_emptyPassword() {
        UserLombok user = UserLombok.builder()
                .username("elenam@gmail.com")
                .password("")
                .build();
        loginPage.typeLoginForm(user);
        Assert.assertTrue(loginPage.validateMessageErrorPassword(), "loginNegativeTest_emptyPassword");
    }
}
