package by.chitatel.api;

import by.chitatel.api.endpoints.Feedback;
import by.chitatel.api.responses.feedback.FeedbackErrors;
import by.chitatel.api.utils.Errors;
import by.chitatel.api.utils.FormParametersFeedback;
import by.chitatel.constants.ErrorMessages;
import by.chitatel.generators.PhoneGenerator;
import by.chitatel.generators.StringGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@Epic("API Tests")
@Feature("API Tests of feedback")
public class FeedbackTest extends BaseTest {

    @Test
    @DisplayName("API POST Test of sending feedback without user name")
    public void testFeedbackWithoutName() {
        Map<String, Object> formParams = new FormParametersFeedback()
                .setPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setTheme(StringGenerator.generateString(20))
                .setMessage(StringGenerator.generateString(30))
                .build();
        Response response = new Feedback().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.CONTACTS_NAME_WAS_NOT_INPUT, errors.getMessageNameError().getFirst()),
                () -> Assertions.assertNull(errors.getMessagePhoneError()),
                () -> Assertions.assertNull(errors.getMessageNoteError())
        );
    }

    @Test
    @DisplayName("API POST Test of sending feedback without phone number")
    public void testFeedbackWithoutPhoneNumber() {
        Map<String, Object> formParams = new FormParametersFeedback()
                .setName(StringGenerator.generateString(7))
                .setTheme(StringGenerator.generateString(20))
                .setMessage(StringGenerator.generateString(30))
                .build();
        Response response = new Feedback().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getMessageNameError()),
                () -> Assertions.assertEquals(ErrorMessages.CONTACTS_PHONE_WAS_NOT_INPUT, errors.getMessagePhoneError().getFirst()),
                () -> Assertions.assertNull(errors.getMessageNoteError())
        );
    }

    @Test
    @DisplayName("API POST Test of sending feedback without message note")
    public void testFeedbackWithoutMessage() {
        Map<String, Object> formParams = new FormParametersFeedback()
                .setName(StringGenerator.generateString(7))
                .setPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberFullFormatted())
                .setTheme(StringGenerator.generateString(20))
                .build();
        Response response = new Feedback().performPostRequest(formParams, csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertNull(errors.getMessageNameError()),
                () -> Assertions.assertNull(errors.getMessagePhoneError()),
                () -> Assertions.assertEquals(ErrorMessages.CONTACTS_MESSAGE_WAS_NOT_INPUT, errors.getMessageNoteError().getFirst())
        );
    }

    @Test
    @DisplayName("API POST Test of sending feedback without parameters")
    public void testFeedbackWithoutParams() {
        Response response = new Feedback().performPostRequest(csrfToken, cookies);
        int statusCode = response.statusCode();
        FeedbackErrors errors = Errors.getErrorsFromFeedbackResponse(response);

        Assertions.assertAll(
                () -> Assertions.assertEquals(200, statusCode),
                () -> Assertions.assertEquals(ErrorMessages.CONTACTS_NAME_WAS_NOT_INPUT, errors.getMessageNameError().getFirst()),
                () -> Assertions.assertEquals(ErrorMessages.CONTACTS_PHONE_WAS_NOT_INPUT, errors.getMessagePhoneError().getFirst()),
                () -> Assertions.assertEquals(ErrorMessages.CONTACTS_MESSAGE_WAS_NOT_INPUT, errors.getMessageNoteError().getFirst())
        );
    }
}
