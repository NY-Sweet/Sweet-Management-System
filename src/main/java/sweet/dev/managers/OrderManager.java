package sweet.dev.managers;

import sweet.format.PrettyFormatter;
import sweet.dev.models.Order;
import sweet.dev.models.OrderDetails;
import sweet.dev.models.product;
import sweet.dev.models.supplier;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class OrderManager {
    private LinkedList<Order> orders;
    private boolean successOperation;
    private sweet.dev.models.supplier supplier;
    private static final Logger logger = Logger.getLogger(OrderManager.class.getName());


    public boolean isSuccessOperation() {
        return successOperation;
    }


    public OrderManager(supplier supplier) {
        this.supplier = supplier;
        this.orders= supplier.getOrders();
        setupLogger();
    }
    private void setupLogger() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
    }


    public boolean addOrder(Order order) {
       if( isStockAvailable(order)) {
           orders.add(order);
           reserveStock(order);
           return true;
       }
       else
       {
           return false;
       }
    }

    public void viewDailySalesAndProfits(int day, int month, int year) {
        successOperation = false;
        double totalSales = 0.0;
        double totalCost = 0.0;
        StringBuilder dailyReport = new StringBuilder();

        // Header
        dailyReport.append(String.format("%-10s %-20s %-12s %-12s %-15s%n",
                "Order ID", "Username", "Total Price", "Total Cost", "Date"));
        dailyReport.append("--------------------------------------------------------------\n");

        // Add data rows
        for (Order order : orders) {
            LocalDate orderDate = order.getDate();
            if (orderDate.getDayOfMonth() == day && orderDate.getMonthValue() == month && orderDate.getYear() == year) {
                totalSales += order.getTotalPrice();
                totalCost += order.getTotalCost();
                dailyReport.append(String.format("%-10s %-20s %-12.2f %-12.2f %-15s%n",
                        order.getOrderId(), order.getUsername(), order.getTotalPrice(), order.getTotalCost(), orderDate.toString()));
            }
        }

        dailyReport.append(String.format("%n %-10s %-20s %-12.2f %-12.2f%n",
                "Total", "", totalSales, totalCost));
        dailyReport.append(String.format("Profit: %.2f%n", totalSales - totalCost));

        logger.info(dailyReport.toString());
        successOperation = true;
    }


    public void viewMonthlySalesAndProfits(int month, int year) {
        final StringBuilder monthlyReport = new StringBuilder();
        monthlyReport.append(String.format("%-10s %-20s %-12s %-12s %-15s%n",
                "Order ID", "Username", "Total Price", "Total Cost", "Date"));
        monthlyReport.append("--------------------------------------------------------------%n");

        final double[] totals = {0.0, 0.0}; // [totalSales, totalCost]

        for (Order order : orders) {
            LocalDate orderDate = order.getDate();
            if (orderDate.getMonthValue() == month && orderDate.getYear() == year) {
                totals[0] += order.getTotalPrice();
                totals[1] += order.getTotalCost();
                monthlyReport.append(String.format("%-10s %-20s %-12.2f %-12.2f %-15s%n",
                        order.getOrderId(), order.getUsername(), order.getTotalPrice(),
                        order.getTotalCost(), orderDate.toString()));
            }
        }

        final double totalSales = totals[0];
        final double totalCost = totals[1];

        logger.info(() -> {
            StringBuilder logMessage = new StringBuilder(monthlyReport);
            logMessage.append(String.format("%nTotal Sales: %.2f, Total Cost: %.2f, Profit: %.2f",
                    totalSales, totalCost, totalSales - totalCost));
            return logMessage.toString();
        });

        successOperation = true;
    }



    public void showBestProducts() {
        Map<String, Integer> productSales = new HashMap<>();

        // Count the total quantity sold for each product
        for (Order order : orders) {
            for (OrderDetails orderDetail : order.getOrderDetails()) {
                String productId = orderDetail.getProduct().getId();
                int quantitySold = orderDetail.getQuantity();
                productSales.merge(productId, quantitySold, Integer::sum);
            }
        }

        // Sort the products by quantity sold in descending order and get the top 5
        List<Map.Entry<String, Integer>> topProducts = productSales.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());

        // Log the best-selling products
        logger.info(() -> {
            StringBuilder bestProducts = new StringBuilder("Top 5 Best Selling Products:%n");
            for (Map.Entry<String, Integer> entry : topProducts) {
                bestProducts.append(String.format("Product ID: %s, Quantity Sold: %d%n", entry.getKey(), entry.getValue()));
            }
            return bestProducts.toString();
        });

        successOperation = true;
    }

    public void reserveStock(Order order) {
        for (OrderDetails details : order.getOrderDetails()) {
            product product = supplier.getProductManager().findProduct(details.getProduct().getId());
            if (product != null) {
                product.setQuantity(product.getQuantity() - details.getQuantity());
            }
        }
    }

    public void restoreStock(Order order) {
        for (OrderDetails details : order.getOrderDetails()) {
            product product = supplier.getProductManager().findProduct(details.getProduct().getId());
            if (product != null) {
                product.setQuantity(product.getQuantity() + details.getQuantity());
            }
        }
    }



    private boolean isStockAvailable(Order order) {
        for (OrderDetails details : order.getOrderDetails()) {
            product product = supplier.getProductManager().findProduct(details.getProduct().getId());
            if (product == null || product.getQuantity() < details.getQuantity()) {
                logger.info("Sorry the product "+product.getName()+" quantity not available the max quantity you can order "+product.getQuantity());
                return false;
            }
        }
        return true;
    }

    public void updateOrderStatus(String orderId, String newStatus, UserManager userManager) {
        Order order=getOrderById(orderId);
            if (order!=null) {
                if (newStatus.equals("rejected")) {
                    restoreStock(order);
                }

                order.setStatus(newStatus);
                String message = "Order status updated: " + orderId + " to " + newStatus + "\n";
                StringBuilder table = new StringBuilder();
                printProduct(order, table);
                String username = order.getUsername();
                String email = userManager.getTheUser(username).getEmail();
                logger.info("Order status updated: " + orderId + " to " + newStatus);
                sendEmailTo(email, message + table.toString());
                successOperation = true;
                if(newStatus.equals("delivered"))
                {
                    userManager.getTheUser(username).addOrder(order);
                }
                return;
            }
        else {
                logger.warning("Order not found: " + orderId);
                successOperation = false;
            }
    }

    public Order getOrderById(String id)
    {
        for (Order order:orders)
        {
            if (order.getOrderId().equals(id))
                return order;
        }
        return null;
    }

    public void printProduct(Order order, StringBuilder table) {
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

             String emailUsername = System.getenv("EMAIL_USERNAME");
          String emailPassword = System.getenv("EMAIL_PASSWORD");
            Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailUsername, emailPassword);
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


    public boolean showFinancialReports(int year) {
        double[] totalSalesByMonth = new double[12];
        double[] totalCostByMonth = new double[12];


            for (int month = 1; month <= 12; month++) {
                double[] monthlyTotals = MonthlySalesAndProfits(month, year);
                totalSalesByMonth[month - 1] += monthlyTotals[0];
                totalCostByMonth[month - 1] += monthlyTotals[1];
            }

        printAnnualReport(year, totalSalesByMonth, totalCostByMonth);
        return true;
    }
    private double[] MonthlySalesAndProfits(int month, int year) {
        double totalSales = 0.0;
        double totalCost = 0.0;
        totalSales = getTotalSalesForMonth(month, year);
        totalCost = getTotalCostForMonth(month, year);

        return new double[]{totalSales, totalCost};
    }
    private void printAnnualReport(int year, double[] totalSalesByMonth, double[] totalCostByMonth) {
        StringBuilder report = new StringBuilder();
        report.append(String.format("Financial Report for Year %d%n", year));
        report.append(String.format("%-10s %-15s %-15s %-15s%n", "Month", "Total Sales", "Total Cost", "Profit"));
        report.append("---------------------------------------------------------------\n");

        // Add each month's data to the report
        for (int month = 0; month < 12; month++) {
            double profit = totalSalesByMonth[month] - totalCostByMonth[month];
            report.append(String.format("%-10d %-15.2f %-15.2f %-15.2f%n", month + 1, totalSalesByMonth[month], totalCostByMonth[month], profit));
        }

        logger.info(report.toString());
    }


    public double getTotalSalesForMonth(int month, int year) {
        double totalSales = 0.0;

        for (Order order : orders) {
            LocalDate orderDate = order.getDate();
            if (orderDate.getMonthValue() == month && orderDate.getYear() == year) {
                totalSales += order.getTotalPrice();
            }
        }

        return totalSales;
    }

    public double getTotalCostForMonth(int month, int year) {
        double totalCost = 0.0;

        for (Order order : orders) {
            LocalDate orderDate = order.getDate();
            if (orderDate.getMonthValue() == month && orderDate.getYear() == year) {
                totalCost += order.getTotalCost();
            }
        }

        return totalCost;
    }



}
