package test;
public class Shirts {
    private int shirtID;
    private String shirtBrand;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;

    // Constructor
    public Shirts(int shirtID, String shirtBrand, String name, String description, double price, int stockQuantity) {
        this.shirtID = shirtID;
        this.shirtBrand = shirtBrand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters
    public int getShirtID() {
        return shirtID;
    }

    public String getShirtBrand() {
        return shirtBrand;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public void decreaseStock(int quantity) {
        stockQuantity -= quantity;
    }
    
    public boolean reduceStock(int quantity) {
        if (quantity <= stockQuantity) {
            stockQuantity -= quantity; // Reduce stock
            return true;
        } else {
            return false; // Not enough stock
        }
    }
    
    
    @Override
    public String toString() {
        return "Shirt [ID=" + shirtID + ", Brand=" + shirtBrand + ", Name=" + name + 
               ", Description=" + description + ", Price=" + price + ", Stock=" + stockQuantity + "]";
    }
}
