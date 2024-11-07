package test;

public class Discount {
    private double discountAmount;
    private String discountCode;

    // Constructor
    public Discount(double discountAmount, String discountCode) {
        this.discountAmount = discountAmount;
        this.discountCode = discountCode;
    }

    // Method to apply discount to the payment
    public void applyDiscount(Payment payment, String inputCode) {
        if (inputCode.equals(discountCode)) {
            double discountedAmount = payment.getAmount() - discountAmount;
            if (discountedAmount < 0) {
                discountedAmount = 0;  // Ensure amount does not become negative
            }
            payment.setAmount(discountedAmount);
            System.out.println("Discount of RM" + discountAmount + " applied. New total: RM" + payment.getAmount());
        } else {
            System.out.println("Discount is no longer valid.");
        }
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
    
}
