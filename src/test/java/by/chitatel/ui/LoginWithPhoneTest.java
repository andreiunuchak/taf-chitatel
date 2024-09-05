package by.chitatel.ui;

import by.chitatel.ui.constants.ExpectedErrorMessages;
import by.chitatel.ui.modals.ErrorsModal;
import by.chitatel.ui.pages.ChitatelPage;
import by.chitatel.ui.utils.Passwords;
import by.chitatel.ui.utils.Phones;
import by.chitatel.ui.objects.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginWithPhoneTest extends BaseTest {
    @Test
    public void testSendPasswordWithIncorrectPhone() {
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(Phones.generateIncorrectPhoneNumber().getPhoneNumberWithOperatorCode())
                .clickSendCodeButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.PHONE_NOT_FOUND;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testSendPasswordWithMockPhone() {
        Phone phone = Phones.generateMockPhoneNumber();
        String inputPhoneNumber = phone.getPhoneNumberWithOperatorCode();
        String fullPhoneNumber = phone.getFullPhoneNumber();
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(inputPhoneNumber)
                .clickSendCodeButton();
        String actualErrorMessage = new ErrorsModal(driver).getTitle();
        String expectedErrorMessage = String.format(ExpectedErrorMessages.PASSWORD_SEND_TO_PHONE_NUMBER, fullPhoneNumber);

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithMockPhoneAndIncorrectPassword() {
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(Phones.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .inputPassword(Passwords.generatePassword(10))
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.PASSWORD_DOES_NOT_MATCH;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithMockPhoneAndEmptyPassword() {
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(Phones.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.PASSWORD_WAS_NOT_INPUT;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithMockPhoneAndLongPassword() {
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(Phones.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .inputPassword(Passwords.generatePassword(500))
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.PASSWORD_IS_TOO_LONG;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithEmptyPhoneAndIncorrectPassword() {
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .inputPassword(Passwords.generatePassword(10))
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithInvalidPhoneAndIncorrectPassword() {
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .performLogin(Phones.generateInvalidPhoneNumber(1,6).getPhoneNumberWithOperatorCode(), Passwords.generatePassword(10), true);
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = ExpectedErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT;

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
