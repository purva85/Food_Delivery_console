package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private int id;
    private String name;
    private List<LineItem> cart;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.cart = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public List<LineItem> getCart() { return cart; }

    public void addToCart(LineItem item) {
        cart.add(item);
    }

    public void removeFromCart(int foodId) {
        cart.removeIf(item -> item.getFood().getId() == foodId);
    }

    public void clearCart() {
        cart.clear();
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name;
    }
}


