package by.chitatel.api.tests;

import by.chitatel.api.apis.LoginWithPhone;
import by.chitatel.api.responses.login.phone.PhoneLoginErrors;
import by.chitatel.api.utils.Errors;
import by.chitatel.api.utils.Responses;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginWithPhoneTests extends BaseTest {

    @Test
    public void testChitatelPhoneLogin() {
        Response response = new LoginWithPhone("+375(99)1111111", "test", true, csrfToken, cookies).execute();

        int statusCode = Responses.getStatusCodeFromResponse(response);
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        Assertions.assertEquals(statusCode, 200);
        Assertions.assertNull(errors.getNouserError());
        Assertions.assertNull(errors.getPhoneError());
        Assertions.assertEquals("The password phone must be at least 6 characters.", errors.getPasswordError().getFirst());
    }
}
