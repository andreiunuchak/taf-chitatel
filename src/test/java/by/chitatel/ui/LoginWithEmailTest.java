package by.chitatel.ui;

import by.chitatel.ui.constants.ExpectedErrorMessages;
import by.chitatel.ui.modals.ErrorsModal;
import by.chitatel.ui.pages.ChitatelPage;
import by.chitatel.ui.utils.Emails;
import by.chitatel.ui.utils.Passwords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginWithEmailTest extends BaseTest{

    @Test
    public void testLoginWithIncorrectEmailAndPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(Emails.generateValidEmail())
                .inputPassword(Passwords.generatePassword(10))
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithIncorrectEmailAndEmptyPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(Emails.generateValidEmail())
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.PASSWORD_WAS_NOT_INPUT;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithEmptyEmailAndIncorrectPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputPassword(Passwords.generatePassword(10))
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.EMAIL_WAS_NOT_INPUT;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithEmptyEmailAndEmptyPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.EMAIL_AND_PASSWORD_WERE_NOT_INPUT;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithInvalidEmailAndIncorrectPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(Emails.generateInvalidEmail())
                .inputPassword(Passwords.generatePassword(10))
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithLongEmailAndLongPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .performLogin(Emails.generateValidEmail(500), Passwords.generatePassword(500), false);
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
