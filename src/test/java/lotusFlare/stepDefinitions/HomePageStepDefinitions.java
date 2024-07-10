package lotusFlare.stepDefinitions;

import io.cucumber.java.en.Then;
import lotusFlare.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class HomePageStepDefinitions {

    BasePage basePage = new BasePage();


    @Then("verify that images are displayed to the user")
    public void verify_that_images_are_displayed_to_the_user() throws InterruptedException {

        basePage.waitUntilWebElementIsDisplayed(basePage.images);

        Assert.assertTrue(basePage.images.isDisplayed());

    }

}
