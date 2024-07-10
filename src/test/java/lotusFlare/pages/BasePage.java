package lotusFlare.pages;

import lotusFlare.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    public BasePage () {

        PageFactory.initElements(Driver.getDriver(),this);

    }


    Wait<WebDriver> wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory ']")
    public List<WebElement> addToCartButton;

    @FindBy(xpath = "//div[@class='inventory_item_label']//a//div")
    public List<WebElement> inventoryItemName;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
    public WebElement shoppingCartBadge;

    @FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory']")
    public WebElement addToCartDetailsPageButton;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> priceTag;

    @FindBy(css = "[class='inventory_details_name large_size']")
    public WebElement inventoryDetailsItemName;

    @FindBy(css = "[class='inventory_details_price']")
    public WebElement inventoryDetailsPriceTag;

    @FindBy(css = "[class='shopping_cart_link']")
    public WebElement shoppingCartButton;

    @FindBy(css = "[class='app_logo']")
    public WebElement basePageHeader;

    @FindBy(css = "[class='inventory_item_img']")
    public WebElement images;

    public void waitForAvailabilityOfWebElement (WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();

    }

    public void waitUntilWebElementIsDisplayed (WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

        Assert.assertTrue(element.isDisplayed());

    }


}
