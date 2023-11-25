package com.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutStepTwo extends BasePage{
    @FindBy(className = "cart_list")
    WebElement cartList;

    @FindBy(id = "finish")
    WebElement btnFinish;

    @FindBy(className = "summary_subtotal_label")
    WebElement txtSubTotal;

    public CheckoutStepTwo(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<SelectedItem> verifyPriceOfFirstItem() {
        List<SelectedItem> items = new ArrayList<>();

        List<WebElement> cartItems = cartList.findElements(By.className("cart_item"));
        for (WebElement cartItem : cartItems) {
            String productName = cartItem.findElement(By.className("inventory_item_name")).getText();
            String description = cartItem.findElement(By.className("inventory_item_desc")).getText();
            double price = Double.parseDouble(cartItem.findElement(By.className("inventory_item_price")).getText().replace("$", ""));

            SelectedItem selectedItem = new SelectedItem(productName, description, price);
            items.add(selectedItem);
        }
        clickBtnFinish();
        return items;
    }

    public double getTxtSubTotal() {
        return Double.parseDouble(txtSubTotal.getText().replace("Item total: $", ""));
    }

    private void clickBtnFinish(){
        click(btnFinish, "");
    }

}
