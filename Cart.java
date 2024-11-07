package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Cart {
    private int cartID;
    private ArrayList<CartItem> cartItems; // Holds CartItem objects
    private static Scanner scanner = new Scanner(System.in);

    public Cart(int cartID) {
        this.cartID = cartID;
        this.cartItems = new ArrayList<>();
    }

    // Method to add a shirt with a specified quantity to the cart
    public void addItem(Shirts shirt, int quantity) {
        CartItem cartItem = new CartItem(shirt, quantity);
        cartItems.add(cartItem);
    }

    public void removeItem(Shirts shirt) {
        cartItems.removeIf(item -> item.getShirt().equals(shirt));
    }

    // Method to view all items in the cart, including their quantity
    public void viewCart() {
        System.out.println("Items in Cart:");
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        for (CartItem item : cartItems) {
            System.out.println(item.getShirt().getName() + " - $" + item.getShirt().getPrice() + " x " + item.getQuantity());
        }
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    // Calculate total amount based on price and quantity
    public double getTotalAmount() {
        double totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.getShirt().getPrice() * item.getQuantity(); // Price * Quantity
        }
        return totalAmount;
    }

public void removeItemFromCart() {
    viewCart(); // Display the current items in the cart

    if (cartItems.isEmpty()) {
        System.out.println("Your cart is empty. No items to remove.");
        return;
    }

    System.out.println("Enter the ID of the shirt you want to remove:");
    int shirtID = scanner.nextInt();
    scanner.nextLine(); // consume newline

    CartItem itemToRemove = null;
    for (CartItem item : cartItems) {
        if (item.getShirt().getShirtID() == shirtID) { // Compare by shirt ID
            itemToRemove = item;
            break;
        }
    }

    if (itemToRemove != null) {
        cartItems.remove(itemToRemove);
        System.out.println("Item has been removed from your cart.");
    } else {
        System.out.println("Shirt not found in the cart.");
    }
}

    public void proceedToCheckout(Customer customer) {
        System.out.println("Proceed to checkout? (yes/no)");
        String checkoutResponse = scanner.next();
        if (checkoutResponse.equalsIgnoreCase("yes")) {
            double totalAmount = getTotalAmount(); // Calculate total amount based on quantity

            // Create a new order with a unique ID
            Order order = new Order(new Date(), totalAmount);
            customer.placeOrder(order);

            // Payment processing
            Payment payment = handlePayment(totalAmount);

            if (payment == null) {
                System.out.println("Payment failed or canceled.");
                return;
            }

            // Apply discount (optional)
            applyDiscountIfAvailable(payment);

            // Final step: Complete order
            System.out.println("Order placed successfully! Total amount: " + payment.getAmount());
        } else {
            System.out.println("Order canceled.");
        }
    }

    private Payment handlePayment(double totalAmount) {
        System.out.println("Enter payment method (credit/debit/tng):");
        String paymentMethod = scanner.next();
        Payment payment = null;
        boolean validPayment = false;

        switch (paymentMethod.toLowerCase()) {
            case "credit":
                while (!validPayment) {
                    System.out.println("Please enter your Credit Card Number (7 digits):");
                    String cardNumber = scanner.next();
                    if (cardNumber.length() == 7 && cardNumber.matches("\\d+")) {
                        payment = new CreditPayment("PAY" + System.currentTimeMillis(), totalAmount, cardNumber);
                        payment.processPayment();
                        validPayment = true;
                    } else {
                        System.out.println("Invalid Credit Card Number. It should be 7 digits.");
                    }
                }
                break;
            case "debit":
                while (!validPayment) {
                    System.out.println("Please enter your Debit Card Number (6 digits):");
                    String debitCardNumber = scanner.next();
                    if (debitCardNumber.length() == 6 && debitCardNumber.matches("\\d+")) {
                        payment = new DebitPayment("PAY" + System.currentTimeMillis(), totalAmount, debitCardNumber);
                        payment.processPayment();
                        validPayment = true;
                    } else {
                        System.out.println("Invalid Debit Card Number. It should be 6 digits.");
                    }
                }
                break;
            case "tng":
                while (!validPayment) {
                    System.out.println("Please enter your TnG Pin Number:");
                    String tngPin = scanner.next();
                    payment = new TnGPayment(tngPin, "PAY" + System.currentTimeMillis(), totalAmount, "TnG");
                    payment.processPayment();
                    validPayment = true;
                }
                break;
            default:
                System.out.println("Invalid payment method.");
                return null;
        }
        return payment;
    }

    private void applyDiscountIfAvailable(Payment payment) {
        System.out.println("Do you have a discount code? (yes/no)");
        String discountResponse = scanner.next();
        if (discountResponse.equalsIgnoreCase("yes")) {
            System.out.println("Please enter your discount code:");
            String userCode = scanner.next();
            Discount discount = new Discount(10.0, "SAVE10"); // Example code and amount
            discount.applyDiscount(payment, userCode);
        }
    }

    // Shopping process now handles adding shirts with quantity
    public void shoppingProcess(ShirtStore shirtStore) {
        boolean keepShopping = true;
        while (keepShopping) {
            System.out.println("Enter the shirt ID to add to the cart:");
            int shirtID = scanner.nextInt();
            System.out.println("Enter quantity:");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Get shirt by ID
            Shirts selectedShirt = shirtStore.getShirtById(shirtID);
            if (selectedShirt != null) {
                if (selectedShirt.getStockQuantity() >= quantity) {
                    addItem(selectedShirt, quantity); // Add to cart
                    selectedShirt.reduceStock(quantity); // Reduce stock
                    System.out.println("Added " + quantity + " of " + selectedShirt.getName() + " to the cart.");
                    System.out.println("Remaining stock: " + selectedShirt.getStockQuantity());
                } else {
                    System.out.println("Sorry, only " + selectedShirt.getStockQuantity() + " available in stock.");
                }
            } else {
                System.out.println("Shirt ID not found.");
            }

            System.out.println("Do you want to continue shopping? (yes/no)");
            String continueShopping = scanner.next();
            keepShopping = continueShopping.equalsIgnoreCase("yes");
        }
    }

    public void manageCart(Customer customer) {
    Scanner scanner = new Scanner(System.in);
    boolean keepManaging = true;

    while (keepManaging) {
        System.out.println("Do you want to proceed to checkout or remove an item from the cart?");
        System.out.println("1. Proceed to Checkout");
        System.out.println("2. Remove Item from Cart");
        System.out.println("3. Cancel");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                proceedToCheckout(customer);
                keepManaging = false; // Exit the loop after proceeding to checkout
                break;
            case 2:
                removeItemFromCart(); // Calls the method to remove an item
                break;
            case 3:
                System.out.println("Cancelling...");
                keepManaging = false; // Exit the loop
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
        }
    }
}

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Cart.scanner = scanner;
    }
}
