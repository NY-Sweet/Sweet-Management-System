package sweet.dev;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class OrderManager {
    private LinkedList<Order> orders;
    private boolean successOperation;
    private static final Logger logger = Logger.getLogger(OrderManager.class.getName());

    public boolean isSuccessOperation() {
        return successOperation;
    }

    public OrderManager() {
        this.orders = new LinkedList<>();
    }
    public OrderManager(LinkedList<Order> orders) {
        this.orders = orders;
    }


    public void addOrder(Order order) {
        orders.add(order);
    }

    public void viewDailySalesAndProfits(int day, int month, int year) {
        successOperation=false;
        double totalSales = 0.0;
        double totalCost = 0.0;
        StringBuilder dailyReport = new StringBuilder();
        dailyReport.append(String.format("%-10s %-20s %-10s %-10s %-15s%n", "Order ID", "Username", "Total Price", "Total Cost", "Date"));
        dailyReport.append("------------------------------------------------------------\n");

        for (Order order : orders) {
            LocalDate orderDate = order.getDate();
            if (orderDate.getDayOfMonth() == day && orderDate.getMonthValue() == month && orderDate.getYear() == year) {
                totalSales += order.getTotalPrice();
                totalCost += order.getTotalCost();
                dailyReport.append(String.format("%-10s %-20s %-10.2f %-10.2f %-15s%n",
                        order.getOrderId(), order.getUsername(), order.getTotalPrice(), order.getTotalCost(), orderDate.toString()));
            }
        }

        logger.info(dailyReport.toString());
        logger.info(String.format("Total Sales: %.2f, Total Cost: %.2f, Profit: %.2f", totalSales, totalCost, totalSales - totalCost));
        successOperation=true;
    }

    public void viewMonthlySalesAndProfits(int month, int year) {
        successOperation=false;
        double totalSales = 0.0;
        double totalCost = 0.0;
        StringBuilder monthlyReport = new StringBuilder();
        monthlyReport.append(String.format("%-10s %-20s %-10s %-10s %-15s%n", "Order ID", "Username", "Total Price", "Total Cost", "Date"));
        monthlyReport.append("------------------------------------------------------------\n");

        for (Order order : orders) {
            LocalDate orderDate = order.getDate();
            if (orderDate.getMonthValue() == month && orderDate.getYear() == year) {
                totalSales += order.getTotalPrice();
                totalCost += order.getTotalCost();
                monthlyReport.append(String.format("%-10s %-20s %-10.2f %-10.2f %-15s%n",
                        order.getOrderId(), order.getUsername(), order.getTotalPrice(), order.getTotalCost(), orderDate.toString()));
            }
        }

        logger.info(monthlyReport.toString());
        logger.info(String.format("Total Sales: %.2f, Total Cost: %.2f, Profit: %.2f", totalSales, totalCost, totalSales - totalCost));
        successOperation=true;
    }


    public LinkedList<Order> getOrders() {
        return orders;
    }

    public void showBestProducts() {
        successOperation=false;
        Map<String, Integer> productSales = new HashMap<>();

        // Count the total quantity sold for each product
        for (Order order : orders) {
            for (OrderDetails orderDetail : order.getOrderDetails()) {
                String productId = orderDetail.getProduct().getId();
                int quantitySold = orderDetail.getQuantity();

                productSales.put(productId, productSales.getOrDefault(productId, 0) + quantitySold);
            }
        }

        // Sort the products by quantity sold in descending order and get the top 5
        List<Map.Entry<String, Integer>> topProducts = productSales.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(5)
                .collect(Collectors.toList());

        // Collect the top 5 products
        StringBuilder bestProducts = new StringBuilder("Top 5 Best Selling Products:\n");
        for (Map.Entry<String, Integer> entry : topProducts) {
            bestProducts.append(String.format("Product ID: %s, Quantity Sold: %d\n", entry.getKey(), entry.getValue()));
        }

        // Log the best-selling products
        Logger logger = Logger.getLogger(OrderManager.class.getName());
        logger.info(bestProducts.toString());
        successOperation=true;

    }


    public void updateOrderStatus(String orderId, String newStatus) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setStatus(newStatus);
                String message="Order status updated: " + orderId + " to " + newStatus+"\n";
                StringBuilder table = new StringBuilder();
                printProduct(order, table);

                logger.info("Order status updated: " + orderId + " to " + newStatus);
                sendEmailTo("s12112422@stu.najah.edu",message+table.toString());
                return;
            }
        }
        logger.warning("Order not found: " + orderId);
    }

    private void printProduct(Order order, StringBuilder table) {
        table.append("Products:\n");
        table.append(String.format("  %-10s %-20s %-10s %-10s%n", "Product ID", "Name", "Quantity", "Price"));
        for (OrderDetails details : order.getOrderDetails()) {
            product product = details.getProduct();
            table.append(String.format("  %-10s %-20s %-10d %-10.2f%n",
                    product.getId(), product.getName(), details.getQuantity(), product.getPrice()));
        }
        table.append("---------------------------------------------------------\n");
    }


    public void sendEmailTo(String recipient,String s) {


        try {
            successOperation=false;
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("worldsweet974@gmail.com\n", "jrsd tqyg jehd umch");
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("worldsweet974@gmail.com\n"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient, false));

            message.setSubject("sweet shop order");
            message.setText(s);
            Transport.send(message);
            successOperation=true;
        } catch (MessagingException m) {
            logger.info("MessagingException");
        }
    }



    public void showOrders(String state) {
        successOperation = false;
        StringBuilder table = new StringBuilder();
        table.append(String.format("%-10s %-20s %-10s %-15s%n", "Order ID", "Username", "Total Price", "Date"));
        table.append("---------------------------------------------------------\n");

        for (Order order : orders) {
            if (state.equals(order.getStatus())) {
                table.append(String.format("%-10s %-20s %-10.2f %-15s%n",
                        order.getOrderId(), order.getUsername(), order.getTotalPrice(), order.getDate()));

                // Append product details
                printProduct(order, table);
            }
        }

        logger.info("Delivered Orders:");
        logger.info(table.toString());
        successOperation = true;
    }


    public void showPendingOrders() {
        showOrders("pending");

    }

    public void showShippedOrders() {
        showOrders("shipped");

    }

    public void showDeliveredOrders() {
        showOrders("delivered");

    }
}
