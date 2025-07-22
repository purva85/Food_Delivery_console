
package com.aurionpro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryBoyUtility {

    // ✅ Initialize default delivery boys
    public static List<DeliveryBoy> initializeDefaultDeliveryBoys() {
        List<DeliveryBoy> boys = new ArrayList<>();
        boys.add(new DeliveryBoy(1, "Ravi", true, "7am - 12am"));
        boys.add(new DeliveryBoy(2, "Amit", true, "12am - 6pm"));
        boys.add(new DeliveryBoy(3, "Sunil", true, "7pm - 11pm"));
        return boys;
    }

    // ✅ View all delivery boys
    public static void displayAllDeliveryBoys(List<DeliveryBoy> deliveryBoys) {
        System.out.println("\n--- All Delivery Boys ---");
        for (DeliveryBoy boy : deliveryBoys) {
            System.out.println(boy);
        }
    }

    //  Add new delivery boy with time slot selection
    public static void addDeliveryBoy(List<DeliveryBoy> deliveryBoys, Scanner sc) {
        System.out.print("Enter Delivery Boy ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        
        // Check for duplicate ID
        for (DeliveryBoy boy : deliveryBoys) {
            if (boy.getId() == id) {
                System.out.println("Delivery Boy ID already exists. Please use a unique ID.");
                return;
            }
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.println("Select Time Slot:");
        System.out.println("1. 7am - 12am");
        System.out.println("2. 12am - 6pm");
        System.out.println("3. 7pm - 11pm");
        System.out.print("Enter your choice: ");
        int slotChoice = sc.nextInt();
        sc.nextLine(); // consume newline

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

        deliveryBoys.add(new DeliveryBoy(id, name, true, timeSlot));
        System.out.println(" Delivery boy added.");
    }

    //  Toggle availability status
    public static void toggleAvailability(DeliveryBoy boy) {
        boy.setAvailable(!boy.isAvailable());
    }

    //  Remove delivery 
    public static boolean removeDeliveryBoyById(List<DeliveryBoy> deliveryBoys, int id) {
        return deliveryBoys.removeIf(boy -> boy.getId() == id);
    }

    //  Find delivery
    public static DeliveryBoy getById(List<DeliveryBoy> deliveryBoys, int id) {
        for (DeliveryBoy boy : deliveryBoys) {
            if (boy.getId() == id) {
                return boy;
            }
        }
        return null;
    }

    //  Assign delivery boy based 
    public static DeliveryBoy assignDeliveryBoy(List<DeliveryBoy> boys, String timeSlot) throws DeliveryBoyNotFoundException {
        for (DeliveryBoy boy : boys) {
            if (boy.getTimeSlot().equalsIgnoreCase(timeSlot) && boy.isAvailable()) {
                boy.setAvailable(false);
                return boy;
            }
        }
        throw new DeliveryBoyNotFoundException("No delivery boy available for " + timeSlot);
    }
    //update delivery boy
    public static void updateDeliveryBoy(List<DeliveryBoy> deliveryBoys, Scanner sc) {
        System.out.print("Enter Delivery Boy ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        DeliveryBoy boy = getById(deliveryBoys, id);
        if (boy == null) {
            System.out.println("Delivery boy not found.");
            return;
        }
        System.out.print("Enter new name (leave blank to keep current): ");
        String name = sc.nextLine();
        if (!name.isBlank()) boy.setName(name);

        System.out.println("Select new Time Slot (or 0 to keep current):");
        System.out.println("1. 7am - 12am");
        System.out.println("2. 12am - 6pm");
        System.out.println("3. 7pm - 11pm");
        System.out.print("Enter your choice: ");
        int slotChoice = sc.nextInt();
        sc.nextLine();
        if (slotChoice >= 1 && slotChoice <= 3) {
            String timeSlot = switch (slotChoice) {
                case 1 -> "7am - 12am";
                case 2 -> "12am - 6pm";
                case 3 -> "7pm - 11pm";
                default -> boy.getTimeSlot();
            };
            boy.setTimeSlot(timeSlot);
        }
        System.out.println("Delivery boy updated.");
    }
    
    //remove
    public static void removeDeliveryBoy(List<DeliveryBoy> deliveryBoys, Scanner sc) {
        System.out.print("Enter Delivery Boy ID to remove: ");
        int id = sc.nextInt();
        boolean removed = removeDeliveryBoyById(deliveryBoys, id);
        if (removed) {
            System.out.println("Delivery boy removed.");
        } else {
            System.out.println("Delivery boy not found.");
        }
    }
}
