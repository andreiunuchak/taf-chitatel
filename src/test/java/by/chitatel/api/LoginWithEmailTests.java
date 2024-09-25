package by.chitatel.api;

import by.chitatel.api.endpoints.LoginWithEmail;
import by.chitatel.api.responses.login.email.EmailLoginErrors;
import by.chitatel.api.utils.Errors;
import by.chitatel.api.utils.FormParameters;
import by.chitatel.api.utils.Responses;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class LoginWithEmailTests extends BaseTest {

    @Test
    public void testLoginEmailOptions() {
        Response response = new LoginWithEmail().performOptionsRequest(csrfToken,cookies);
        String allowedMethods = response.getHeader("Allow");

        Assertions.assertEquals("GET,HEAD,POST", allowedMethods);
    }

    @Test
    public void testLoginWithIncorrectEmailAndPassword() {
        Map<String, Object> formParams = new FormParameters().setEmail("user@email.com").setPassword("123456").setRememberMe("1").build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertEquals("Неправильный e-mail или пароль!", errors.getNoUserError());
        Assertions.assertNull(errors.getEmailError());
        Assertions.assertNull(errors.getPasswordError());
    }

    @Test
    public void testLoginWithEmptyParameters() {
        Map<String, Object> formParams = new FormParameters().setEmail("").setPassword("").setRememberMe("").build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNoUserError());
        Assertions.assertEquals("Вы не указали \"Email\"", errors.getEmailError().getFirst());
        Assertions.assertEquals("Вы не указали \"Пароль\"", errors.getPasswordError().getFirst());
    }

    @Test
    public void testLoginWithIncorrectEmailAndEmptyPassword() {
        Map<String, Object> formParams = new FormParameters().setEmail("user@test.com").setPassword("").setRememberMe("0").build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNoUserError());
        Assertions.assertNull(errors.getEmailError());
        Assertions.assertEquals("Вы не указали \"Пароль\"", errors.getPasswordError().getFirst());
    }

    @Test
    public void testLoginWithEmptyEmailAndIncorrectPassword() {
        Map<String, Object> formParams = new FormParameters().setEmail("").setPassword("123456").setRememberMe("").build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNoUserError());
        Assertions.assertEquals("Вы не указали \"Email\"", errors.getEmailError().getFirst());
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
        Assertions.assertEquals("Вы не указали \"Email\"", errors.getEmailError().getFirst());
        Assertions.assertEquals("Вы не указали \"Пароль\"", errors.getPasswordError().getFirst());
    }

    @Test
    public void testLoginWithIntegerParameters() {
        Map<String, Object> formParams = new FormParameters().setEmail(123456).setPassword(123456).setRememberMe(1).build();
        Response response = new LoginWithEmail().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        EmailLoginErrors errors = Errors.getErrorsFromEmailLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertEquals("Неправильный e-mail или пароль!", errors.getNoUserError());
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
        Assertions.assertEquals("Неправильный e-mail или пароль!", errors.getNoUserError());
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
        Assertions.assertEquals("Вы не указали \"Email\"", errors.getEmailError().getFirst());
        Assertions.assertEquals("Вы не указали \"Пароль\"", errors.getPasswordError().getFirst());
    }
}
