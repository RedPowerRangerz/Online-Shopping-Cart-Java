package test;

public class DebitPayment extends Payment {
    private String debitCardNumber;

    public DebitPayment(String paymentID, double amount, String debitCardNumber) {
        super(paymentID, amount, "Debit");
        this.debitCardNumber = debitCardNumber;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing debit card payment. Debit Card Number: " + debitCardNumber);
        super.processPayment();
    }

    @Override
    public String toString() {
        return "DebitPayment{debitCardNumber='" + debitCardNumber + "', " + super.toString() + "}";
    }
}
