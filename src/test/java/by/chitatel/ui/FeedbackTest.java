package by.chitatel.ui;

import by.chitatel.constants.ErrorMessages;
import by.chitatel.generators.PhoneGenerator;
import by.chitatel.generators.StringGenerator;
import by.chitatel.names.EpicNames;
import by.chitatel.names.FeatureNames;
import by.chitatel.ui.modals.ErrorDialogPage;
import by.chitatel.ui.modals.TopMenuPage;
import by.chitatel.ui.pages.HomePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic(EpicNames.UI)
@Feature(FeatureNames.UI_FEEDBACK)
public class FeedbackTest extends BaseTest {

    @Test(description = "UI Test of sending feedback without user name")
    public void testFeedbackWithoutName() {
        new HomePage().openPage();
        new TopMenuPage()
                .clickMenuContactsButton()
                .inputPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .inputTheme(StringGenerator.generateString(20))
                .inputMessage(StringGenerator.generateString(50))
                .clickSendButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.CONTACTS_NAME_WAS_NOT_INPUT);
    }

    @Test(description = "UI Test of sending feedback without phone number")
    public void testFeedbackWithoutPhoneNumber() {
        new HomePage().openPage();
        new TopMenuPage()
                .clickMenuContactsButton()
                .inputName(StringGenerator.generateString(7))
                .inputTheme(StringGenerator.generateString(20))
                .inputMessage(StringGenerator.generateString(50))
                .clickSendButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.CONTACTS_PHONE_WAS_NOT_INPUT);
    }


    @Test(description = "UI Test of sending feedback without message note")
    public void testFeedbackWithoutMessage() {
        new HomePage().openPage();
        new TopMenuPage()
                .clickMenuContactsButton()
                .inputName(StringGenerator.generateString(7))
                .inputPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .inputTheme(StringGenerator.generateString(20))
                .clickSendButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.CONTACTS_MESSAGE_WAS_NOT_INPUT);
    }

    @Test(description = "UI Test of sending feedback without user name and phone number")
    public void testFeedbackWithoutNameAndPhoneNumber() {
        new HomePage().openPage();
        new TopMenuPage()
                .clickMenuContactsButton()
                .inputTheme(StringGenerator.generateString(20))
                .inputMessage(StringGenerator.generateString(50))
                .clickSendButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.CONTACTS_NAME_AND_PHONE_WERE_NOT_INPUT);
    }

    @Test(description = "UI Test of sending feedback without user name and message note")
    public void testFeedbackWithoutNameAndMessage() {
        new HomePage().openPage();
        new TopMenuPage()
                .clickMenuContactsButton()
                .inputPhoneNumber(PhoneGenerator.generateMockPhoneNumber().getPhoneNumberWithOperatorCode())
                .inputTheme(StringGenerator.generateString(20))
                .clickSendButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.CONTACTS_NAME_AND_MESSAGE_WERE_NOT_INPUT);
    }

    @Test(description = "UI Test of sending feedback without phone number and message note")
    public void testFeedbackWithoutPhoneNumberAndMessage() {
        new HomePage().openPage();
        new TopMenuPage()
                .clickMenuContactsButton()
                .inputName(StringGenerator.generateString(7))
                .inputTheme(StringGenerator.generateString(20))
                .clickSendButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.CONTACTS_PHONE_AND_MESSAGE_WERE_NOT_INPUT);
    }

    @Test(description = "UI Test of sending feedback with empty fields")
    public void testFeedbackEmptyForm() {
        new HomePage().openPage();
        new TopMenuPage()
                .clickMenuContactsButton()
                .clickSendButton();
        Assert.assertEquals(new ErrorDialogPage().getErrorMessage(), ErrorMessages.CONTACTS_NAME_AND_PHONE_AND_MESSAGE_WERE_NOT_INPUT);
    }
}
