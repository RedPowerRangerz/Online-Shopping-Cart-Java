
package test;

public class TnGPayment extends Payment{
    private String TnGPin;

    public TnGPayment(String TnGPin, String paymentID, double amount, String paymentMethod) {
        super(paymentID, amount, paymentMethod);
        this.TnGPin = TnGPin;
    }
    
    @Override
    public void processPayment() {
        System.out.println("Processing TnG payment. TnG Pin Number: " + TnGPin);
        super.processPayment();
    }

    @Override
    public String toString() {
        return "TnGPayment{" + "TnGPin=" + TnGPin + '}';
    }
    
}
