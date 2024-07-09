package lotusFlare.pages;

import lotusFlare.utilities.ConfigPropertiesReader;
import lotusFlare.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage () {

        PageFactory.initElements(Driver.getDriver(),this);

    }

    @FindBy(id = "user-name")
    public WebElement userNameInputBox;

    @FindBy(id = "password")
    public WebElement passwordInputBox;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    public  WebElement errorMessage;

    public void logIn (String username, String password) {

        userNameInputBox.sendKeys(username);

        passwordInputBox.sendKeys(password);

        loginButton.click();

    }

}
