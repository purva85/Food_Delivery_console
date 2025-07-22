package com.aurionpro.model;

import java.util.List;
import java.util.Scanner;

public class FoodUtility {
//✅ Display all products
	public static void displayAllFoods(List<Food> foodList) {
		System.out.println("\n--- Available Food Items ---");
		for (Food food : foodList) {
			System.out.println(food);
		}
		System.out.println();
	}
	
	public static void addFood(List<Food> foodList, Scanner sc) {
        System.out.print("Enter food ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        
     // Check for duplicate ID
        for (Food food : foodList) {
            if (food.getId() == id) {
                System.out.println("Food ID already exists. Please use a unique ID.");
                return;
            }
        }
        System.out.print("Enter food name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter discount (in %): ");
        double discount = sc.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        Food food = new Food(id, name, price, discount, quantity);
        foodList.add(food);
        System.out.println("Product added successfully.");
    }

    public static void removeFood(List<Food> foodList, Scanner sc) throws ProductNotFoundException {
        System.out.print("Enter ID of product to remove: ");
        int id = sc.nextInt();
        boolean removed = foodList.removeIf(food -> food.getId() == id);
        if (removed) {
            System.out.println("Product removed successfully.");
        } else {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
    }

    public static void updateFood(List<Food> foodList, Scanner sc) throws ProductNotFoundException {
    	while (true) {
    		System.out.print("Enter ID of product to update: ");
    		int id = sc.nextInt();
    		Food found = null;

    		for (Food food : foodList) {
    			if (food.getId() == id) {
    				found = food;
    				break;
    			}
    		}

    		if (found == null) {
    			System.out.println("Product with ID " + id + " not found. Please try again.");
    			continue;  //  now valid inside while loop
    		}

    		// valid food found
    		sc.nextLine(); // consume newline
    		System.out.print("Enter new name (current: " + found.getName() + "): ");
    		String name = sc.nextLine();
    		System.out.print("Enter new price (current: " + found.getPrice() + "): ");
    		double price = sc.nextDouble();
    		System.out.print("Enter new discount (current: " + found.getDiscount() + "%): ");
    		double discount = sc.nextDouble();
    		System.out.print("Enter new quantity (current: " + found.getQuantity() + "): ");
    		int quantity = sc.nextInt();

    		found.setName(name);
    		found.setPrice(price);
    		found.setDiscount(discount);
    		found.setQuantity(quantity);

    		System.out.println("Product updated successfully.");
    		break; //  exit loop after successful update
    	}
    }

//  Search food by partial/full name (case-insensitive)
	public static void searchFoodByName(List<Food> foodList, String keyword) {
		boolean found = false;
		System.out.println("\n--- Search Results ---");
		for (Food food : foodList) {
			if (food.getName().toLowerCase().contains(keyword.toLowerCase())) {
				System.out.println(food);
				found = true;
			}
		}
		if (!found) {
			System.out.println("No food item found with keyword: " + keyword);
		}
	}

//  Get food by ID (throws exception if not found)
	public static Food getFoodById(List<Food> foodList, int id) throws ProductNotFoundException {
		return foodList.stream().filter(f -> f.getId() == id).findFirst()
				.orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found."));
	}

//  Validate available quantity before placing order
	public static boolean isQuantityAvailable(Food food, int requestedQty) {
		return food.getQuantity() >= requestedQty;
	}

//  Decrease quantity after successful purchase
	public static void reduceQuantity(Food food, int purchasedQty) {
		food.setQuantity(food.getQuantity() - purchasedQty);
	}
}
