package sweet.dev;

public class OrderDetails {
    private product product;
    private int quantity;
    private double totalPrice;

    public OrderDetails(product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }


}