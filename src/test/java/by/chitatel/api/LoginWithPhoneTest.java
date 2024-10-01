package by.chitatel.api;

import by.chitatel.api.endpoints.LoginWithPhone;
import by.chitatel.api.responses.login.phone.PhoneLoginErrors;
import by.chitatel.api.utils.Errors;
import by.chitatel.api.utils.FormParameters;
import by.chitatel.constants.ErrorMessages;
import by.chitatel.generators.Passwords;
import by.chitatel.generators.Phones;
import by.chitatel.generators.enums.RememberMeCodes;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;

public class LoginWithPhoneTest extends BaseTest {

    @Test
    public void testLoginPhoneOptions() {
        Response response = new LoginWithPhone().performOptionsRequest(csrfToken, cookies);
        String allowedMethods = response.getHeader(HttpHeaders.ALLOW);

        Assertions.assertEquals("POST", allowedMethods);
    }

    @Test
    public void testPhoneLoginWithValidNotFormattedPhone() {
        Map<String, Object> formParams = new FormParameters()
                .setPhone(Phones.generateIncorrectPhoneNumber().getPhoneNumberFull())
                .setPhonePassword(Passwords.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
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
        Map<String, Object> formParams = new FormParameters()
                .setPhone(Phones.generateIncorrectPhoneNumber().getPhoneNumber())
                .setPhonePassword(Passwords.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
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
        Map<String, Object> formParams = new FormParameters()
                .setPhone(Phones.generateIncorrectPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(Passwords.generatePassword(Passwords.MIN_ALLOWED_LENGTH - 1))
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
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
        Map<String, Object> formParams = new FormParameters()
                .setPhone(Phones.generateIncorrectPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(Passwords.generatePassword(Passwords.MAX_ALLOWED_LENGTH + 1))
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
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
        Map<String, Object> formParams = new FormParameters()
                .setPhone(Phones.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(Passwords.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
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
        Map<String, Object> formParams = new FormParameters()
                .setPhone(Phones.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword("")
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
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
        Map<String, Object> formParams = new FormParameters()
                .setPhone("")
                .setPhonePassword("")
                .setPhoneRememberMe("")
                .build();
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
        Map<String, Object> formParams = new FormParameters()
                .setPhone(true)
                .setPhonePassword(true)
                .setPhoneRememberMe(false)
                .build();
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
        Map<String, Object> formParams = new FormParameters()
                .setPhone(new Random().nextInt(1000000))
                .setPhonePassword(new Random().nextInt(1000000))
                .setPhoneRememberMe(new Random().nextInt(1000000))
                .build();
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
        Map<String, Object> formParams = new FormParameters()
                .setPhone(null)
                .setPhonePassword(null)
                .setPhoneRememberMe(null)
                .build();
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
        Map<String, Object> formParams = new FormParameters()
                .setPhone(Phones.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(Passwords.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, cookies);
        int statusCode = response.statusCode();

        Assertions.assertEquals(statusCode, 419);
    }

    @Test
    public void testPhoneLoginWithoutCookies() {
        Map<String, Object> formParams = new FormParameters()
                .setPhone(Phones.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(Passwords.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken);
        int statusCode = response.statusCode();

        Assertions.assertEquals(statusCode, 419);
    }
}
