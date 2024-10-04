package by.chitatel.ui;

import by.chitatel.constants.ErrorMessages;
import by.chitatel.names.EpicNames;
import by.chitatel.names.FeatureNames;
import by.chitatel.ui.modals.ErrorDialogPage;
import by.chitatel.ui.pages.HomePage;
import by.chitatel.generators.EmailGenerator;
import by.chitatel.generators.PasswordGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Epic(EpicNames.UI)
@Feature(FeatureNames.UI_LOGIN_EMAIL)
public class LoginWithEmailTest extends BaseTest {

    @Test
    @DisplayName("UI Test of email login with incorrect email and password")
    public void testLoginWithIncorrectEmailAndPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(EmailGenerator.generateValidEmail())
                .inputPassword(PasswordGenerator.generatePassword())
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, new ErrorDialogPage().getErrorMessage());
    }

    @Test
    @DisplayName("UI Test of email login with empty password")
    public void testLoginWithIncorrectEmailAndEmptyPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(EmailGenerator.generateValidEmail())
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, new ErrorDialogPage().getErrorMessage());
    }

    @Test
    @DisplayName("UI Test of email login with empty email")
    public void testLoginWithEmptyEmailAndIncorrectPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputPassword(PasswordGenerator.generatePassword())
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, new ErrorDialogPage().getErrorMessage());
    }

    @Test
    @DisplayName("UI Test of email login with empty email and password")
    public void testLoginWithEmptyEmailAndEmptyPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_WERE_NOT_INPUT, new ErrorDialogPage().getErrorMessage());
    }

    @Test
    @DisplayName("UI Test of email login with invalid email")
    public void testLoginWithInvalidEmailAndIncorrectPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail(EmailGenerator.generateInvalidEmail())
                .inputPassword(PasswordGenerator.generatePassword())
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, new ErrorDialogPage().getErrorMessage());
    }

    @Test
    @DisplayName("UI Test of email login with long email and password")
    public void testLoginWithLongEmailAndLongPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .performLogin(
                        EmailGenerator.generateValidEmail(EmailGenerator.MAX_ALLOWED_LENGTH + new Random().nextInt(EmailGenerator.MAX_ALLOWED_LENGTH)),
                        PasswordGenerator.generatePassword(PasswordGenerator.MAX_ALLOWED_LENGTH + new Random().nextInt(PasswordGenerator.MAX_ALLOWED_LENGTH)),
                        false);
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, new ErrorDialogPage().getErrorMessage());
    }
}
