package test;

abstract class Payment {
    private String paymentID;
    private double amount;
    private String paymentMethod;

    public Payment(String paymentID, double amount, String paymentMethod) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void processPayment() {
        // Simulate payment processing
        System.out.printf("Processing payment of RM%.2f using %s method.%n", amount, paymentMethod);
        // Add logic here for payment gateway, etc.
        System.out.println("Payment successful. |Payment ID|: " + paymentID);
    }

    @Override
    public String toString() {
        return "Payment{paymentID='" + paymentID + "', amount=" + amount + ", paymentMethod='" + paymentMethod + "'}";
    }
}
