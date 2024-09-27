package by.chitatel.api;

import by.chitatel.api.endpoints.LoginWithPhone;
import by.chitatel.api.responses.login.phone.PhoneLoginErrors;
import by.chitatel.api.utils.Errors;
import by.chitatel.api.utils.FormParameters;
import by.chitatel.constants.ErrorMessages;
import by.chitatel.generators.Passwords;
import by.chitatel.generators.Phones;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;

public class LoginWithPhoneTests extends BaseTest {

    @Test
    public void testLoginPhoneOptions() {
        Response response = new LoginWithPhone().performOptionsRequest(csrfToken, cookies);
        String allowedMethods = response.getHeader("Allow");

        Assertions.assertEquals("POST", allowedMethods);
    }

    @Test
    public void testPhoneLoginWithValidNotFormattedPhone() {
        String phoneNumber = Phones.generateIncorrectPhoneNumber().getFullPhoneNumber();
        String phonePassword = Passwords.generatePassword();
        String rememberMe = "1";

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertEquals(ErrorMessages.PHONE_AND_PASSWORD_ARE_WRONG, errors.getNouserError());
        Assertions.assertNull(errors.getPhoneError());
        Assertions.assertNull(errors.getPasswordError());
    }

    @Test
    public void testPhoneLoginWithPhoneThatDoesNotHaveCodes() {
        String phoneNumber = Phones.generateIncorrectPhoneNumber().getPhoneNumber();
        String phonePassword = Passwords.generatePassword();
        String rememberMe = "1";

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertEquals(ErrorMessages.PHONE_AND_PASSWORD_ARE_WRONG, errors.getNouserError());
        Assertions.assertNull(errors.getPhoneError());
        Assertions.assertNull(errors.getPasswordError());
    }

    @Test
    public void testPhoneLoginWithValidPhoneAndShortPassword() {
        String phoneNumber = Phones.generateIncorrectPhoneNumber().getFullPhoneNumberFormatted();
        String phonePassword = Passwords.generatePassword(5);
        String rememberMe = "1";

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNouserError());
        Assertions.assertNull(errors.getPhoneError());
        Assertions.assertEquals(ErrorMessages.PASSWORD_IS_TOO_SHORT, errors.getPasswordError().getFirst());
    }

    @Test
    public void testPhoneLoginWithValidPhoneAndLongPassword() {
        String phoneNumber = Phones.generateIncorrectPhoneNumber().getFullPhoneNumberFormatted();
        String phonePassword = Passwords.generatePassword(500);
        String rememberMe = "1";

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNouserError());
        Assertions.assertNull(errors.getPhoneError());
        Assertions.assertEquals(ErrorMessages.PASSWORD_IS_TOO_LONG, errors.getPasswordError().getFirst());
    }

    @Test
    public void testPhoneLoginWithMockPhoneAndIncorrectPassword() {
        String phoneNumber = Phones.generateMockPhoneNumber().getFullPhoneNumberFormatted();
        String phonePassword = Passwords.generatePassword();
        String rememberMe = "1";

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertEquals(ErrorMessages.PASSWORD_DOES_NOT_MATCH, errors.getNouserError());
        Assertions.assertNull(errors.getPhoneError());
        Assertions.assertNull(errors.getPasswordError());
    }

    @Test
    public void testPhoneLoginWithMockPhoneAndEmptyPassword() {
        String phoneNumber = Phones.generateMockPhoneNumber().getFullPhoneNumberFormatted();
        String phonePassword = "";
        String rememberMe = "1";

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNouserError());
        Assertions.assertNull(errors.getPhoneError());
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst());
    }

    @Test
    public void testPhoneLoginWithEmptyParams() {
        String phoneNumber = "";
        String phonePassword = "";
        String rememberMe = "";

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNouserError());
        Assertions.assertEquals(ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT, errors.getPhoneError().getFirst());
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst());
    }

    @Test
    public void testPhoneLoginWithBooleanParams() {
        boolean phoneNumber = true;
        boolean phonePassword = true;
        boolean rememberMe = false;

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNouserError());
        Assertions.assertNull(errors.getPhoneError());
        Assertions.assertEquals(ErrorMessages.PASSWORD_IS_TOO_SHORT, errors.getPasswordError().getFirst());
    }

    @Test
    public void testPhoneLoginWithIntegerParams() {
        int phoneNumber = new Random().nextInt(1000000);
        int phonePassword = new Random().nextInt(1000000);
        int rememberMe = new Random().nextInt(1000000);

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertEquals(ErrorMessages.PHONE_AND_PASSWORD_ARE_WRONG, errors.getNouserError());
        Assertions.assertNull(errors.getPhoneError());
        Assertions.assertNull(errors.getPasswordError());
    }

    @Test
    public void testPhoneLoginWithNullParams() {
        String phoneNumber = null;
        String phonePassword = null;
        String rememberMe = null;

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNouserError());
        Assertions.assertEquals(ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT, errors.getPhoneError().getFirst());
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst());
    }

    @Test
    public void testPhoneLoginWithoutParams() {
        Response response = new LoginWithPhone().performPostRequest(csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNouserError());
        Assertions.assertEquals(ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT, errors.getPhoneError().getFirst());
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst());
    }

    @Test
    public void testPhoneLoginWithoutToken() {
        String phoneNumber = Phones.generateMockPhoneNumber().getFullPhoneNumberFormatted();
        String phonePassword = Passwords.generatePassword();
        String rememberMe = "0";

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, cookies);
        int statusCode = response.statusCode();

        Assertions.assertEquals(statusCode, 419);
    }

    @Test
    public void testPhoneLoginWithoutCookies() {
        String phoneNumber = Phones.generateMockPhoneNumber().getFullPhoneNumberFormatted();
        String phonePassword = Passwords.generatePassword();
        String rememberMe = "0";

        Map<String, Object> formParams = new FormParameters().setPhone(phoneNumber).setPhonePassword(phonePassword).setPhoneRememberMe(rememberMe).build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken);
        int statusCode = response.statusCode();

        Assertions.assertEquals(statusCode, 419);
    }
}
