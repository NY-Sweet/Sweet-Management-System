package sweet.dev.models;

public class OrderDetails {
    private Product product;
    private int quantity;
    private double totalPrice;

    public OrderDetails(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }


}