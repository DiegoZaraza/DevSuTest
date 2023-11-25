package com.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutComplete extends BasePage{
    @FindBy(className = "complete-header")
    WebElement txtCongratulate;
    public CheckoutComplete(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getTxtCongratulate(){
        return getText(txtCongratulate, "");
    }
}
