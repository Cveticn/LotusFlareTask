package lotusFlare.stepDefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import lotusFlare.pages.BasePage;
import lotusFlare.pages.LoginPage;
import lotusFlare.utilities.ConfigPropertiesReader;
import lotusFlare.utilities.Driver;
import org.junit.Assert;

public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();

    BasePage basePage = new BasePage();

    @When("user is on the login page")
    public void user_is_on_the_login_page() {

        Driver.getDriver().get(ConfigPropertiesReader.getProperty("url"));

    }
    @When("user enters valid username")
    public void user_enters_valid_username() {

        loginPage.userNameInputBox.sendKeys(ConfigPropertiesReader.getProperty("username"));

    }
    @When("user enters valid password")
    public void user_enters_valid_password() {

        loginPage.passwordInputBox.sendKeys(ConfigPropertiesReader.getProperty("password"));

    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {

        loginPage.loginButton.click();

    }
    @Then("verify that user is successfully logged into the application")
    public void verify_that_user_is_successfully_logged_into_the_application() {

        //asserting url
        Assert.assertEquals("https://www.saucedemo.com/inventory.html", Driver.getDriver().getCurrentUrl());
        //asserting title of the page
        Assert.assertEquals("Swag Labs", basePage.basePageHeader.getText());

    }

    @When("locked out user enters username")
    public void locked_out_user_enters_username() {

        loginPage.userNameInputBox.sendKeys(ConfigPropertiesReader.getProperty("lockedout_username"));

    }

    @When("locked out user enters password")
    public void locked_out_user_enters_password() {

        loginPage.passwordInputBox.sendKeys(ConfigPropertiesReader.getProperty("lockedout_password"));

    }

    @Then("verify that error message is displayed correctly")
    public void verify_that_error_message_is_displayed_correctly() {
        //asserting error message
        Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", loginPage.errorMessage.getText());


    }


}
