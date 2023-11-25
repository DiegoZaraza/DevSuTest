package com.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends BasePage {
    @FindBy(id = "user-name")
    WebElement txtUserName;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "login-button")
    WebElement btnLogin;

    public LoginPageObject(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private void setTxtUserName(String userName){
        sendKeys(txtUserName, userName, "");
    }

    private void setTxtPassword(String password){
        sendKeys(txtPassword, password, "");
    }

    private void clickBtnLogin(){
        click(btnLogin, "");
    }

    public void loginPage(String userName, String password) {
        setTxtUserName(userName);
        setTxtPassword(password);
        clickBtnLogin();
    }
}
