package test;

import java.util.Date;

public class Order {
    private static int nextOrderID = 1000; // Static variable for unique order IDs
    private int orderID;
    private Date orderDate;
    private double totalAmount;

    // Constructor without passing orderID, auto-generating it
    public Order(Date orderDate, double totalAmount) {
        this.orderID = nextOrderID++; // Assign current value and increment
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
