package test;

import java.util.Scanner;

public class Admin {
    private static final String adminUsername = "admin"; // Hardcoded username
    private static final String adminPassword = "password"; // Hardcoded password

     private String username;

    public Admin(String username) {
        this.username = username;
    }
     
     // Static method to login as admin
    public static Admin login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();

        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        // Check if the username and password match the hardcoded admin credentials
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            System.out.println("Admin login successful!");
            return new Admin(username); // Return new Admin object upon successful login
        } else {
            System.out.println("Invalid admin username or password.");
            return null; // Return null if login fails
        }
    }

    // Method for adding a single shirt
public void addShirt(ShirtStore store, Scanner scanner) {
    int shirtID = 0;
    double price = 0.0;
    int stock = 0;
    
    // Validate Shirt ID input (int)
    while (true) {
        System.out.print("Enter Shirt ID: ");
        if (scanner.hasNextInt()) {
            shirtID = scanner.nextInt();
            if (shirtID > 0) break; // Shirt ID should be positive
            else System.out.println("Shirt ID should be a positive number. Try again.");
        } else {
            System.out.println("Invalid input! Please enter a valid integer for Shirt ID.");
            scanner.next(); // Clear invalid input
        }
    }
    scanner.nextLine(); // consume newline
    
    // Validate Shirt Brand input (non-empty string)
    String brand = "";
    while (true) {
        System.out.print("Enter Shirt Brand: ");
        brand = scanner.nextLine();
        if (!brand.trim().isEmpty()) break; // Non-empty input
        else System.out.println("Brand name cannot be empty. Please enter a valid brand.");
    }
    
    // Validate Shirt Name input (non-empty string)
    String name = "";
    while (true) {
        System.out.print("Enter Shirt Name: ");
        name = scanner.nextLine();
        if (!name.trim().isEmpty()) break; // Non-empty input
        else System.out.println("Shirt name cannot be empty. Please enter a valid name.");
    }
    
    // Validate Shirt Description input (non-empty string)
    String description = "";
    while (true) {
        System.out.print("Enter Shirt Description: ");
        description = scanner.nextLine();
        if (!description.trim().isEmpty()) break; // Non-empty input
        else System.out.println("Description cannot be empty. Please enter a valid description.");
    }

    // Validate Shirt Price input (double)
    while (true) {
        System.out.print("Enter Shirt Price: ");
        if (scanner.hasNextDouble()) {
            price = scanner.nextDouble();
            if (price > 0) break; // Price should be positive
            else System.out.println("Price should be a positive number. Try again.");
        } else {
            System.out.println("Invalid input! Please enter a valid numeric value for the price.");
            scanner.next(); // Clear invalid input
        }
    }

    // Validate Shirt Stock input (int)
    while (true) {
        System.out.print("Enter Shirt Stock Quantity: ");
        if (scanner.hasNextInt()) {
            stock = scanner.nextInt();
            if (stock >= 0) break; // Stock can be zero or positive
            else System.out.println("Stock quantity cannot be negative. Try again.");
        } else {
            System.out.println("Invalid input! Please enter a valid stock quantity.");
            scanner.next(); // Clear invalid input
        }
    }

    // Create and add the new shirt to the store
    Shirts newShirt = new Shirts(shirtID, brand, name, description, price, stock);
    store.addShirt(newShirt);
    System.out.println("Shirt added successfully!");
}



    // Method for removing a shirt
    public void removeShirt(ShirtStore store, Scanner scanner) {
        System.out.print("Enter Shirt ID to remove: ");
        int id = scanner.nextInt();
        store.removeShirtById(id); // Call remove method
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Admin{");
        sb.append("username=").append(username);
        sb.append('}');
        return sb.toString();
    }
    
}
