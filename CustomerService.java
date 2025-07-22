package com.aurionpro.model;

import com.aurionpro.model.FoodUtility;
import com.aurionpro.model.OrderUtility;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerService {
	private List<Food> foodList;
    private List<DeliveryBoy> deliveryBoyList;
    private Scanner sc;
    private List<LineItem> cart;

    public CustomerService(List<Food> foodList, List<DeliveryBoy> deliveryBoyList, Scanner sc) {
        this.foodList = foodList;
        this.deliveryBoyList = deliveryBoyList;
        this.sc = sc;
        this.cart = new ArrayList<>();
    }

    public void startCustomerMenu() {
        int choice;
        do {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View Products");
            System.out.println("2. Search Product by Name");
            System.out.println("3. Add to Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. View Delivery Boy Availability");
            System.out.println("0. Exit Customer Menu");

            choice = safeIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> FoodUtility.displayAllFoods(foodList);
                case 2 -> { 
                	System.out.print("Enter food name to search: ");
                String keyword = sc.next();
                FoodUtility.searchFoodByName(foodList, keyword);
                }
                case 3 -> OrderUtility.addToCart(cart, foodList, sc);
                case 4 -> OrderUtility.removeFromCart(cart, sc);
                case 5 -> OrderUtility.viewCart(cart);
                case 6 -> viewAvailableDeliveryBoys();
                case 0 -> System.out.println("Exiting Customer Menu.");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private void viewAvailableDeliveryBoys() {
        System.out.println("\nAvailable Delivery Boys:");
        for (DeliveryBoy boy : deliveryBoyList) {
            if (boy.isAvailable()) {
                System.out.println(boy);
            }
        }
    }

    private int safeIntInput(String prompt) {
        int num;
        while (true) {
            try {
                System.out.print(prompt);
                num = sc.nextInt();
                return num;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }
    }
}


