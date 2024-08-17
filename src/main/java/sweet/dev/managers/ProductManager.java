package sweet.dev.managers;

import sweet.dev.models.Product;
import sweet.format.PrettyFormatter;
import sweet.dev.models.DiscountRule;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class ProductManager {

    private LinkedList<Product> products;
    private DiscountRule discountRule;
    private boolean invalidProductId;

    private boolean operationSuccess;
    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());
    private static final String EXPIRATION_DATE_STRING = "Expiration Date";
    private static final String AFTER_DISCOUNT_STRING = "After Discount";
    private static final String QUANTITY_STRING = "Quantity";
    private static final String PRICE_STRING = "Price";
    private static final String DISCOUNT_STRING ="Discount (%)";
   private static final String EXPIRATION_DATE_FORMAT_STRING ="%02d/%02d/%04d";



    public List<Product> getProducts() {
        return products;
    }

    public ProductManager(LinkedList<Product> Products) {
        this.products = Products;
        this.discountRule = new DiscountRule(0, 0);
        setupLogger();
    }

    private void setupLogger() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
    }

    public void addProduct(String id, String name, Integer quantity, Double price,Double cost, Integer day, Integer month, Integer year, Double percentage) {
       if(findProduct(id)==null) {
           Product product=new Product(id, name, quantity, price, cost, day, month, year, percentage);
           products.add(product);
           setOperationSuccess(true);
       }
       else
           setOperationSuccess(false);
    }

    public void deleteProduct(String id) {
        Product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            products.remove(product);
            setOperationSuccess(true);
        }
    }

    public boolean showProducts() {
        applyDiscount();
        StringBuilder table = new StringBuilder();
        table.append(String.format("%-10s %-20s %-10s %-10s %-10s %-20s %-15s %-15s%n",
                "ID", "Name", QUANTITY_STRING, PRICE_STRING, "Cost", EXPIRATION_DATE_STRING, DISCOUNT_STRING, AFTER_DISCOUNT_STRING));
        table.append("===============================================================================================================================\n");
       List<Product> products1=getProducts();
        for (Product p : products1) {
            String expirationDate = String.format(EXPIRATION_DATE_FORMAT_STRING, p.getDay(), p.getMonth(), p.getYear());
            table.append(String.format("%-10s %-20s %-10d %-10.2f %-10.2f %-20s %-15.2f %-15.2f%n",
                    p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getCost(), expirationDate, p.getDiscountPercentage(), p.getPrice() * (1 - p.getDiscountPercentage() / 100)));
        }

        logger.info(table.toString());
        return true;
    }
    public boolean showProductsForCustomer() {
        applyDiscount();
        StringBuilder table = new StringBuilder();
        table.append(String.format("%-10s %-20s %-10s %-10s %-20s %-15s %-15s %-50s%n",
                "ID", "Name", QUANTITY_STRING, PRICE_STRING, EXPIRATION_DATE_STRING, DISCOUNT_STRING, AFTER_DISCOUNT_STRING, "Feedbacks"));
        table.append("==========================================================================================================================================================\n");

        List<Product> products1 = getProducts();
        for (Product p : products1) {
            String expirationDate = String.format(EXPIRATION_DATE_FORMAT_STRING, p.getDay(), p.getMonth(), p.getYear());
            String feedbacks = p.formatFeedbacks();

            table.append(String.format("%-10s %-20s %-10d %-10.2f %-20s %-15.2f %-15.2f %-50s%n",
                    p.getId(), p.getName(), p.getQuantity(), p.getPrice(), expirationDate,
                    p.getDiscountPercentage(), p.getPrice() * (1 - p.getDiscountPercentage() / 100),
                    feedbacks));
        }

        logger.info(table.toString());
        return true;
    }




    public void setDiscountRule(double percentage, int daysBeforeExpiration) {
        this.discountRule = new DiscountRule(percentage, daysBeforeExpiration);
        applyDiscount();
    }

    public void applyDiscount() {

        for (Product product : products) {
            if (isProductNearExpiration(product)) {
                double discount = product.getPrice() * (discountRule.getPercentage() / 100);
                double newPrice = product.getPrice() - discount;
                product.setDiscountPercentage(discountRule.getPercentage());
            }
        }
    }

    private boolean isProductNearExpiration(Product product) {
        LocalDate today = LocalDate.now();
        LocalDate expirationDate = LocalDate.of(product.getYear(), product.getMonth(), product.getDay());
        long daysUntilExpiration = ChronoUnit.DAYS.between(today, expirationDate);

        return daysUntilExpiration <= discountRule.getDaysBeforeExpiration();
    }

    public Product findProduct(String productId) {
        invalidProductId=true;
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                invalidProductId=false;
                return product;
            }
        }
        return null;
    }

    public void editProductName(String id, String name) {
        Product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            product.setName(name);
            setOperationSuccess(true);
        }
    }

    public void editProductQuantity(String id, int quantity) {
        Product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            product.setQuantity(quantity);
            setOperationSuccess(true);
        }
    }

    public void editProductPrice(String id, double price) {
        Product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            product.setPrice(price);
            setOperationSuccess(true);
        }
    }

    public void editProductCost(String id, double cost) {
        Product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            product.setCost(cost);
            setOperationSuccess(true);
        }
    }

    public void editProductExpirationDate(String id, int day, int month, int year) {
        Product product = findProduct(id);
        setOperationSuccess(false);
        if (product != null) {
            product.setDay(day);
            product.setMonth(month);
            product.setYear(year);
            applyDiscount();
            setOperationSuccess(true);
        }
    }

    public boolean showDiscountProducts() {
        StringBuilder table = new StringBuilder();
        table.append(String.format("%-10s %-20s %-10s %-10s %-10s %-15s %-10s  %-10s%n",
                "ID", "Name", QUANTITY_STRING, PRICE_STRING, "Cost", EXPIRATION_DATE_STRING, DISCOUNT_STRING,AFTER_DISCOUNT_STRING));
        table.append("-----------------------------------------------------------------------------------------------------\n");

        applyDiscount();

        for (Product p : products) {
            if (p.getDiscountPercentage() > 0) {
                String expirationDate = String.format(EXPIRATION_DATE_FORMAT_STRING, p.getDay(), p.getMonth(), p.getYear());
                table.append(String.format("%-10s %-20s %-10d %-10.2f %-10.2f %-15s %-10.2f %-10.2f%n",
                        p.getId(), p.getName(), p.getQuantity(), p.getPrice(), p.getCost(), expirationDate, p.getDiscountPercentage(), p.getPrice() * (1 - p.getDiscountPercentage() / 100)));
            }
        }

        logger.info(table.toString());
        return true;

    }

    public boolean isOperationSuccess() {
        return operationSuccess;
    }

    public boolean isInvalidProductId() {
        return invalidProductId;
    }

    private void setOperationSuccess(boolean operationSuccess) {
        this.operationSuccess = operationSuccess;
    }
}
