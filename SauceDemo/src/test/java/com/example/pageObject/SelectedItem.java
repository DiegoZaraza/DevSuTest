package com.example.pageObject;

public class SelectedItem {

    private final String productName;
    private final String description;
    private final double price;

    public SelectedItem(String productName, String description, double price) {
        this.productName = productName;
        this.description = description;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "SelectedItem{" +
                "productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}