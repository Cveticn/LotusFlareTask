package lotusFlare.stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lotusFlare.pages.BasePage;
import lotusFlare.pages.LoginPage;
import lotusFlare.pages.ShoppingCartPage;
import lotusFlare.utilities.ConfigPropertiesReader;
import lotusFlare.utilities.Driver;
import org.junit.Assert;

public class AddingItemsStepDefinitions {

    LoginPage loginPage = new LoginPage();

    BasePage basePage = new BasePage();

    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    int randomNumber;

    String itemOneName;

    String priceTagFirstItem;

    int randomNumber2;

    String itemTwoName;

    String priceTagSecondItem;

    Faker faker = new Faker();

    @When("user is successfully logged in to the application")
    public void user_is_successfully_logged_in_to_the_application() {

        Driver.getDriver().get(ConfigPropertiesReader.getProperty("url"));

        loginPage.logIn(ConfigPropertiesReader.getProperty("username"), ConfigPropertiesReader.getProperty("password"));

    }
    @When("user adds and item from the list to the cart")
    public void user_adds_and_item_from_the_list_to_the_cart() {

        //generating random number between 0 and size of the item list/2
        randomNumber = faker.number().numberBetween(0, basePage.inventoryItemName.size()/2);

        System.out.println(randomNumber);

        //using randomly generated number to select different item each time
        basePage.addToCartButton.get(randomNumber).click();

        itemOneName = basePage.inventoryItemName.get(randomNumber).getText();

        priceTagFirstItem = basePage.priceTag.get(randomNumber).getText();

        System.out.println(priceTagFirstItem);


    }
    @Then("verify that cart badge is updated correctly")
    public void verify_that_cart_badge_is_updated_correctly() {

        Assert.assertTrue(basePage.shoppingCartBadge.isDisplayed());

    }
    @Then("user open another item's details page")
    public void user_open_another_item_s_details_page() {

        randomNumber2 = faker.number().numberBetween(basePage.inventoryItemName.size()/2, basePage.inventoryItemName.size());

        //using randomly generated number to select 2nd item from the list.
        basePage.inventoryItemName.get(randomNumber2).click();

        itemTwoName = basePage.inventoryDetailsItemName.getText();

        priceTagSecondItem = basePage.inventoryDetailsPriceTag.getText();

        System.out.println(priceTagSecondItem);

    }
    @Then("user adds the item to the cart")
    public void user_adds_the_item_to_the_cart() {

        basePage.waitForAvailabilityOfWebElement(basePage.addToCartDetailsPageButton);

        //basePage.addToCartDetailsPageButton.click();

        //verifying that shopping cart badge is updating as expected
        Assert.assertEquals("2", basePage.shoppingCartBadge.getText());

    }
    @Then("user opens the cart")
    public void user_opens_the_cart() {

        basePage.shoppingCartBadge.click();

    }
    @Then("verify that the correct items are present")
    public void verify_that_the_correct_items_are_present() {

        //verifying that number of items in the shopping cart is 2
        Assert.assertEquals(2, shoppingCartPage.listOfItemsInCart.size());

        //verifying that first added item is present in shopping cart
        Assert.assertEquals(itemOneName, shoppingCartPage.inventoryItemName.get(0).getText());

        System.out.println(itemOneName);

        //verifying that second added item is present in shopping cart
        Assert.assertEquals(itemTwoName, shoppingCartPage.inventoryItemName.get(1).getText());

        //verifying that correct price is shown for first item
        Assert.assertEquals(priceTagFirstItem, shoppingCartPage.shoppingCartPriceTag.get(0).getText());


        //verifying that correct price is shown for second item
        Assert.assertEquals(priceTagSecondItem, shoppingCartPage.shoppingCartPriceTag.get(1).getText());



    }


}
