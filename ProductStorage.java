package com.aurionpro.model;
import com.aurionpro.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductStorage {

    //private static final String FILE_PATH = "profood.txt";  // serialized file

    public static void saveProducts(List<Food> foodList) {
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\purvas.ambre\\Desktop\\profood.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(foodList);

            oos.close();
            fos.close();
            System.out.println("Products saved successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Food> loadProducts() {
        File file = new File("C:\\Users\\purvas.ambre\\Desktop\\profood.txt");
        if (!file.exists()) {
            System.out.println(" No saved product file found. Loading default products...");
            return getDefaultProducts();
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<Food> foodList = (List<Food>) ois.readObject();
            System.out.println("Products loaded successfully from file.");

            ois.close();
            fis.close();
            return foodList;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Error loading products: " + e.getMessage());
            return getDefaultProducts();
        }
    }

    public static List<Food> getDefaultProducts() {
        List<Food> defaultList = new ArrayList<>();
        defaultList.add(new Food(1, "Veg Pizza", 250.0, 10.0, 10));
        defaultList.add(new Food(2, "Burger", 120.0, 5.0, 15));
        defaultList.add(new Food(3, "French Fries", 80.0, 0.0, 20));
        return defaultList;
    }
}

