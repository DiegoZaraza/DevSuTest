package com.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOne extends BasePage {
    @FindBy(id = "first-name")
    WebElement txtFirstName;

    @FindBy(id ="last-name")
    WebElement txtLastName;

    @FindBy(id = "postal-code")
    WebElement txtPostalCode;

    @FindBy(id = "continue")
    WebElement btnContinue;

    public CheckoutStepOne(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void setTxtFirstName(String firstName){
        sendKeys(txtFirstName, firstName, "");
    }

    private void setTxtLastName(String lastName){
        sendKeys(txtLastName, lastName, "");
    }

    private void setTxtPostalCode(String postalCode){
        sendKeys(txtPostalCode, postalCode, "");
    }

    private void clickBtnContinue(){
        click(btnContinue, "");
    }

    public void EnterData(String firstName, String lastName, String postalCode){
        setTxtFirstName(firstName);
        setTxtLastName(lastName);
        setTxtPostalCode(postalCode);
        clickBtnContinue();
    }

}
