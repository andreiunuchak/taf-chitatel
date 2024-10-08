package by.chitatel.api;

import by.chitatel.api.endpoints.LoginWithPhone;
import by.chitatel.api.responses.login.phone.PhoneLoginErrors;
import by.chitatel.api.utils.Errors;
import by.chitatel.api.utils.FormParametersLoginPhone;
import by.chitatel.constants.ErrorMessages;
import by.chitatel.generators.PasswordGenerator;
import by.chitatel.generators.PhoneGenerator;
import by.chitatel.generators.enums.RememberMeCodes;
import by.chitatel.names.EpicNames;
import by.chitatel.names.FeatureNames;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;

@Epic(EpicNames.API)
@Feature(FeatureNames.API_LOGIN_PHONE)
public class LoginWithPhoneTest extends BaseTest {

    @Test
    @DisplayName("API OPTIONS Test of allowed methods")
    public void testLoginPhoneOptions() {
        Response response = new LoginWithPhone().performOptionsRequest(csrfToken, cookies);
        String allowedMethods = response.getHeader(HttpHeaders.ALLOW);

        Assertions.assertEquals("POST", allowedMethods);
    }

    @Test
    @DisplayName("API POST Test of phone login with not formatted phone number")
    public void testPhoneLoginWithValidNotFormattedPhone() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateIncorrectPhoneNumber().getPhoneNumberFull())
                .setPhonePassword(PasswordGenerator.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.PHONE_AND_PASSWORD_ARE_WRONG, errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getPhoneError()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login without country and operator codes")
    public void testPhoneLoginWithPhoneThatDoesNotHaveCodes() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateIncorrectPhoneNumber().getPhoneNumber())
                .setPhonePassword(PasswordGenerator.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.PHONE_AND_PASSWORD_ARE_WRONG, errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getPhoneError()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login with short password")
    public void testPhoneLoginWithValidPhoneAndShortPassword() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateIncorrectPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(PasswordGenerator.generatePassword(PasswordGenerator.MIN_ALLOWED_LENGTH - 1))
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getPhoneError()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_IS_TOO_SHORT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login with long password")
    public void testPhoneLoginWithValidPhoneAndLongPassword() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateIncorrectPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(PasswordGenerator.generatePassword(PasswordGenerator.MAX_ALLOWED_LENGTH + 1))
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getPhoneError()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_IS_TOO_LONG, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login with mock phone number and incorrect password")
    public void testPhoneLoginWithMockPhoneAndIncorrectPassword() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(PasswordGenerator.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_DOES_NOT_MATCH, errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getPhoneError()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login with mock phone number and empty password")
    public void testPhoneLoginWithMockPhoneAndEmptyPassword() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword("")
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getPhoneError()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login with empty parameters")
    public void testPhoneLoginWithEmptyParams() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone("")
                .setPhonePassword("")
                .setPhoneRememberMe("")
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertEquals(ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT, errors.getPhoneError().getFirst()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login with boolean parameters")
    public void testPhoneLoginWithBooleanParams() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(true)
                .setPhonePassword(true)
                .setPhoneRememberMe(false)
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getPhoneError()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_IS_TOO_SHORT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login with integer parameters")
    public void testPhoneLoginWithIntegerParams() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(new Random().nextInt(1000000))
                .setPhonePassword(new Random().nextInt(1000000))
                .setPhoneRememberMe(new Random().nextInt(1000000))
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.PHONE_AND_PASSWORD_ARE_WRONG, errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getPhoneError()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login with 'null' parameters")
    public void testPhoneLoginWithNullParams() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(null)
                .setPhonePassword(null)
                .setPhoneRememberMe(null)
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertEquals(ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT, errors.getPhoneError().getFirst()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login without parameters")
    public void testPhoneLoginWithoutParams() {
        Response response = new LoginWithPhone().performPostRequest(csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(statusCode, 200),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertEquals(ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT, errors.getPhoneError().getFirst()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of phone login without scrf-token")
    public void testPhoneLoginWithoutToken() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(PasswordGenerator.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, cookies);
        int statusCode = response.statusCode();

        Assertions.assertEquals(419, statusCode);
    }

    @Test
    @DisplayName("API POST Test of phone login without cookies")
    public void testPhoneLoginWithoutCookies() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(PasswordGenerator.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken);
        int statusCode = response.statusCode();

        Assertions.assertEquals(419, statusCode);
    }
}
