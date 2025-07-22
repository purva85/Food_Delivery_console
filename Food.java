package com.aurionpro.model;

import java.io.Serializable;

public class Food implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private double price;
	private double discount;
	private int quantity;
	public Food(int id, String name, double price, double discount, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
    public String toString() {
        return "Food ID: " + id + ", Name: " + name + ", Price: ₹" + price +
               ", Discount: " + discount + "%, Quantity: " + quantity;
    }
	
	

}
