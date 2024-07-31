package sweet.dev;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

public class Order {
    private String orderId;
    private String username;  // Added to identify the user who placed the order

    private String status;
    private LinkedList<OrderDetails> orderDetails;
    private double totalPrice;
    private double totalCost;
    private LocalDate date;

    public Order(String orderId, String username, LinkedList<OrderDetails>orderDetails ) {
        this.orderId = orderId;
        this.username = username;
        this.orderDetails = orderDetails;
        this.date=LocalDate.now();
        this.totalPrice = calculateTotalPrice();
        this.totalCost = calculateTotalCost();
        this.status="pending";

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public LocalDate getDate() {
        return date;
    }

    private double calculateTotalCost() {
        double total = 0.0;
        for (OrderDetails detail : orderDetails) {
            total += detail.getProduct().getCost() * detail.getQuantity();
        }
        return total;
    }

    public LinkedList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    private double calculateTotalPrice() {
        double total = 0.0;
        for (OrderDetails detail : orderDetails) {
            total += detail.getTotalPrice();
        }
        return total;
    }

    // Getter and setter methods
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
