package by.chitatel.ui;

import by.chitatel.ui.constants.ErrorMessages;
import by.chitatel.ui.modals.ErrorModal;
import by.chitatel.ui.pages.ChitatelPage;
import by.chitatel.ui.utils.Emails;
import by.chitatel.ui.utils.Passwords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginWithEmailTest extends BaseTest {

    @Test
    public void testLoginWithIncorrectEmailAndPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(Emails.generateValidEmail())
                .inputPassword(Passwords.generatePassword(10))
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, new ErrorModal().getErrorMessage());
    }

    @Test
    public void testLoginWithIncorrectEmailAndEmptyPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(Emails.generateValidEmail())
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, new ErrorModal().getErrorMessage());
    }

    @Test
    public void testLoginWithEmptyEmailAndIncorrectPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputPassword(Passwords.generatePassword(10))
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, new ErrorModal().getErrorMessage());
    }

    @Test
    public void testLoginWithEmptyEmailAndEmptyPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_WERE_NOT_INPUT, new ErrorModal().getErrorMessage());
    }

    @Test
    public void testLoginWithInvalidEmailAndIncorrectPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(Emails.generateInvalidEmail())
                .inputPassword(Passwords.generatePassword(10))
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, new ErrorModal().getErrorMessage());
    }

    @Test
    public void testLoginWithLongEmailAndLongPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .performLogin(Emails.generateValidEmail(500), Passwords.generatePassword(500), false);
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, new ErrorModal().getErrorMessage());
    }
}
