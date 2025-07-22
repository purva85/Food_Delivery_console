package com.aurionpro.model;

import com.aurionpro.model.FoodUtility;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminService {
	private List<Food> foodList;
	private List<DeliveryBoy> deliveryBoyList;
	private Scanner sc;

	public AdminService(List<Food> foodList, List<DeliveryBoy> deliveryBoyList, Scanner sc) {
		this.foodList = foodList;
		this.deliveryBoyList = deliveryBoyList;
		this.sc = sc;
	}

	public void startAdminMenu() throws ProductNotFoundException {
		int choice;
		do {
			System.out.println("\n--- Admin Menu ---");
			System.out.println("1. View All Products");
			System.out.println("2. Add Product");
			System.out.println("3. Remove Product");
			System.out.println("4. Update Product");
			System.out.println("5. Search Product by Name");
			System.out.println("6. View Delivery Boys");
			System.out.println("7. Add Delivery Boy");
			System.out.println("8. Remove Delivery Boy");
			System.out.println("9. Update Delivery Boy Availability");
			System.out.println("0. Exit Admin Menu");

			choice = safeIntInput("Enter your choice: ");

			switch (choice) {
			case 1 -> FoodUtility.displayAllFoods(foodList); 
			case 2 -> FoodUtility.addFood(foodList, sc); 
			case 3 -> FoodUtility.removeFood(foodList, sc); 
			case 4 -> FoodUtility.updateFood(foodList, sc); 
			case 5 -> { 
				System.out.print("Enter product name to search: ");
				String name = sc.next();
				FoodUtility.searchFoodByName(foodList, name);
			}
			case 6 -> DeliveryBoyUtility.displayAllDeliveryBoys(deliveryBoyList);
			case 7 -> addDeliveryBoy();
			case 8 -> removeDeliveryBoy();
			case 9 -> updateDeliveryBoyAvailability();
			case 0 -> System.out.println("Exiting Admin Menu.");
			default -> System.out.println("Invalid choice.");
			}

		} while (choice != 0);
	}

	private void addDeliveryBoy() {
		System.out.print("Enter Delivery Boy ID: ");
		int id = safeIntInput("");
		System.out.print("Enter Delivery Boy Name: ");
		String name = sc.next();
		
		System.out.println("Select Time Slot:");
	    System.out.println("1. 7am - 12am");
	    System.out.println("2. 12am - 6pm");
	    System.out.println("3. 7pm - 11pm");
	    System.out.print("Enter your choice: ");
	    int slotChoice = safeIntInput("");
	    String timeSlot;
	    switch (slotChoice) {
	        case 1 -> timeSlot = "7am - 12am";
	        case 2 -> timeSlot = "12am - 6pm";
	        case 3 -> timeSlot = "7pm - 11pm";
	        default -> {
	            System.out.println("Invalid choice. Defaulting to 7am - 12am.");
	            timeSlot = "7am - 12am";
	        }
	    }
		deliveryBoyList.add(new DeliveryBoy(id, name, true, timeSlot));
		System.out.println("Delivery Boy added.");
	}

	private void removeDeliveryBoy() {
		System.out.print("Enter ID of Delivery Boy to remove: ");
		int id = safeIntInput("");
		boolean removed = DeliveryBoyUtility.removeDeliveryBoyById(deliveryBoyList, id);
		if (removed)
			System.out.println("Delivery Boy removed.");
		else
			System.out.println("Delivery Boy not found.");
	}

	private void updateDeliveryBoyAvailability() {
		System.out.print("Enter Delivery Boy ID: ");
		int id = safeIntInput("");
		DeliveryBoy boy = DeliveryBoyUtility.getById(deliveryBoyList, id);
		if (boy != null) {
			DeliveryBoyUtility.toggleAvailability(boy);
			System.out.println("Availability updated to: " + boy.isAvailable());
		} else {
			System.out.println("Delivery Boy not found.");
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
