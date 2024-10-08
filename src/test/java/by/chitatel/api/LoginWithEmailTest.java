package by.chitatel.api;

import by.chitatel.api.endpoints.LoginWithEmail;
import by.chitatel.api.responses.login.email.EmailLoginErrors;
import by.chitatel.api.utils.Errors;
import by.chitatel.api.utils.FormParametersLoginEmail;
import by.chitatel.constants.ErrorMessages;
import by.chitatel.generators.EmailGenerator;
import by.chitatel.generators.PasswordGenerator;
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
@Feature(FeatureNames.API_LOGIN_EMAIL)
public class LoginWithEmailTest extends BaseTest {

    @Test
    @DisplayName("API OPTIONS Test of allowed methods")
    public void testLoginEmailOptions() {
        Response response = new LoginWithEmail().performOptionsRequest(csrfToken, cookies);
        String allowedMethods = response.getHeader(HttpHeaders.ALLOW);

        Assertions.assertEquals("GET,HEAD,POST", allowedMethods);
    }

    @Test
    @DisplayName("API GET Test of email login")
    public void testGetRequest() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail(EmailGenerator.generateValidEmail())
                .setPassword(PasswordGenerator.generatePassword())
                .setRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithEmail().performGetRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();

        Assertions.assertEquals(200, statusCode);
    }

    @Test
    @DisplayName("API HEAD Test of email login")
    public void testHeadRequest() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail(EmailGenerator.generateValidEmail())
                .setPassword(PasswordGenerator.generatePassword())
                .setRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithEmail().performHeadRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();

        Assertions.assertEquals(200, statusCode);
    }

    @Test
    @DisplayName("API POST Test of email login with incorrect email")
    public void testLoginWithIncorrectEmailAndPassword() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail(EmailGenerator.generateValidEmail())
                .setPassword(PasswordGenerator.generatePassword())
                .setRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getEmailError()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }

    @Test
    @DisplayName("API POST Test of email login with empty strings")
    public void testLoginWithEmptyParameters() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail("")
                .setPassword("")
                .setRememberMe("")
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, errors.getEmailError().getFirst()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of email login with incorrect email and empty password")
    public void testLoginWithIncorrectEmailAndEmptyPassword() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail(EmailGenerator.generateValidEmail())
                .setPassword("")
                .setRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getEmailError()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of email login with empty email")
    public void testLoginWithEmptyEmailAndIncorrectPassword() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail("")
                .setPassword(PasswordGenerator.generatePassword())
                .setRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, errors.getEmailError().getFirst()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }

    @Test
    @DisplayName("API POST Test of email login with 'null' parameters")
    public void testLoginWithNullParameters() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail(null)
                .setPassword(null)
                .setRememberMe(null)
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, errors.getEmailError().getFirst()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of email login with integer parameters")
    public void testLoginWithIntegerParameters() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail(new Random().nextInt(1000000))
                .setPassword(new Random().nextInt(1000000))
                .setRememberMe(new Random().nextInt(1000000))
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getEmailError()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }

    @Test
    @DisplayName("API POST Test of email login with boolean parameters")
    public void testLoginWithBooleanParameters() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail(true)
                .setPassword(false)
                .setRememberMe(true)
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getEmailError()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }

    @Test
    @DisplayName("API POST Test of email login without email parameter")
    public void testLoginWithoutEmailParameter() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setPassword(PasswordGenerator.generatePassword())
                .setRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, errors.getEmailError().getFirst()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }

    @Test
    @DisplayName("API POST Test of email login without password parameter")
    public void testLoginWithoutPasswordParameter() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail(EmailGenerator.generateValidEmail())
                .setRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getEmailError()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of email login without parameters")
    public void testLoginWithoutParameters() {
        Response response = new LoginWithEmail().performPostRequest(csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(statusCode, 200),
                () -> Assertions.assertNull(errors.getNoUserError()),
                () -> Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, errors.getEmailError().getFirst()),
                () -> Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of email login with long email and password")
    public void testLoginWithLongEmailAndLongPassword() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail(EmailGenerator.generateValidEmail(EmailGenerator.MAX_ALLOWED_LENGTH + new Random().nextInt(EmailGenerator.MAX_ALLOWED_LENGTH)))
                .setPassword(PasswordGenerator.generatePassword(PasswordGenerator.MAX_ALLOWED_LENGTH + new Random().nextInt(PasswordGenerator.MAX_ALLOWED_LENGTH)))
                .setRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getEmailError()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }

    @Test
    @DisplayName("API POST Test of email login with invalid email")
    public void testLoginWithInvalidEmailAndIncorrectPassword() {
        Map<String, Object> formParams = new FormParametersLoginEmail()
                .setEmail(EmailGenerator.generateInvalidEmail())
                .setPassword(PasswordGenerator.generatePassword())
                .setRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, errors.getNoUserError()),
                () -> Assertions.assertNull(errors.getEmailError()),
                () -> Assertions.assertNull(errors.getPasswordError())
        );
    }
}
