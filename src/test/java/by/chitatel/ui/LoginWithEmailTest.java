package by.chitatel.ui;

import by.chitatel.constants.ErrorMessages;
import by.chitatel.ui.modals.ErrorModal;
import by.chitatel.ui.pages.HomePage;
import by.chitatel.generators.Emails;
import by.chitatel.generators.Passwords;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("UI Tests")
@Feature("UI Tests of email login")
public class LoginWithEmailTest extends BaseTest {

    @Test
    @DisplayName("UI Test of email login with incorrect email and password")
    public void testLoginWithIncorrectEmailAndPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(Emails.generateValidEmail())
                .inputPassword(Passwords.generatePassword())
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, new ErrorModal().getErrorMessage());
    }

    @Test
    @DisplayName("UI Test of email login with empty password")
    public void testLoginWithIncorrectEmailAndEmptyPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(Emails.generateValidEmail())
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, new ErrorModal().getErrorMessage());
    }

    @Test
    @DisplayName("UI Test of email login with empty email")
    public void testLoginWithEmptyEmailAndIncorrectPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputPassword(Passwords.generatePassword())
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, new ErrorModal().getErrorMessage());
    }

    @Test
    @DisplayName("UI Test of email login with empty email and password")
    public void testLoginWithEmptyEmailAndEmptyPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_WERE_NOT_INPUT, new ErrorModal().getErrorMessage());
    }

    @Test
    @DisplayName("UI Test of email login with invalid email")
    public void testLoginWithInvalidEmailAndIncorrectPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(Emails.generateInvalidEmail())
                .inputPassword(Passwords.generatePassword())
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, new ErrorModal().getErrorMessage());
    }

    @Test
    @DisplayName("UI Test of email login with long email and password")
    public void testLoginWithLongEmailAndLongPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .performLogin(Emails.generateValidEmail(Emails.MAX_ALLOWED_LENGTH + 1), Passwords.generatePassword(Passwords.MAX_ALLOWED_LENGTH + 1), false);
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, new ErrorModal().getErrorMessage());
    }
}
