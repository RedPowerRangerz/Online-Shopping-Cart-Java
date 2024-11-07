package test;



public class ShirtStore {
    private Shirts[] shirtsList;
    private int shirtCount; // To keep track of the number of shirts in the array

    public ShirtStore() {
        shirtsList = new Shirts[25]; // Set a fixed size for the array
        shirtCount = 0; // Initially no shirts are added
        addShirt(new Shirts(1,"Uniqlo", "Kenshi Yonezu UT", "Short Sleeve Graphic Tee", 59.90, 100));
        addShirt(new Shirts(2,"Uniqlo", "Mickey Stands UT", "Short Sleeve Graphic Tee", 59.90, 100));
        addShirt(new Shirts(3,"Uniqlo", "Peanuts UT", "Short Sleeve Graphic Tee", 59.90, 100));
        addShirt(new Shirts(4,"Uniqlo", "Pokemon UT", "Short Sleeve Graphic Tee", 59.90, 100));
        addShirt(new Shirts(5,"Uniqlo", "Oshi No Ko UT", "Short Sleeve Graphic Tee", 59.90, 100));
        addShirt(new Shirts(6,"HYPE", "HYPE Girl Brat", "Slim Fit Crop Top", 99.90, 100));
        addShirt(new Shirts(7,"HYPE", "DOD Distort", "Crop Top", 119.90, 100));
        addShirt(new Shirts(8,"HYPE", "Peace in Chaos", "Graphic Tee", 149.90, 100));
        addShirt(new Shirts(9,"HYPE", "HYPE Girl Julie", "Long Sleeve Shirt", 149.90, 100));
        addShirt(new Shirts(10,"PULL&BEAR", "Metallica T-Shirt", "Graphic Tee", 49.90, 100));
        addShirt(new Shirts(11,"PULL&BEAR", "Green Day T-Shirt", "Graphic Tee", 119.90, 100));
        addShirt(new Shirts(12,"PULL&BEAR", "Oversized T-Shirt", "Graphic Tee", 119.90, 100));
        addShirt(new Shirts(13,"Padini Authentics", "DC Knit Men Tee", "Short Sleeve Graphic Tee", 59.00, 100));
        addShirt(new Shirts(14,"Padini Authentics", "Essential Knit Men Tee", "Short Sleeve Graphic Tee", 39.00, 100));
        addShirt(new Shirts(15,"Padini Authentics", "Essential Sweater", "Long Sleeve Sweater", 69.00, 100));
        addShirt(new Shirts(16,"Rope", "Casual Short Sleeve Shirt", "Short Sleeve Shirt", 89.00, 100));
        addShirt(new Shirts(17,"Rope", "Casual Long Sleeve Shirt", "Long Sleeve Shirt", 89.00, 100));
        addShirt(new Shirts(18,"Filanto", "Filanto Trend Long Sleeve Blouse", "Long Sleeve Blouse", 39.90, 100));
        addShirt(new Shirts(19,"Filanto", "Filanto Trend Short Dress", "Short Dress", 59.90, 100));
    }

    // Method to add a shirt to the array
    public void addShirt(Shirts shirt) {
        if (shirtCount < shirtsList.length) {
            shirtsList[shirtCount] = shirt;
            shirtCount++;
        } else {
            System.out.println("Shirt list is full!");
        }
    }
    
    public void addShirts(Shirts[] newShirts) {
        for (Shirts shirt : newShirts) {
            addShirt(shirt); // Reuse addShirt() to add each shirt individually
        }
    }
    
        public void removeShirtById(int id) {
        boolean found = false;
        for (int i = 0; i < shirtCount; i++) {
            if (shirtsList[i].getShirtID() == id) {
                found = true;
                System.out.println("Removing shirt: " + shirtsList[i].getName());
                // Shift remaining shirts to fill the gap
                for (int j = i; j < shirtCount - 1; j++) {
                    shirtsList[j] = shirtsList[j + 1];
                }
                shirtsList[shirtCount - 1] = null; // Clear the last shirt slot
                shirtCount--; // Decrement the count
                break;
            }
        }
        if (!found) {
            System.out.println("Shirt with ID " + id + " not found.");
        }
    }

    // Display shirts
    public void displayShirts() {
        System.out.println("Available Shirts:");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-20s %-40s %-40s %-10s %-10s\n", "ID","Brand", "Name", "Description", "Price", "Stock");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < shirtCount; i++) {
            Shirts shirt = shirtsList[i];
            System.out.printf("%-10d %-20s %-40s %-40s %-10.2f %-10d\n",
                    shirt.getShirtID(),
                    shirt.getShirtBrand(),
                    shirt.getName(),
                    shirt.getDescription(),
                    shirt.getPrice(),
                    shirt.getStockQuantity());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
    }

    // Sort shirts by brand
    public void sortbyShirtBrand() {
        for (int i = 0; i < shirtCount - 1; i++) {
            for (int j = i + 1; j < shirtCount; j++) {
                if (shirtsList[i].getShirtBrand().compareTo(shirtsList[j].getShirtBrand()) > 0) {
                    Shirts temp = shirtsList[i];
                    shirtsList[i] = shirtsList[j];
                    shirtsList[j] = temp;
                }
            }
        }
        System.out.println("Shirts have been sorted by brands:");
        displayShirts();
    }

    // Sort shirts by price
    public void sortbyPrice() {
        for (int i = 0; i < shirtCount - 1; i++) {
            for (int j = i + 1; j < shirtCount; j++) {
                if (shirtsList[i].getPrice() > shirtsList[j].getPrice()) {
                    Shirts temp = shirtsList[i];
                    shirtsList[i] = shirtsList[j];
                    shirtsList[j] = temp;
                }
            }
        }
        System.out.println("Shirts have been sorted by price:");
        displayShirts();
    }

    // Get a shirt by its ID
    public Shirts getShirtById(int id) {
        for (int i = 0; i < shirtCount; i++) {
            if (shirtsList[i].getShirtID() == id) {
                return shirtsList[i];
            }
        }
        return null;
    }

        public boolean isShirtIDUnique(int shirtID) {
        for (Shirts shirt : shirtsList) {
            if (shirt.getShirtID() == shirtID) {
                return false; // ID is not unique
            }
        }
        return true; // ID is unique
    }
    public Shirts[] getShirtsList() {
        return shirtsList;
    }

    public void setShirtsList(Shirts[] shirtsList) {
        this.shirtsList = shirtsList;
    }

    public int getShirtCount() {
        return shirtCount;
    }

    public void setShirtCount(int shirtCount) {
        this.shirtCount = shirtCount;
    }

    @Override
    public String toString() {
        return "ShirtStore{" + "shirtsList=" + shirtsList + ", shirtCount=" + shirtCount + '}';
    }
    
}
