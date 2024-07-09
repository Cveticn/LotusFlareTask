package lotusFlare.stepDefinitions;

import io.cucumber.java.en.Then;
import lotusFlare.pages.BasePage;
import lotusFlare.pages.ShoppingCartPage;
import org.junit.Assert;

public class RemovingItemsStepDefinitions {

    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    BasePage basePage = new BasePage();


    @Then("user removes first item from the shopping cart")
    public void user_removes_first_item_from_the_shopping_cart() {

        //removing first item from the cart
        shoppingCartPage.removeFirstItemButton.click();

        //verifying that shopping cart badge is updated as expected
        Assert.assertEquals("1", basePage.shoppingCartBadge.getText());

    }
    @Then("verify that 2nd added item is present in the shopping cart")
    public void verify_that_2nd_added_item_is_present_in_the_shopping_cart() {

        //verifying that number of items in shopping cart is 1
        Assert.assertEquals(1, shoppingCartPage.listOfItemsInCart.size());

    }

}
