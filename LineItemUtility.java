package com.aurionpro.model;

public class LineItemUtility {

    //  Create a LineItem (food + quantity)
    public static LineItem createLineItem(Food food, int quantity) {
        return new LineItem(food, quantity);
    }

    //  Update LineItem quantity (if needed)
    public static void updateQuantity(LineItem item, int newQuantity) {
        item.setQuantity(newQuantity);
    }

    //  Calculate total price for a line item
    public static double calculateItemTotal(LineItem item) {
        return item.getItemTotal();
    }
}
