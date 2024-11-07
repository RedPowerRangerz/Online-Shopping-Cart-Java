package test;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShirtStore store = new ShirtStore(); // Shared store instance
        Scanner scanner = new Scanner(System.in);

        while (true) { // Main loop for login or register
            System.out.println("========================================");
            System.out.println("|          Welcome to Our Shop         |");
            System.out.println("========================================");
            System.out.println("|   Please select an option:            |");
            System.out.println("|   1. Customer                         |");
            System.out.println("|   2. Admin                            |");
            System.out.println("|   3. Exit                             |");
            System.out.println("========================================");
            System.out.print("Enter your choice (1-3): ");
            int menuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (menuChoice == 3) {
                System.out.println("Exiting the system...");
                break; // Exit the entire program
            }

            Customer customer = null;
            Admin admin = null;

            if (menuChoice == 1) {
                customer = Customer.loginRegisterFlow(); // Customer login flow
            } else if (menuChoice == 2) {
                admin = Admin.login(); // Admin login flow
                if (admin == null) {
                    continue; // Invalid admin, return to login
                }
                adminMenu(admin, scanner, store); // Pass store instance to admin menu
                continue; // After admin exits, return to the main menu
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                continue; // Invalid input, restart the loop
            }

            // Main menu loop for customer
            while (true) {
                System.out.println("\n=====================================");
                System.out.println("|              Main Menu            |");
                System.out.println("=====================================");
                System.out.println("|  1. Shop                          |");
                System.out.println("|  2. View Order History            |");
                System.out.println("|  3. Logout                        |");
                System.out.println("=====================================");
                System.out.print("Enter your choice (1-3): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1: // Shopping process
                        Cart cart = new Cart(101);
                        displaySortingOptions(store, scanner); // Pass store instance

                        // Add shirts to the cart with quantity
                        cart.shoppingProcess(store);

                        // View cart
                        cart.viewCart();

                        // Manage cart (proceed to checkout or remove item)
                        cart.manageCart(customer);
                        break;

                    case 2: // View Order History
                        customer.viewOrderHistory();
                        break;

                    case 3: // Logout
                        System.out.println("Logging out...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
                if (choice == 3) {
                    break; // Exit customer menu and return to the main login menu
                }
            }
        }
        scanner.close();
    }

    // Method to display sorting options and sort shirts accordingly
    public static void displaySortingOptions(ShirtStore shirtStore, Scanner scanner) {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("\n==========================================");
            System.out.println("|       How would you like to sort?       |");
            System.out.println("==========================================");
            System.out.println("|  1. Sort by Default                     |");
            System.out.println("|  2. Sort by Brand                       |");
            System.out.println("|  3. Sort by Price                       |");
            System.out.println("==========================================");
            System.out.print("Enter your choice (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    shirtStore.displayShirts(); // Sort by default
                    validInput = true;
                    break;
                case 2:
                    shirtStore.sortbyShirtBrand(); // Sort by brand
                    validInput = true;
                    break;
                case 3:
                    shirtStore.sortbyPrice(); // Sort by price
                    validInput = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    // Method to handle admin functionality
    public static void adminMenu(Admin admin, Scanner scanner, ShirtStore store) {
        while (true) {
            System.out.println("\n==========================================");
            System.out.println("|               Admin Menu                |");
            System.out.println("==========================================");
            System.out.println("|  1. Add New Shirt                       |");
            System.out.println("|  2. Remove Shirt                        |");
            System.out.println("|  3. Logout                              |");
            System.out.println("==========================================");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    admin.addShirt(store, scanner); // Pass the store instance
                    break;

                case 2:
                    store.displayShirts(); // Display current shirts before removing
                    admin.removeShirt(store, scanner);
                    break;

                case 3:
                    System.out.println("Logging out from admin...");
                    return; // Exit admin menu and return to login

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }
}
