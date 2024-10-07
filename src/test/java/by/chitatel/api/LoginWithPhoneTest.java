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
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;
import java.util.Random;

@Epic(EpicNames.API)
@Feature(FeatureNames.API_LOGIN_PHONE)
public class LoginWithPhoneTest extends BaseTest {

    @Test(description = "API OPTIONS Test of allowed methods")
    public void testLoginPhoneOptions() {
        Response response = new LoginWithPhone().performOptionsRequest(csrfToken, cookies);
        String allowedMethods = response.getHeader(HttpHeaders.ALLOW);

        Assert.assertEquals(allowedMethods, "POST");
    }

    @Test(description = "API POST Test of phone login with not formatted phone number")
    public void testPhoneLoginWithValidNotFormattedPhone() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateIncorrectPhoneNumber().getPhoneNumberFull())
                .setPhonePassword(PasswordGenerator.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertEquals(errors.getNoUserError(), ErrorMessages.PHONE_AND_PASSWORD_ARE_WRONG);
        softAssert.assertNull(errors.getPhoneError());
        softAssert.assertNull(errors.getPasswordError());
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login without country and operator codes")
    public void testPhoneLoginWithPhoneThatDoesNotHaveCodes() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateIncorrectPhoneNumber().getPhoneNumber())
                .setPhonePassword(PasswordGenerator.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertEquals(errors.getNoUserError(), ErrorMessages.PHONE_AND_PASSWORD_ARE_WRONG);
        softAssert.assertNull(errors.getPhoneError());
        softAssert.assertNull(errors.getPasswordError());
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login with short password")
    public void testPhoneLoginWithValidPhoneAndShortPassword() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateIncorrectPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(PasswordGenerator.generatePassword(PasswordGenerator.MIN_ALLOWED_LENGTH - 1))
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertNull(errors.getNoUserError());
        softAssert.assertNull(errors.getPhoneError());
        softAssert.assertEquals(errors.getPasswordError().getFirst(), ErrorMessages.PASSWORD_IS_TOO_SHORT);
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login with long password")
    public void testPhoneLoginWithValidPhoneAndLongPassword() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateIncorrectPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(PasswordGenerator.generatePassword(PasswordGenerator.MAX_ALLOWED_LENGTH + 1))
                .setPhoneRememberMe(RememberMeCodes.SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertNull(errors.getNoUserError());
        softAssert.assertNull(errors.getPhoneError());
        softAssert.assertEquals(errors.getPasswordError().getFirst(), ErrorMessages.PASSWORD_IS_TOO_LONG);
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login with mock phone number and incorrect password")
    public void testPhoneLoginWithMockPhoneAndIncorrectPassword() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(PasswordGenerator.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertEquals(errors.getNoUserError(), ErrorMessages.PASSWORD_DOES_NOT_MATCH);
        softAssert.assertNull(errors.getPhoneError());
        softAssert.assertNull(errors.getPasswordError());
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login with mock phone number and empty password")
    public void testPhoneLoginWithMockPhoneAndEmptyPassword() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword("")
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertNull(errors.getNoUserError());
        softAssert.assertNull(errors.getPhoneError());
        softAssert.assertEquals(errors.getPasswordError().getFirst(), ErrorMessages.PASSWORD_WAS_NOT_INPUT);
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login with empty parameters")
    public void testPhoneLoginWithEmptyParams() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone("")
                .setPhonePassword("")
                .setPhoneRememberMe("")
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertNull(errors.getNoUserError());
        softAssert.assertEquals(errors.getPhoneError().getFirst(), ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT);
        softAssert.assertEquals(errors.getPasswordError().getFirst(), ErrorMessages.PASSWORD_WAS_NOT_INPUT);
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login with boolean parameters")
    public void testPhoneLoginWithBooleanParams() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(true)
                .setPhonePassword(true)
                .setPhoneRememberMe(false)
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertNull(errors.getNoUserError());
        softAssert.assertNull(errors.getPhoneError());
        softAssert.assertEquals(errors.getPasswordError().getFirst(), ErrorMessages.PASSWORD_IS_TOO_SHORT);
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login with integer parameters")
    public void testPhoneLoginWithIntegerParams() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(new Random().nextInt(1000000))
                .setPhonePassword(new Random().nextInt(1000000))
                .setPhoneRememberMe(new Random().nextInt(1000000))
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertEquals(errors.getNoUserError(), ErrorMessages.PHONE_AND_PASSWORD_ARE_WRONG);
        softAssert.assertNull(errors.getPhoneError());
        softAssert.assertNull(errors.getPasswordError());
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login with 'null' parameters")
    public void testPhoneLoginWithNullParams() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(null)
                .setPhonePassword(null)
                .setPhoneRememberMe(null)
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertNull(errors.getNoUserError());
        softAssert.assertEquals(errors.getPhoneError().getFirst(), ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT);
        softAssert.assertEquals(errors.getPasswordError().getFirst(), ErrorMessages.PASSWORD_WAS_NOT_INPUT);
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login without parameters")
    public void testPhoneLoginWithoutParams() {
        Response response = new LoginWithPhone().performPostRequest(csrfToken, cookies);
        int statusCode = response.statusCode();
        PhoneLoginErrors errors = Errors.getErrorsFromPhoneLoginResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertNull(errors.getNoUserError());
        softAssert.assertEquals(errors.getPhoneError().getFirst(), ErrorMessages.PHONE_NUMBER_WAS_NOT_INPUT);
        softAssert.assertEquals(errors.getPasswordError().getFirst(), ErrorMessages.PASSWORD_WAS_NOT_INPUT);
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of phone login without scrf-token")
    public void testPhoneLoginWithoutToken() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(PasswordGenerator.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, cookies);
        int statusCode = response.statusCode();

        Assert.assertEquals(statusCode, 419);
    }

    @Test(description = "API POST Test of phone login without cookies")
    public void testPhoneLoginWithoutCookies() {
        Map<String, Object> formParams = new FormParametersLoginPhone()
                .setPhone(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setPhonePassword(PasswordGenerator.generatePassword())
                .setPhoneRememberMe(RememberMeCodes.NOT_SELECTED.getCode())
                .build();
        Response response = new LoginWithPhone().performPostRequest(formParams, csrfToken);
        int statusCode = response.statusCode();

        Assert.assertEquals(statusCode, 419);
    }
}
