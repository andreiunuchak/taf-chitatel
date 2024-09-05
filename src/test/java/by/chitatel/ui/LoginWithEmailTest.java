package by.chitatel.ui;

import by.chitatel.ui.constants.ExpectedErrorMessages;
import by.chitatel.ui.modals.ErrorsModal;
import by.chitatel.ui.pages.ChitatelPage;
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
                .inputEmail("user@test.com")
                .inputPassword("123456")
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
                .inputEmail("user@test.com")
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
                .inputPassword("123456")
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
                .inputEmail("user@test.")
                .inputPassword("123456")
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
                .performLogin("incorrectemail".repeat(50)+"@gmail.com", Passwords.generatePassword(500), false);
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
