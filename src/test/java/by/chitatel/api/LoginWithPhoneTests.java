package by.chitatel.api;

import by.chitatel.api.endpoints.LoginWithPhone;
import by.chitatel.api.responses.login.phone.PhoneLoginErrors;
import by.chitatel.api.utils.Errors;
import by.chitatel.api.utils.FormParameters;
import by.chitatel.api.utils.Responses;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class LoginWithPhoneTests extends BaseTest {

    @Test
    public void testLoginPhoneOptions() {
        Response response = new LoginWithPhone().performOptionsRequest(csrfToken,cookies);
        String allowedMethods = response.getHeader("Allow");

        Assertions.assertEquals("POST", allowedMethods);
    }

    @Test
    public void testChitatelPhoneLogin() {
        Map<String, Object> formParams = new FormParameters().setPhone("+375(99)1111111").setPhonePassword("test").setPhoneRememberMe("1").build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNouserError());
        Assertions.assertNull(errors.getPhoneError());
        Assertions.assertEquals("The password phone must be at least 6 characters.", errors.getPasswordError().getFirst());
    }
}
