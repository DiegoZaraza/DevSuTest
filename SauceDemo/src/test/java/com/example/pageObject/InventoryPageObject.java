package com.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryPageObject extends BasePage{
    @FindBy(id = "inventory_container")
    private WebElement inventoryContainer;
    @FindBy(className = "inventory_list")
    private static WebElement inventoryList;

    @FindBy(className = "shopping_cart_link")
    private static WebElement iconShoppingCart;

    public InventoryPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public static List<SelectedItem> addItemsWithSamePriceToCart() {
        List<WebElement> inventoryItems = getInventoryItems();
        List<SelectedItem> selectedItems = new ArrayList<>();
        Map<Double, List<WebElement>> priceToItems = new HashMap<>();
        String productName = null;
        String description = null;
        double price = 0;

        for (WebElement inventoryItem : inventoryItems) {
            price = Double.parseDouble(getPriceElement(inventoryItem).getText().replace("$", ""));
            List<WebElement> itemsWithSamePrice = priceToItems.getOrDefault(price, new ArrayList<>());
            itemsWithSamePrice.add(inventoryItem);
            priceToItems.put(price, itemsWithSamePrice);
        }

        for (Map.Entry<Double, List<WebElement>> entry : priceToItems.entrySet()) {

            if (entry.getValue().size() == 2) {
                for (WebElement inventoryItem : entry.getValue()) {
                    productName = inventoryItem.findElement(By.className("inventory_item_name")).getText();
                    description = inventoryItem.findElement(By.className("inventory_item_desc")).getText();
                    price = Double.parseDouble(inventoryItem.findElement(By.className("inventory_item_price")).getText().replace("$", ""));
                    SelectedItem selectedItem = new SelectedItem(productName, description, price);
                    selectedItems.add(selectedItem);
                    clickAddToCartButton(inventoryItem);
                }
            }
        }
        clickIconShoppingCart();
        return selectedItems;
    }

    private static List<WebElement> getInventoryItems() {
        return inventoryList.findElements(By.className("inventory_item"));
    }

    private static WebElement getPriceElement(WebElement inventoryItem) {
        return inventoryItem.findElement(By.className("inventory_item_price"));
    }

    private static void clickAddToCartButton(WebElement inventoryItem) {
        inventoryItem.findElement(By.tagName("button")).click();
    }


    public void addItems() {
        List<WebElement> inventoryItems = inventoryContainer.findElements(By.className("inventory_item"));

        Map<Double, List<WebElement>> priceToItems = new HashMap<>();

        for (WebElement inventoryItem : inventoryItems) {
            double price = Double.parseDouble(inventoryItem.findElement(By.className("inventory_item_price")).getText().replace("$", ""));

            List<WebElement> itemsWithSamePrice = priceToItems.getOrDefault(price, new ArrayList<>());
            itemsWithSamePrice.add(inventoryItem);
            priceToItems.put(price, itemsWithSamePrice);
        }

        for (Map.Entry<Double, List<WebElement>> entry : priceToItems.entrySet()) {
            List<WebElement> itemsWithSamePrice = entry.getValue();

            if (itemsWithSamePrice.size() == 2) {
                for (WebElement inventoryItem : itemsWithSamePrice) {
                    WebElement addToCartButton = inventoryItem.findElement(By.tagName("button"));
                    addToCartButton.click();
                }
            }
        }
        clickIconShoppingCart();
    }

    private static void clickIconShoppingCart(){
        click(iconShoppingCart, "");
    }


}


