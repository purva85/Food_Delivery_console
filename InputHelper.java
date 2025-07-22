package com.aurionpro.model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {
	 //  Safe read for integer input with retry logic
    public static int readInt(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print(" Invalid input. Please enter a number: ");
                sc.nextLine(); // Clear invalid input
            }
        }
    }

    //  Read integer with a prompt message
    public static int readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        return readInt(sc);
    }

    //  Safe read for double input with retry logic
    public static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input. Please enter a valid number.");
                sc.nextLine(); // Clear input buffer
            }
        }
    }

    //  Safe read for String input
    public static String readString(Scanner sc, String prompt) {
        System.out.print(prompt);
        sc.nextLine(); // Clear buffer
        return sc.nextLine();
    }

    //  Safe read for yes/no confirmation
    public static boolean confirm(Scanner sc, String prompt) {
        System.out.print(prompt + " (y/n): ");
        String input = sc.next().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
}


