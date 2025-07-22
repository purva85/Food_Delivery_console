package com.aurionpro.model;

import java.util.List;

public class Admin {
	private String name;
    private List<Food> productList;
    private List<DeliveryBoy> deliveryBoys;

    public Admin(String name, List<Food> productList, List<DeliveryBoy> deliveryBoys) {
        this.name = name;
        this.productList = productList;
        this.deliveryBoys = deliveryBoys;
    }

    public String getName() {
        return name;
    }

    public List<Food> getProductList() {
        return productList;
    }

    public List<DeliveryBoy> getDeliveryBoys() {
        return deliveryBoys;
    }
}


