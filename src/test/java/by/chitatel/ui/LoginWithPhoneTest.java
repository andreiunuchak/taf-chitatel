package by.chitatel.ui;

import by.chitatel.ui.constants.ErrorMessages;
import by.chitatel.ui.modals.ErrorModal;
import by.chitatel.ui.pages.ChitatelPage;
import by.chitatel.ui.utils.Passwords;
import by.chitatel.ui.utils.Phones;
import by.chitatel.ui.objects.Phone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginWithPhoneTest extends BaseTest {
    @Test
    public void testSendPasswordWithIncorrectPhone() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(Phones.generateIncorrectPhoneNumber().getPhoneNumberWithOperatorCode())
                .clickSendCodeButton();
        Assertions.assertEquals(ErrorMessages.PHONE_NOT_FOUND, new ErrorModal().getErrorMessage());
    }

    @Test
    public void testSendPasswordWithMockPhone() {
        Phone phone = Phones.generateMockPhoneNumber();
        String inputPhoneNumber = phone.getPhoneNumberWithOperatorCode();
        String fullPhoneNumber = phone.getFullPhoneNumber();
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(inputPhoneNumber)
                .clickSendCodeButton();
        Assertions.assertEquals(String.format(ErrorMessages.PASSWORD_SEND_TO_PHONE_NUMBER, fullPhoneNumber), new ErrorModal().getTitle());
    }

    @Test
    public void testLoginWithMockPhoneAndIncorrectPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(Phones.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .inputPassword(Passwords.generatePassword(10))
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.PASSWORD_DOES_NOT_MATCH, new ErrorModal().getErrorMessage());
    }

    @Test
    public void testLoginWithMockPhoneAndEmptyPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(Phones.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, new ErrorModal().getErrorMessage());
    }

    @Test
    public void testLoginWithMockPhoneAndLongPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(Phones.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .inputPassword(Passwords.generatePassword(500))
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.PASSWORD_IS_TOO_LONG, new ErrorModal().getErrorMessage());
    }

    @Test
    public void testLoginWithEmptyPhoneAndIncorrectPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .inputPassword(Passwords.generatePassword(10))
                .clickLoginButton();
        Assertions.assertEquals(ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT, new ErrorModal().getErrorMessage());
    }

    @Test
    public void testLoginWithInvalidPhoneAndIncorrectPassword() {
        new ChitatelPage()
                .openPage()
                .clickLoginButton()
                .performLogin(Phones.generateInvalidPhoneNumber(1,6).getPhoneNumberWithOperatorCode(), Passwords.generatePassword(10), true);
        Assertions.assertEquals(ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT, new ErrorModal().getErrorMessage());
    }
}
