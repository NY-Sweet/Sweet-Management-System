package sweet.dev;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.logging.Logger;

public class ProductManager {

    private LinkedList<product> products;
    private DiscountRule discountRule;

    private boolean operationSuccess;
    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());

    public ProductManager(LinkedList<product> products) {
        this.products = products;
        this.discountRule = new DiscountRule(30, 3);
    }

    public void addProduct(String id, String name, Integer quantity, Double price,Double cost, Integer day, Integer month, Integer year, Double percentage) {
       if(findProduct(id)==null) {
           product product=new product(id, name, quantity, price, cost, day, month, year, percentage);
           products.add(product);
           setOperationSuccess(true);
       }
       else
           setOperationSuccess(false);
    }

    public void deleteProduct(String id) {
        product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            products.remove(product);
            setOperationSuccess(true);
        }
    }

    public void showProducts() {

        applyDiscount();
        StringBuilder table = new StringBuilder();
        table.append(String.format("%-10s %-20s %-10s %-10s %-10s %-15s %-10s  %-10s%n",
                "ID", "Name", "Quantity", "Price", "Cost", "Expiration Date", "Discount (%)", "After Discount"));
        table.append("-----------------------------------------------------------------------------------------\n");

        for (product p : products) {
            String expirationDate = String.format("%02d/%02d/%04d", p.getDay(), p.getMonth(), p.getYear());
            table.append(String.format("%-10s %-20s %-10d %-10.2f %-10.2f %-15s %-10.2f %-10.2f%n",
                    p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getCost(), expirationDate, p.getDiscountPercentage(), p.getPrice() * (1 - p.getDiscountPercentage() / 100)));
        }

        logger.info(table.toString());
    }

    public void setDiscountRule(double percentage, int daysBeforeExpiration) {
        this.discountRule = new DiscountRule(percentage, daysBeforeExpiration);
        applyDiscount();
    }

    public void applyDiscount() {
        if (discountRule == null) {
            throw new IllegalStateException("No discount rule defined");
        }

        for (product product : products) {
            if (isProductNearExpiration(product)) {
                double discount = product.getPrice() * (discountRule.getPercentage() / 100);
                double newPrice = product.getPrice() - discount;
                product.setDiscountPercentage(discountRule.getPercentage());
            }
        }
    }

    private boolean isProductNearExpiration(product product) {
        LocalDate today = LocalDate.now();
        LocalDate expirationDate = LocalDate.of(product.getYear(), product.getMonth(), product.getDay());
        long daysUntilExpiration = ChronoUnit.DAYS.between(today, expirationDate);

        return daysUntilExpiration <= discountRule.getDaysBeforeExpiration();
    }

    public product findProduct(String productId) {
        for (product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public void editProductName(String id, String name) {
        product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            product.setName(name);
            setOperationSuccess(true);
        }
    }

    public void editProductQuantity(String id, int quantity) {
        product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            product.setQuantity(quantity);
            setOperationSuccess(true);
        }
    }

    public void editProductPrice(String id, double price) {
        product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            product.setPrice(price);
            setOperationSuccess(true);
        }
    }

    public void editProductCost(String id, double cost) {
        product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            product.setCost(cost);
            setOperationSuccess(true);
        }
    }

    public void editProductExpirationDate(String id, int day, int month, int year) {
        product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            product.setDay(day);
            product.setMonth(month);
            product.setYear(year);
            applyDiscount();
            setOperationSuccess(true);
        }
    }

    public void showDiscountProducts() {
        StringBuilder table = new StringBuilder();
        table.append(String.format("%-10s %-20s %-10s %-10s %-10s %-15s %-10s  %-10s%n",
                "ID", "Name", "Quantity", "Price", "Cost", "Expiration Date", "Discount (%)", "After Discount"));
        table.append("-----------------------------------------------------------------------------------------\n");

        applyDiscount();

        for (product p : products) {
            if (p.getDiscountPercentage() > 0) {
                String expirationDate = String.format("%02d/%02d/%04d", p.getDay(), p.getMonth(), p.getYear());
                table.append(String.format("%-10s %-20s %-10d %-10.2f %-10.2f %-15s %-10.2f %-10.2f%n",
                        p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getCost(), expirationDate, p.getDiscountPercentage(), p.getPrice() * (1 - p.getDiscountPercentage() / 100)));
            }
        }

        logger.info(table.toString());
    }

    public boolean isOperationSuccess() {
        return operationSuccess;
    }

    private void setOperationSuccess(boolean operationSuccess) {
        this.operationSuccess = operationSuccess;
    }
}
