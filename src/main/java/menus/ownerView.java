package menus;
import sweet.dev.*;

import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ownerView {

    private static final String ANSI_PURPLE = "\n\u001B[95m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String CHOICE_PROMPT = ANSI_WHITE + "Enter the number of your choice: " + ANSI_RESET;
    private final Scanner scanner;
    private final Logger logger;
    private supplier supplier;

    public ownerView(supplier supplier) {
        this.scanner = new Scanner(System.in);
        this.logger = Logger.getLogger(ownerView.class.getName());
        logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.addHandler(consoleHandler);

        this.supplier = supplier;
    }

    public void displayMenu() {
        while (true) {
            String menuOptions = ANSI_PURPLE + """
                ╔════════════════════════════╗
                ║       Owner Menu           ║
                ╠════════════════════════════╣
                ║ 1. Product Management      ║
                ║ 2. Reports                 ║
                ║ 3. Order Management        ║
                ║ 4. Messaging               ║
                ║ 5. Account Management      ║
                ║ 6. Go Back                 ║
                ╚════════════════════════════╝
                """ + ANSI_RESET + "\n" + CHOICE_PROMPT;
            logger.info(menuOptions);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageProducts();
                    break;
                case "2":
                    reports();
                    break;
                case "5":
                    logger.info("Exiting owner menu");
                    return;
                default:
                    logger.warning("Invalid menu choice: " + choice);
                    logger.info("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void manageProducts() {
        while (true) {
            String menuOptions = ANSI_PURPLE + """
                ╔══════════════════════════════════╗
                ║  Owner Product Management Menu   ║
                ╠══════════════════════════════════╣
                ║ 1. Show All Products             ║
                ║ 2. Add New Product               ║
                ║ 3. Edit a Product                ║
                ║ 4. Delete a Product              ║
                ║ 5. View Discounted Products      ║
                ║ 6. Set Discount Rule             ║
                ║ 7. Go Back                       ║
                ╚══════════════════════════════════╝
                """ + ANSI_RESET + "\n" + CHOICE_PROMPT;
            logger.info(menuOptions);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    supplier.getProductManager().showProducts();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    editProduct();
                    break;
                case "4":
                    deleteProduct();
                    break;
                case "5":
                    supplier.getProductManager().showDiscountProducts();
                    break;
                case "6":
                    setDiscountRule();
                    break;
                case "7":
                    logger.info("Exiting owner menu");
                    return;
                default:
                    logger.warning("Invalid menu choice: " + choice);
                    logger.info("Invalid choice. Please select a valid option.");
            }
        }
    }



    private void addProduct() {
        ProductManager productManager = supplier.getProductManager();

        logger.info("Enter product details:");
        logger.info("Product ID: ");
        String id = scanner.nextLine();

        logger.info("Product Name: ");
        String name = scanner.nextLine();

        logger.info("Quantity: ");
        Integer quantity = Integer.parseInt(scanner.nextLine());

        logger.info("Price: ");
        Double price = Double.parseDouble(scanner.nextLine());

        logger.info("Cost: ");
        Double cost = Double.parseDouble(scanner.nextLine());

        logger.info("Expiration Day (DD): ");
        Integer day = Integer.parseInt(scanner.nextLine());

        logger.info("Expiration Month (MM): ");
        Integer month = Integer.parseInt(scanner.nextLine());

        logger.info("Expiration Year (YYYY): ");
        Integer year = Integer.parseInt(scanner.nextLine());

        logger.info("Discount Percentage: ");
        Double percentage = Double.parseDouble(scanner.nextLine());
        productManager.addProduct(id, name, quantity, price, cost, day, month, year, percentage);

        if (productManager.isOperationSuccess()) {
            logger.info("Product added successfully.");
        } else {
            logger.warning("Product with the same ID already exists. Operation failed.");
        }
    }

    private String productId(ProductManager productManager){
        productManager.showProducts();
        String id;
        while(true) {
            logger.info("Enter the ID of the product to edit: ");
            id = scanner.nextLine();
            if(productManager.findProduct(id)!=null)
                break;
            else
                logger.warning("Please enter a valid product id");

        }
        return id;
    }
    private void editProduct() {

        ProductManager productManager = supplier.getProductManager();
        String id=productId(productManager);
        while (true) {
            String editMenu = ANSI_PURPLE + """
            ╔══════════════════════════════════╗
            ║  Edit Product Menu               ║
            ╠══════════════════════════════════╣
            ║ 1. Edit Product Name             ║
            ║ 2. Edit Product Quantity         ║
            ║ 3. Edit Product Price            ║
            ║ 4. Edit Product Cost             ║
            ║ 5. Edit Product Expiration Date  ║
            ║ 6. Go Back                       ║
            ╚══════════════════════════════════╝
            """ + ANSI_RESET + "\n" + CHOICE_PROMPT;
            logger.info(editMenu);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    logger.info("Enter new product name: ");
                    String newName = scanner.nextLine();
                    productManager.editProductName(id, newName);
                    displayEditResult(productManager);
                    break;
                case "2":
                    logger.info("Enter new product quantity: ");
                    int newQuantity = Integer.parseInt(scanner.nextLine());
                    productManager.editProductQuantity(id, newQuantity);
                    displayEditResult(productManager);
                    break;
                case "3":
                    logger.info("Enter new product price: ");
                    double newPrice = Double.parseDouble(scanner.nextLine());
                    productManager.editProductPrice(id, newPrice);
                    displayEditResult(productManager);
                    break;
                case "4":
                    logger.info("Enter new product cost: ");
                    double newCost = Double.parseDouble(scanner.nextLine());
                    productManager.editProductCost(id, newCost);
                    displayEditResult(productManager);
                    break;
                case "5":
                    logger.info("Enter new expiration day (DD): ");
                    int newDay = Integer.parseInt(scanner.nextLine());
                    logger.info("Enter new expiration month (MM): ");
                    int newMonth = Integer.parseInt(scanner.nextLine());
                    logger.info("Enter new expiration year (YYYY): ");
                    int newYear = Integer.parseInt(scanner.nextLine());
                    productManager.editProductExpirationDate(id, newDay, newMonth, newYear);
                    displayEditResult(productManager);
                    break;
                case "6":
                    return;
                default:
                    logger.warning("Invalid menu choice: " + choice);
                    logger.info("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void displayEditResult(ProductManager productManager) {
        if (productManager.isOperationSuccess()) {
            logger.info("Product updated successfully.");
        } else {
            logger.warning("Product update failed. The product may not exist or the operation could not be completed.");
        }
    }

    private void deleteProduct() {
        ProductManager productManager = supplier.getProductManager();
        String id=productId(productManager);
        productManager.deleteProduct(id);
        if (productManager.isOperationSuccess()) {
            logger.info("Product deleted successfully.");
        } else {
            logger.warning("Product deleted failed. The product may not exist or the operation could not be completed.");
        }
    }

    private void setDiscountRule() {
        ProductManager productManager=supplier.getProductManager();

        logger.info("====== Set Discount Rule ======");
        logger.info("Enter Percentage: ");
        Double percentage = Double.parseDouble(scanner.nextLine());

        logger.info("Enter Days Before Expiration: ");
        Integer daysBeforeExpiration = Integer.parseInt(scanner.nextLine());

        productManager.setDiscountRule(percentage,daysBeforeExpiration);

    }


    private void reports() {
        OrderManager orderManager = supplier.getOrderManager();

        while (true) {
            String reportMenu = ANSI_PURPLE + """
            ╔══════════════════════════════════╗
            ║         Reports Menu             ║
            ╠══════════════════════════════════╣
            ║ 1. Daily Sales and Profits       ║
            ║ 2. Monthly Sales and Profits     ║
            ║ 3. Best-Selling Products         ║
            ║ 4. Annual Financial Reports      ║
            ║ 5. Go Back                       ║
            ╚══════════════════════════════════╝
            """ + ANSI_RESET + "\n" + CHOICE_PROMPT;
            logger.info(reportMenu);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    logger.info("Enter the day (DD): ");
                    int day = Integer.parseInt(scanner.nextLine());
                    logger.info("Enter the month (MM): ");
                    int month = Integer.parseInt(scanner.nextLine());
                    logger.info("Enter the year (YYYY): ");
                    int year = Integer.parseInt(scanner.nextLine());
                    orderManager.viewDailySalesAndProfits(day, month, year);
                    break;
                case "2":
                    logger.info("Enter the month (MM): ");
                    int monthForMonthlyReport = Integer.parseInt(scanner.nextLine());
                    logger.info("Enter the year (YYYY): ");
                    int yearForMonthlyReport = Integer.parseInt(scanner.nextLine());
                    orderManager.viewMonthlySalesAndProfits(monthForMonthlyReport, yearForMonthlyReport);
                    break;
                case "3":
                    orderManager.showBestProducts();
                    break;
                case "4":
                    logger.info("Enter the year (YYYY): ");
                    int yearForAnnualReport = Integer.parseInt(scanner.nextLine());
                    orderManager.showFinancialReports(yearForAnnualReport);
                    break;
                case "5":
                    return;
                default:
                    logger.warning("Invalid menu choice: " + choice);
                    logger.info("Invalid choice. Please select a valid option.");
            }
        }
    }



}
