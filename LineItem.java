package com.aurionpro.model;

public class LineItem {
	private Food food;
	private int quantity;

	public LineItem(Food food, int quantity) {
		this.food = food;
		this.quantity = quantity;
	}

	public Food getFood() {
		return food;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getItemTotal() {
		double priceAfterDiscount = food.getPrice() * (1 - food.getDiscount() / 100);
		return priceAfterDiscount * quantity;
	}

	@Override
	public String toString() {
		return food.getName() + " x" + quantity + " = ₹" + getItemTotal();
	}
}
