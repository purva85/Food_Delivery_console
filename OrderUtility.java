package com.aurionpro.model;

import java.util.List;
import java.util.Scanner;

public class OrderUtility {

	public static Order createOrder(Customer customer, List<DeliveryBoy> deliveryBoys, Scanner sc) throws DeliveryBoyNotFoundException {
	    if (customer.getCart().isEmpty()) {
	        throw new IllegalStateException("Cart is empty. Cannot place an order.");
	    }

	    DeliveryBoy assignedBoy = null;
	    boolean isAssigned = false;
	    String timeSlot = "";

	    while (!isAssigned) {
	        // 1. Prompt for time slot
	        System.out.println("Select Delivery Time Slot:");
	        System.out.println("1. 7am - 12am");
	        System.out.println("2. 12am - 6pm");
	        System.out.println("3. 7pm - 11pm");
	        System.out.print("Enter your choice: ");
	        int slotChoice = sc.nextInt();
	        sc.nextLine();

	        timeSlot = switch (slotChoice) {
	            case 1 -> "7am - 12am";
	            case 2 -> "12am - 6pm";
	            case 3 -> "7pm - 11pm";
	            default -> "7am - 12am";
	        };

	        try {
	            assignedBoy = DeliveryBoyUtility.assignDeliveryBoy(deliveryBoys, timeSlot);
	            isAssigned = true;
	        } catch (DeliveryBoyNotFoundException e) {
	            System.out.println("Oops! No delivery boy available for the selected time slot.");
	            System.out.println("Choose an option:");
	            System.out.println("1. Try same time slot for next day");
	            System.out.println("2. Choose a different time slot");
	            System.out.print("Enter your choice: ");
	            int retryChoice = sc.nextInt();
	            sc.nextLine();

	            if (retryChoice == 1) {
	                System.out.println("Your order will be scheduled for the same time slot tomorrow.");
	                isAssigned = true;
	                // Leave assignedBoy as null or handle as needed in Order class
	            }
	        }
	    }

	    // 2. Prompt for payment method
	    System.out.println("Select Payment Method:");
	    System.out.println("1. Cash on Delivery");
	    System.out.println("2. UPI");
	    System.out.println("3. Card");
	    System.out.print("Enter your choice: ");
	    int paymentChoice = sc.nextInt();
	    sc.nextLine();

	    String paymentMethod = switch (paymentChoice) {
	        case 1 -> "Cash on Delivery";
	        case 2 -> "UPI";
	        case 3 -> "Card";
	        default -> "Cash on Delivery";
	    };

	    // 3. Create and return order
	    Order order = new Order(customer, customer.getCart(), assignedBoy, timeSlot, paymentMethod);
	    if (assignedBoy != null)
	        System.out.println("Delivery boy assigned: " + assignedBoy.getName() + " (" + timeSlot + ")");
	    else
	        System.out.println("Delivery will be scheduled for tomorrow.");

	    return order;
	}


    public static void addToCart(List<LineItem> cart, List<Food> foodList, Scanner sc) {
        System.out.print("Enter Food ID to add to cart: ");
        int id = sc.nextInt();
        Food selectedFood = null;
        for (Food food : foodList) {
            if (food.getId() == id) {
                selectedFood = food;
                break;
            }
        }

        if (selectedFood == null) {
            System.out.println("Food item not found!");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        if (quantity > selectedFood.getQuantity()) {
            System.out.println("Not enough quantity in stock.");
            return;
        }

        // Check if item already exists in cart
        for (LineItem item : cart) {
            if (item.getFood().getId() == selectedFood.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Updated quantity in cart.");
                return;
            }
        }

        // Add new item to cart
        LineItem newItem = new LineItem(selectedFood, quantity);
        cart.add(newItem);
        System.out.println("Item added to cart.");
    }

    public static void removeFromCart(List<LineItem> cart, Scanner sc) {
        System.out.print("Enter Food ID to remove from cart: ");
        int id = sc.nextInt();
        boolean removed = cart.removeIf(item -> item.getFood().getId() == id);
        if (removed) {
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    public static void viewCart(List<LineItem> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\n--- Cart Contents ---");
        double total = 0;
        for (LineItem item : cart) {
            double price = item.getFood().getPrice();
            double discount = item.getFood().getDiscount();
            int qty = item.getQuantity();

            double discountedPrice = price - (price * discount / 100);
            double itemTotal = discountedPrice * qty;
            total += itemTotal;

            System.out.printf("ID: %d | Name: %s | Qty: %d | Price(after %.1f%% off): %.2f | Total: %.2f%n",
                    item.getFood().getId(), item.getFood().getName(), qty, discount, discountedPrice, itemTotal);
        }
        System.out.println("Total Cart Amount: ₹" + total);
    }

    public static void printOrderSummary(Order order) {
        System.out.println("\nFinal Order Summary:");
        System.out.println(order);
    }
}
