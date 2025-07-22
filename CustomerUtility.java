package com.aurionpro.model;

import java.util.List;

public class CustomerUtility {

    //  Add item
    public static void addToCart(Customer customer, Food food, int quantity) {
        List<LineItem> cart = customer.getCart();

        // Check if item already in cart
        for (LineItem item : cart) {
            if (item.getFood().getId() == food.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Updated quantity in cart.");
                return;
            }
        }

        // Else add new line item
        cart.add(new LineItem(food, quantity));
        System.out.println("Item added to cart.");
    }

    //  Remove item 
    public static void removeFromCart(Customer customer, int foodId) {
        List<LineItem> cart = customer.getCart();
        boolean removed = cart.removeIf(item -> item.getFood().getId() == foodId);

        if (removed) {
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    // ✅ View cart items
    public static void viewCart(Customer customer) {
        List<LineItem> cart = customer.getCart();

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n--- Your Cart ---");
        double total = 0;
        for (LineItem item : cart) {
            System.out.println(item);
            total += item.getItemTotal();
        }
        System.out.println("Total: ₹" + total);
    }

    //  Clear cart
    public static void clearCart(Customer customer) {
        customer.getCart().clear();
        System.out.println("Cart cleared.");
    }

    //  Calculate total cart value
    public static double calculateCartTotal(Customer customer) {
        return customer.getCart().stream()
                .mapToDouble(LineItem::getItemTotal)
                .sum();
    }
}

