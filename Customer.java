package test;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    private int customerID;
    private String name;
    private String email;
    private String password;
    private String phone;
    private ArrayList<Order> orderHistoryList;
    
    // HashMap to store customer data (username-password pairs)
    private static HashMap<String, Customer> customers = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
        this.orderHistoryList = new ArrayList<>();
    }
    
    public Customer(int customerID, String name, String email, String password, String phone) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.orderHistoryList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void viewOrderHistory() {
        if (orderHistoryList.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orderHistoryList) {
                System.out.println(order); // This will use the overridden toString() method in Order class
            }
        }
    }
    

    // Method for the login/register flow
    public static Customer loginRegisterFlow() {
        while (true) {
            System.out.println("=========================================");
            System.out.println("|      WELCOME TO THE SHIRT STORE!      |");
            System.out.println("=========================================");
            System.out.println("     We're thrilled to have you here!    ");
            System.out.println("=========================================");
            System.out.println("|                                       |");
            System.out.println("|  Please select one of the following   |");
            System.out.println("|              options:                 |");
            System.out.println("|                                       |");
            System.out.println("=========================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit program");
            System.out.println("=========================================");
            System.out.print("Please enter your choice (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> {
                    Customer customer = login();
                    if (customer != null) {
                        return customer; // Successful login, return the customer object
                    }
                }
                case 2 -> register();
                case 3 -> {
                    System.out.println("Alright then. Goodbye!");
                    System.exit(0);
                }
            }
        }
    }

    // Register a new user (creates a new Customer)
    private static void register() {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if (customers.containsKey(username)) {
            System.out.println("This username is already in use. Please try again!");
            return;
        }

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        // Store the new customer
        Customer newCustomer = new Customer(username,password);
        customers.put(username, newCustomer);

        System.out.println("Registration successful! You can now login.");
    }

    // Login an existing user (retrieves a Customer)
    private static Customer login() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Validate the username and password
        if (customers.containsKey(username) && customers.get(username).getPassword().equals(password)) {
            System.out.println("Login successful! Welcome " + username + "!");
            return customers.get(username); // Return the logged-in customer
        } else {
            System.out.println("Invalid username or password. Please try again!");
            return null; // Login failed
        }
    }


    public void placeOrder(Order order) {
        this.orderHistoryList.add(order);
    }

    public void updateInfo(String newName, String newEmail, String newPhone) {
        this.name = newName;
        this.email = newEmail;
        this.phone = newPhone;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone + ", orderHistoryList=" + orderHistoryList + '}';
    }
    
}