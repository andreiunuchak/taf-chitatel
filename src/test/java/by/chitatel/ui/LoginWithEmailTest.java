package by.chitatel.ui;

import by.chitatel.ui.modals.ErrorsModal;
import by.chitatel.ui.pages.ChitatelPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginWithEmailTest extends BaseTest{

    @Test
    public void testLoginWithIncorrectEmailAndPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail("user@test.com")
                .inputPassword("123456")
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = "Неправильный e-mail или пароль!";

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithIncorrectEmailAndEmptyPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail("user@test.com")
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = "Вы не указали \"Пароль\"";

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithEmptyEmailAndIncorrectPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputPassword("123456")
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = "Вы не указали \"Email\"";

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithEmptyEmailAndEmptyPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = "Вы не указали \"Email\"\nВы не указали \"Пароль\"";

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithInvalidEmailAndIncorrectPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .inputEmail("user@test.")
                .inputPassword("123456")
                .clickLoginButton();
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = "Неправильный e-mail или пароль!";

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void testLoginWithLongEmailAndLongPassword(){
        new ChitatelPage(driver)
                .openPage()
                .clickLoginButton()
                .clickTabEmail()
                .performLogin("incorrectemail".repeat(50)+"@gmail.com", "1a@2b#3c%".repeat(50), false);
        String actualErrorMessage = new ErrorsModal(driver).getErrorMessage();
        String expectedErrorMessage = "Неправильный e-mail или пароль!";

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
