package com.aurionpro.test;

import java.util.List;
import java.util.Scanner;

import com.aurionpro.model.DeliveryBoy;
import com.aurionpro.model.DeliveryBoyNotFoundException;
import com.aurionpro.model.DeliveryBoyUtility;
import com.aurionpro.model.Food;
import com.aurionpro.model.ProductStorage;
import com.aurionpro.model.FoodUtility;
import com.aurionpro.model.OrderUtility;
import com.aurionpro.model.ProductNotFoundException;
import com.aurionpro.model.Customer;
import com.aurionpro.model.InputHelper;
import com.aurionpro.model.Order;
import com.aurionpro.model.Admin;


public class FoodTest {
	public static void main(String[] args) throws ProductNotFoundException {
        Scanner sc = new Scanner(System.in);

        // Load product list
        List<Food> foodList = ProductStorage.loadProducts();

        // Create delivery boys
        List<DeliveryBoy> deliveryBoys = DeliveryBoyUtility.initializeDefaultDeliveryBoys();

        // Create admin (for this version, admin has no login)
        Admin admin = new Admin("admin", foodList, deliveryBoys);

        // Create a customer (for simplicity, one session at a time)
//        System.out.print("Enter your name: ");
//        String customerName = sc.nextLine();
        //Customer customer = new Customer(1, customerName);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n========== FOOD DELIVERY SYSTEM ==========");
            System.out.println("1. Admin Panel");
            System.out.println("2. Customer Panel");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = InputHelper.readInt(sc);

            switch (choice) {
                case 1 -> handleAdminPanel(admin, sc);
                case 2 -> {
                	sc.nextLine();
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    Customer customer = new Customer(1, name);
                    handleCustomerPanel(customer, admin, sc);
                }
                case 0 -> {
                    ProductStorage.saveProducts(foodList); // Save product updates
                    System.out.println("Exiting... Thank you!");
                    exit = true;
                }
                default -> System.out.println(" Invalid choice. Try again.");
            }
        }

        sc.close();
    }

    private static void handleAdminPanel(Admin admin, Scanner sc) throws ProductNotFoundException {
        boolean back = false;
        while (!back) {
            System.out.println("\n---- ADMIN PANEL ----");
            System.out.println("1. View All Products");
            System.out.println("2. Add Product");
            System.out.println("3. Remove Product");
            System.out.println("4. Update Product");
            System.out.println("5. View Delivery Boys");
            System.out.println("6. Add Delivery Boy");
            System.out.println("7. Remove Delivery Boy");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = InputHelper.readInt(sc);
            switch (choice) {
                case 1 -> FoodUtility.displayAllFoods(admin.getProductList());
                case 2 -> FoodUtility.addFood(admin.getProductList(), sc);
                case 3 -> FoodUtility.removeFood(admin.getProductList(), sc);
                case 4 -> FoodUtility.updateFood(admin.getProductList(), sc);
                case 5 -> DeliveryBoyUtility.displayAllDeliveryBoys(admin.getDeliveryBoys());
                case 6 -> DeliveryBoyUtility.addDeliveryBoy(admin.getDeliveryBoys(), sc);
                case 7 -> DeliveryBoyUtility.removeDeliveryBoy(admin.getDeliveryBoys(),sc);
                case 0 -> back = true;
                default -> System.out.println(" Invalid choice.");
            }
        }
    }

    private static void handleCustomerPanel(Customer customer, Admin admin, Scanner sc) throws ProductNotFoundException {
        boolean back = false;
        while (!back) {
            System.out.println("\n---- CUSTOMER PANEL ----");
            System.out.println("1. View All Food Items");
            System.out.println("2. Search Food by Name");
            System.out.println("3. Add to Cart");
            System.out.println("4. Remove from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Place Order");
           
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = InputHelper.readInt(sc);
            switch (choice) {
                case 1 -> FoodUtility.displayAllFoods(admin.getProductList());
                case 2 -> FoodUtility.searchFoodByName(admin.getProductList(), InputHelper.readString(sc, "Enter name to search: "));
                case 3 -> OrderUtility.addToCart(customer.getCart(), admin.getProductList(), sc);
                case 4 -> OrderUtility.removeFromCart(customer.getCart(), sc);
                case 5 -> OrderUtility.viewCart(customer.getCart());
                case 6 -> {
                    try {
                        Order order = OrderUtility.createOrder(customer, admin.getDeliveryBoys(), sc);
                        OrderUtility.printOrderSummary(order);
                        customer.getCart().clear(); // Empty cart
                    } catch (DeliveryBoyNotFoundException | IllegalStateException e) {
                        System.out.println("Oops " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Oops Error placing order: " + e.getMessage());
                    }
                }
                
                case 0 -> back = true;
                default -> System.out.println(" Invalid choice.");
            }
        }
    }
}
    
    



