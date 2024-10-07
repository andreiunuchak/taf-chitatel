package by.chitatel.ui;

import by.chitatel.constants.ErrorMessages;
import by.chitatel.names.EpicNames;
import by.chitatel.names.FeatureNames;
import by.chitatel.ui.modals.ErrorDialogPage;
import by.chitatel.ui.pages.HomePage;
import by.chitatel.generators.PasswordGenerator;
import by.chitatel.generators.PhoneGenerator;
import by.chitatel.generators.objects.Phone;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

@Epic(EpicNames.UI)
@Feature(FeatureNames.UI_LOGIN_PHONE)
public class LoginWithPhoneTest extends BaseTest {

    @Test(description = "UI Test of sending password to incorrect phone number")
    public void testSendPasswordWithIncorrectPhone() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(PhoneGenerator.generateIncorrectPhoneNumber().getPhoneNumberWithOperatorCode())
                .clickSendCodeButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.PHONE_NOT_FOUND);
    }

    @Test(description = "UI Test of sending password to mock phone number")
    public void testSendPasswordWithMockPhone() {
        Phone phone = PhoneGenerator.generateMockPhoneNumber();
        String inputPhoneNumber = phone.getPhoneNumberWithOperatorCode();
        String fullPhoneNumber = phone.getPhoneNumberFull();
        new HomePage()
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(inputPhoneNumber)
                .clickSendCodeButton();
        Assert.assertEquals(new ErrorDialogPage().getTitle(), String.format(ErrorMessages.PASSWORD_SEND_TO_PHONE_NUMBER, fullPhoneNumber));
    }

    @Test(description = "UI Test of phone login with mock phone number and incorrect password")
    public void testLoginWithMockPhoneAndIncorrectPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .inputPassword(PasswordGenerator.generatePassword())
                .clickLoginButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.PASSWORD_DOES_NOT_MATCH);
    }

    @Test(description = "UI Test of phone login with mock phone number and empty password")
    public void testLoginWithMockPhoneAndEmptyPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .clickLoginButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.PASSWORD_WAS_NOT_INPUT);
    }

    @Test(description = "UI Test of phone login with mock phone number and long password")
    public void testLoginWithMockPhoneAndLongPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .inputPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .inputPassword(PasswordGenerator.generatePassword(PasswordGenerator.MAX_ALLOWED_LENGTH + new Random().nextInt(PasswordGenerator.MAX_ALLOWED_LENGTH)))
                .clickLoginButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.PASSWORD_IS_TOO_LONG);
    }

    @Test(description = "UI Test of phone login with empty phone number")
    public void testLoginWithEmptyPhoneAndIncorrectPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .inputPassword(PasswordGenerator.generatePassword())
                .clickLoginButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT);
    }

    @Test(description = "UI Test of phone login with invalid phone number")
    public void testLoginWithInvalidPhoneAndIncorrectPassword() {
        new HomePage()
                .openPage()
                .clickLoginButton()
                .performLogin(PhoneGenerator.generateInvalidPhoneNumber().getPhoneNumberWithOperatorCode(), PasswordGenerator.generatePassword(), true);
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT);
    }
}
