package by.chitatel.api;

import by.chitatel.api.endpoints.Feedback;
import by.chitatel.api.responses.feedback.FeedbackErrors;
import by.chitatel.api.utils.Errors;
import by.chitatel.api.utils.FormParametersFeedback;
import by.chitatel.constants.ErrorMessages;
import by.chitatel.generators.PhoneGenerator;
import by.chitatel.generators.StringGenerator;
import by.chitatel.names.EpicNames;
import by.chitatel.names.FeatureNames;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

@Epic(EpicNames.API)
@Feature(FeatureNames.API_FEEDBACK)
public class FeedbackTest extends BaseTest {

    @Test(description = "API POST Test of sending feedback without user name")
    @Severity(SeverityLevel.CRITICAL)
    public void testFeedbackWithoutName() {
        Map<String, Object> formParams = new FormParametersFeedback()
                .setPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setTheme(StringGenerator.generateString(20))
                .setMessage(StringGenerator.generateString(30))
                .build();
        Response response = new Feedback().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertEquals(errors.getMessageNameError().getFirst(), ErrorMessages.CONTACTS_NAME_WAS_NOT_INPUT);
        softAssert.assertNull(errors.getMessagePhoneError());
        softAssert.assertNull(errors.getMessageNoteError());
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of sending feedback without phone number")
    public void testFeedbackWithoutPhoneNumber() {
        Map<String, Object> formParams = new FormParametersFeedback()
                .setName(StringGenerator.generateString(7))
                .setTheme(StringGenerator.generateString(20))
                .setMessage(StringGenerator.generateString(30))
                .build();
        Response response = new Feedback().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertNull(errors.getMessageNameError());
        softAssert.assertEquals(errors.getMessagePhoneError().getFirst(), ErrorMessages.CONTACTS_PHONE_WAS_NOT_INPUT);
        softAssert.assertNull(errors.getMessageNoteError());
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of sending feedback without message note")
    public void testFeedbackWithoutMessage() {
        Map<String, Object> formParams = new FormParametersFeedback()
                .setName(StringGenerator.generateString(7))
                .setPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setTheme(StringGenerator.generateString(20))
                .build();
        Response response = new Feedback().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertNull(errors.getMessageNameError());
        softAssert.assertNull(errors.getMessagePhoneError());
        softAssert.assertEquals(errors.getMessageNoteError().getFirst(), ErrorMessages.CONTACTS_MESSAGE_WAS_NOT_INPUT);
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of sending feedback without user name and phone number")
    public void testFeedbackWithoutNameAndPhone() {
        Map<String, Object> formParams = new FormParametersFeedback()
                .setTheme(StringGenerator.generateString(20))
                .setMessage(StringGenerator.generateString(50))
                .build();
        Response response = new Feedback().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertEquals(errors.getMessageNameError().getFirst(), ErrorMessages.CONTACTS_NAME_WAS_NOT_INPUT);
        softAssert.assertEquals(errors.getMessagePhoneError().getFirst(), ErrorMessages.CONTACTS_PHONE_WAS_NOT_INPUT);
        softAssert.assertNull(errors.getMessageNoteError());
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of sending feedback without user name and message note")
    public void testFeedbackWithoutNameAndMessage() {
        Map<String, Object> formParams = new FormParametersFeedback()
                .setPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setTheme(StringGenerator.generateString(20))
                .build();
        Response response = new Feedback().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertEquals(errors.getMessageNameError().getFirst(), ErrorMessages.CONTACTS_NAME_WAS_NOT_INPUT);
        softAssert.assertNull(errors.getMessagePhoneError());
        softAssert.assertEquals(errors.getMessageNoteError().getFirst(), ErrorMessages.CONTACTS_MESSAGE_WAS_NOT_INPUT);
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of sending feedback without phone number and message note")
    public void testFeedbackWithoutPhoneAndMessage() {
        Map<String, Object> formParams = new FormParametersFeedback()
                .setName(StringGenerator.generateString(7))
                .setTheme(StringGenerator.generateString(20))
                .build();
        Response response = new Feedback().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertNull(errors.getMessageNameError());
        softAssert.assertEquals(errors.getMessagePhoneError().getFirst(), ErrorMessages.CONTACTS_PHONE_WAS_NOT_INPUT);
        softAssert.assertEquals(errors.getMessageNoteError().getFirst(), ErrorMessages.CONTACTS_MESSAGE_WAS_NOT_INPUT);
        softAssert.assertAll();
    }

    @Test(description = "API POST Test of sending feedback without parameters")
    public void testFeedbackWithoutParams() {
        Response response = new Feedback().performPostRequest(csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, 200);
        softAssert.assertEquals(errors.getMessageNameError().getFirst(), ErrorMessages.CONTACTS_NAME_WAS_NOT_INPUT);
        softAssert.assertEquals(errors.getMessagePhoneError().getFirst(), ErrorMessages.CONTACTS_PHONE_WAS_NOT_INPUT);
        softAssert.assertEquals(errors.getMessageNoteError().getFirst(), ErrorMessages.CONTACTS_MESSAGE_WAS_NOT_INPUT);
        softAssert.assertAll();
    }
}
