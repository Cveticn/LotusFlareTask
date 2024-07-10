package lotusFlare.stepDefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lotusFlare.pages.BasePage;
import lotusFlare.pages.ShoppingCartPage;
import org.junit.Assert;

public class CheckoutStepDefinitions {

    Faker faker = new Faker();

    BasePage basePage = new BasePage();

    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

    int randomNumber;

    int randomNumber2;

    String firstItemName, secondItemName, firstItemPriceTag, secondItemPriceTag;

    double firstItemPriceDouble, secondItemPriceDouble, itemTotalAmountDouble, totalTaxDouble, totalDouble;


    @When("user adds first item to the cart")
    public void user_adds_first_item_to_the_cart() {

        randomNumber = faker.number().numberBetween(0,basePage.addToCartButton.size()/2);

        System.out.println(randomNumber);

        //getting the name of the added item
        firstItemName = basePage.inventoryItemName.get(randomNumber).getText();

        System.out.println(firstItemName);

        //getting price of the added item
        firstItemPriceTag = basePage.priceTag.get(randomNumber).getText();

        System.out.println(firstItemPriceTag);

        basePage.addToCartButton.get(randomNumber).click();





    }
    @When("user adds second item to the cart")
    public void user_adds_second_item_to_the_cart(){

        randomNumber2 = faker.number().numberBetween(basePage.addToCartButton.size()/2, basePage.addToCartButton.size());

        basePage.inventoryItemName.get(randomNumber2).click();
        System.out.println(randomNumber2);

        secondItemName = basePage.inventoryDetailsItemName.getText();

        secondItemPriceTag = basePage.inventoryDetailsPriceTag.getText();

        System.out.println(secondItemPriceTag);

        //basePage.addToCartDetailsPageButton.click();

        basePage.waitForAvailabilityOfWebElement(basePage.addToCartDetailsPageButton);

    }
    @When("user clicks on shopping cart")
    public void user_clicks_on_shopping_cart() {

        basePage.shoppingCartButton.click();

    }
    @When("user clicks checkout button")
    public void user_clicks_checkout_button() {

        shoppingCartPage.checkoutButton.click();

    }
    @When("user enters first name")
    public void user_enters_first_name() {

        //generating random first name using javafaker library
        shoppingCartPage.firstNameInputBox.sendKeys(faker.name().firstName());

    }
    @When("user enters last name")
    public void user_enters_last_name() {

        //generating random last name using javafaker library
        shoppingCartPage.lastNameInputBox.sendKeys(faker.name().lastName());

    }
    @When("user enters postal code")
    public void user_enters_postal_code() {

        //generating random postal code using javafaker library
        shoppingCartPage.postalCodeInputBox.sendKeys(faker.address().zipCode());

    }
    @When("user clicks on continue button")
    public void user_clicks_on_continue_button(){

        shoppingCartPage.continueButton.click();

        //verifying that number of items in cart is 2
        Assert.assertEquals(2, shoppingCartPage.listOfItemsInCart.size());

        //verifying that first item is in the shopping cart
        Assert.assertEquals(firstItemName, shoppingCartPage.inventoryItemName.get(0).getText());

        //verifying that first items' price is correct
        Assert.assertEquals(firstItemPriceTag, shoppingCartPage.shoppingCartPriceTag.get(0).getText());

        //verifying that second item is in the shopping cart
        Assert.assertEquals(secondItemName, shoppingCartPage.inventoryItemName.get(1).getText());

        //verifying that second items' price is correct
        Assert.assertEquals(secondItemPriceTag, shoppingCartPage.shoppingCartPriceTag.get(1).getText());

    }
    @When("user clicks on finish button")
    public void user_clicks_on_finish_button() {

        shoppingCartPage.finishButton.click();

    }
    @Then("verify that order is completed successfully and message is displayed correctly")
    public void verify_that_order_is_completed_successfully_and_message_is_displayed_correctly() {

        //verifying that image is displayed
        Assert.assertTrue(shoppingCartPage.verificationImage.isDisplayed());

        //verifying that expected message is displayed
        Assert.assertEquals("Thank you for your order!", shoppingCartPage.successfulMessage.getText());

        //verifying that expected text is displayed
        Assert.assertEquals("Your order has been dispatched, and will arrive just as fast as the pony can get there!", shoppingCartPage.successfulMessage2.getText());


    }

    @Then("verify that prices of the items are added up correctly")
    public void verify_that_prices_of_the_items_are_added_up_correctly() throws InterruptedException {

        double delta = 0.01;

        //getting only digits with substring method
        String itemPrice1 = shoppingCartPage.itemPrices.get(0).getText().substring(1);

        //parsing price of first item into double to perform math operations
        firstItemPriceDouble = Double.parseDouble(itemPrice1);

        //getting only digits with substring method
        String itemPrice2 = shoppingCartPage.itemPrices.get(1).getText().substring(1);

        secondItemPriceDouble = Double.parseDouble(itemPrice2);

        double itemAddition = firstItemPriceDouble + secondItemPriceDouble;

        System.out.println(itemAddition);

        //parsing item total amount to get only digits
        String itemTotal = shoppingCartPage.itemTotalAmount.getText().substring(13);

        itemTotalAmountDouble = Double.parseDouble(itemTotal);

        //parsing tax to get only digits
        String tax = shoppingCartPage.taxAmount.getText().substring(6);

        totalTaxDouble = Double.parseDouble(tax);

        //parsing total amount to get only digits
        String total = shoppingCartPage.totalAmount.getText().substring(8);

        totalDouble = Math.round(Double.parseDouble(total));

        //getting double value by adding sum of items + total tax, rounding to 2 decimals
        double itemTotalsPlusTax = Math.round(itemAddition + totalTaxDouble);

        //verifying that sum of two items in a cart are equal to Item Total on the Checkout Page
        Assert.assertEquals(itemAddition, itemTotalAmountDouble, delta );

        //Verifying that sum of two items + tax amount is equal to total amount displayed on the Checkout Page
        Assert.assertEquals(itemTotalsPlusTax, totalDouble, delta);












    }

}
