package test;

public class CartItem {
    private Shirts shirt;
    private int quantity;

    public CartItem(Shirts shirt, int quantity) {
        this.shirt = shirt;
        this.quantity = quantity;
    }

    public Shirts getShirt() {
        return shirt;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return shirt.getPrice() * quantity; // Calculate total price for this item
    }

    @Override
    public String toString() {
        return shirt.getName() + " (Quantity: " + quantity + ") - Total Price: $" + getTotalPrice();
    }
}
