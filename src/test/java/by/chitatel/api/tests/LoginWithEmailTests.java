package by.chitatel.api.tests;

import by.chitatel.api.apis.LoginWithEmail;
import by.chitatel.api.utils.FormParameters;
import by.chitatel.api.utils.Responses;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class LoginWithEmailTests extends BaseTest {

    @Test
    public void testLoginWithIncorrectEmailAndPassword() {
        Map<String, Object> formParams = new FormParameters().setEmail("user@email.com").setPassword("123456").setRememberMe("1").build();
        Response response = new LoginWithEmail().performRequestWithParamsTokenCookies(formParams, csrfToken, cookies);

        int statusCode = Responses.getStatusCodeFromResponse(response);
        String actualMessage = Responses.getValueFromResponse(response, "errors.nouser");

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertEquals(actualMessage, "Неправильный e-mail или пароль!");
    }
}
