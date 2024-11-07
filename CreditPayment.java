package test;

public class CreditPayment extends Payment {
    private String creditCardNumber;

    public CreditPayment(String paymentID, double amount, String creditCardNumber) {
        super(paymentID, amount, "Credit");
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing credit card payment. Card Number: " + creditCardNumber);
        super.processPayment();
    }

    @Override
    public String toString() {
        return "CreditPayment{creditCardNumber='" + creditCardNumber + "', " + super.toString() + "}";
    }
}
