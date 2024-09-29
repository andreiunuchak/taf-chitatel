package by.chitatel.api;

import by.chitatel.api.endpoints.LoginWithEmail;
import by.chitatel.api.responses.login.email.EmailLoginErrors;
import by.chitatel.api.utils.Errors;
import by.chitatel.api.utils.FormParameters;
import by.chitatel.constants.ErrorMessages;
import by.chitatel.generators.Emails;
import by.chitatel.generators.Passwords;
import by.chitatel.generators.enums.RememberMeCodes;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Random;

public class LoginWithEmailTest extends BaseTest {

    @Test
    public void testLoginEmailOptions() {
        Response response = new LoginWithEmail().performOptionsRequest(csrfToken, cookies);
        String allowedMethods = response.getHeader("Allow");

        Assertions.assertEquals("GET,HEAD,POST", allowedMethods);
    }

    @Test
    public void testGetRequest() {
        String email = Emails.generateValidEmail();
        String password = Passwords.generatePassword();
        String rememberMe = RememberMeCodes.SELECTED.getCode();

        Map<String, Object> formParams = new FormParameters().setEmail(email).setPassword(password).setRememberMe(rememberMe).build();
        Response response = new LoginWithEmail().performGetRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();

        Assertions.assertEquals(statusCode, 200);
    }

    @Test
    public void testHeadRequest() {
        String email = Emails.generateValidEmail();
        String password = Passwords.generatePassword();
        String rememberMe = RememberMeCodes.SELECTED.getCode();

        Map<String, Object> formParams = new FormParameters().setEmail(email).setPassword(password).setRememberMe(rememberMe).build();
        Response response = new LoginWithEmail().performHeadRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();

        Assertions.assertEquals(statusCode, 200);
    }

    @Test
    public void testLoginWithIncorrectEmailAndPassword() {
        String email = Emails.generateValidEmail();
        String password = Passwords.generatePassword();
        String rememberMe = RememberMeCodes.SELECTED.getCode();

        Map<String, Object> formParams = new FormParameters().setEmail(email).setPassword(password).setRememberMe(rememberMe).build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, errors.getNoUserError());
        Assertions.assertNull(errors.getEmailError());
        Assertions.assertNull(errors.getPasswordError());
    }

    @Test
    public void testLoginWithEmptyParameters() {
        String email = "";
        String password = "";
        String rememberMe = "";

        Map<String, Object> formParams = new FormParameters().setEmail(email).setPassword(password).setRememberMe(rememberMe).build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNoUserError());
        Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, errors.getEmailError().getFirst());
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst());
    }

    @Test
    public void testLoginWithIncorrectEmailAndEmptyPassword() {
        String email = Emails.generateValidEmail();
        String password = "";
        String rememberMe = RememberMeCodes.NOT_SELECTED.getCode();

        Map<String, Object> formParams = new FormParameters().setEmail(email).setPassword(password).setRememberMe(rememberMe).build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNoUserError());
        Assertions.assertNull(errors.getEmailError());
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst());
    }

    @Test
    public void testLoginWithEmptyEmailAndIncorrectPassword() {
        String email = "";
        String password = Passwords.generatePassword();
        String rememberMe = RememberMeCodes.SELECTED.getCode();

        Map<String, Object> formParams = new FormParameters().setEmail(email).setPassword(password).setRememberMe(rememberMe).build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNoUserError());
        Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, errors.getEmailError().getFirst());
        Assertions.assertNull(errors.getPasswordError());
    }

    @Test
    public void testLoginWithNullParameters() {
        Map<String, Object> formParams = new FormParameters().setEmail(null).setPassword(null).setRememberMe(null).build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNoUserError());
        Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, errors.getEmailError().getFirst());
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst());
    }

    @Test
    public void testLoginWithIntegerParameters() {
        int email = new Random().nextInt(1000000);
        int password = new Random().nextInt(1000000);
        int rememberMe = new Random().nextInt(1000000);

        Map<String, Object> formParams = new FormParameters().setEmail(email).setPassword(password).setRememberMe(rememberMe).build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, errors.getNoUserError());
        Assertions.assertNull(errors.getEmailError());
        Assertions.assertNull(errors.getPasswordError());
    }

    @Test
    public void testLoginWithBooleanParameters() {
        Map<String, Object> formParams = new FormParameters().setEmail(true).setPassword(false).setRememberMe(true).build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertEquals(ErrorMessages.EMAIL_AND_PASSWORD_ARE_WRONG, errors.getNoUserError());
        Assertions.assertNull(errors.getEmailError());
        Assertions.assertNull(errors.getPasswordError());
    }

    @Test
    public void testLoginWithoutParameters() {
        Response response = new LoginWithEmail().performPostRequest(csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNoUserError());
        Assertions.assertEquals(ErrorMessages.EMAIL_WAS_NOT_INPUT, errors.getEmailError().getFirst());
        Assertions.assertEquals(ErrorMessages.PASSWORD_WAS_NOT_INPUT, errors.getPasswordError().getFirst());
    }
}
