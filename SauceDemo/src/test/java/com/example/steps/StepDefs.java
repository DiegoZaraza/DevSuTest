package com.example.steps;

import com.example.pageObject.*;
import com.example.utilities.PropertiesRead;
import com.github.javafaker.Faker;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class StepDefs {
    private static final String PAGE = PropertiesRead.readFromFrameworkConfig("URL");
    private WebDriver webDriver;
    private SoftAssertions softAssertions;
    private Faker faker;
    private LoginPageObject loginPageObject;
    private InventoryPageObject inventoryPageObject;
    private CheckoutStepTwo checkoutStepTwo = new CheckoutStepTwo(webDriver);
    private CartPageObject cartPageObject;
    private CheckoutComplete checkoutComplete;
    private CheckoutStepOne checkoutStepOne;
    private List<SelectedItem> items;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        webDriver = WebDriverManager.chromedriver().create();
        loginPageObject = new LoginPageObject(webDriver);
        inventoryPageObject = new InventoryPageObject(webDriver);
        cartPageObject = new CartPageObject(webDriver);
        checkoutStepOne = new CheckoutStepOne(webDriver);
        checkoutStepTwo = new CheckoutStepTwo(webDriver);
        checkoutComplete = new CheckoutComplete(webDriver);
        softAssertions = new SoftAssertions();

        faker = new Faker();
        webDriver.manage().window().maximize();
    }

    @After
    public void end() {
        softAssertions.assertAll();
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Given("User navigates to the Sauce Labs website")
    public void userNavigatesToTheSauceLabsWebsite() {
        BasePage.setImplicitlyWait();
        webDriver.get(PAGE);
    }

    @And("User logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        loginPageObject.loginPage("standard_user","secret_sauce");
    }

    @When("User selects two products with the same price")
    public void userSelectsTwoProductsWithTheSamePrice() {
        items = InventoryPageObject.addItemsWithSamePriceToCart();
    }

    @And("User proceeds to the cart for order confirmation")
    public void userProceedsToTheCartForOrderConfirmation() {
        List<SelectedItem> list = cartPageObject.verifyPriceOfFirstItem();
        for(int i = 0; i < list.size(); i++){
            softAssertions.assertThat(list.get(i).getProductName()).isEqualTo(items.get(i).getProductName());
            softAssertions.assertThat(list.get(i).getDescription()).isEqualTo(items.get(i).getDescription());
            softAssertions.assertThat(list.get(i).getPrice()).isEqualTo(items.get(i).getPrice());
        }
    }

    @And("User enters valid personal information")
    public void userEntersValidPersonalInformation() {
        checkoutStepOne.EnterData(faker.name().firstName(), faker.name().lastName(), faker.address().zipCode());
    }

    @Then("User verifies the accuracy of entered information")
    public void userVerifiesTheAccuracyOfEnteredInformation() {
        double total = 0;
        double subTotal = checkoutStepTwo.getTxtSubTotal();

        List<SelectedItem> list = checkoutStepTwo.verifyPriceOfFirstItem();

        for(int i = 0; i < list.size(); i++){
            softAssertions.assertThat(list.get(i).getProductName()).isEqualTo(items.get(i).getProductName());
            softAssertions.assertThat(list.get(i).getDescription()).isEqualTo(items.get(i).getDescription());
            softAssertions.assertThat(list.get(i).getPrice()).isEqualTo(items.get(i).getPrice());
            total = total + items.get(i).getPrice();
        }

        softAssertions.assertThat(subTotal).isEqualTo(total);
    }

    @And("User confirms the purchase and receives a successful transaction message")
    public void userConfirmsThePurchaseAndReceivesASuccessfulTransactionMessage() {
        softAssertions.assertThat(checkoutComplete.getTxtCongratulate()).isEqualTo("Thank you for your order!");
    }
}

