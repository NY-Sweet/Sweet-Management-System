package menus;
import sweet.dev.UserManager;
import sweet.dev.*;

import java.util.*;
import java.util.function.Supplier;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class UserView {
    private static final String ANSI_PURPLE = "\n\u001B[95m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String CHOICE_PROMPT = ANSI_WHITE + "Enter the number of your choice: " + ANSI_RESET;
    private final Scanner scanner;
    private final Logger logger;

    private user User;
    private UserManager userManager;
    private  RecipeManager recipeManager;
    private  SupplierManager supplierManager ;
    public UserView(user User,UserManager userManager ,RecipeManager recipeManager ,SupplierManager supplierManager) {
        this.scanner = new Scanner(System.in);
        this.logger = Logger.getLogger("UserView");
        logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.addHandler(consoleHandler);
        this.userManager = userManager;
        this.User = User;
        this.supplierManager = supplierManager;
        this.recipeManager = recipeManager;
    }

    public void displayMenu() {
        while (true) {
            String menuOptions = ANSI_PURPLE + """
                ╔════════════════════════════╗
                ║        User Menu           ║
                ╠════════════════════════════╣
                ║ 1. Purchase desserts       ║
                ║ 2. Post A recipe           ║
                ║ 3. Browse Recipes          ║
                ║ 4. Messaging               ║
                ║ 5. Give A feedBack         ║
                ║ 6. Account Management      ║
                ║ 7. Go Back                 ║
                ╚════════════════════════════╝
                """ + ANSI_RESET + "\n" + CHOICE_PROMPT;
            logger.info(menuOptions);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    PurchaseDesserts();
                    break;
                case "2":
                    PostaRecipe ();
                    break;
                case "3":
                    BrowseRecipes();
                    break;
                case "4":
                    Messaging();
                    break;
                case "5":
                    GiveAfeedBack();
                    break;
                case "6":
                    UserAccountManagement();
                    break;
                case "7":
                    logger.info("Exiting User menu");
                    return;
                default:
                    logger.warning("Invalid menu choice: " + choice);
                    logger.info("Invalid choice. Please select a valid option.");
            }
        }
    }

    private void UserAccountManagement() {
        while (true) {
        String menuOptions = ANSI_PURPLE + """
                ╔════════════════════════════╗
                ║      Account Setting       ║
                ╠════════════════════════════╣
                ║ 1. Show Account Details    ║
                ║ 2. Update Your Account     ║
                ║ 3. Go Back                 ║
                ╚════════════════════════════╝
                """ + ANSI_RESET + "\n" + CHOICE_PROMPT;
        logger.info(menuOptions);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                logger.info("Show Account Details");
                logger.info("1.UserName: " + User.getUserName());
                logger.info("2.Password: " + User.getPassword());
                logger.info("3.Email: " + User.getEmail());
                logger.info("4. City"+User.getCity());
            case "2":
                logger.info("Enter the Number of info you want to update ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        logger.info("Enter Your New User Name");
                        String newUserName = scanner.nextLine();
                        User.userName=newUserName;

                    case 2:
                        logger.info("Enter Your New Password");
                        String newPassword = scanner.nextLine();
                        User.setPassword(newPassword);
                    case 3 :
                        logger.info("Enter Your New Email");
                        String newEmail = scanner.nextLine();
                        User.setEmail(newEmail);
                    case 4 :
                        logger.info("Enter Your New City");
                        String newCity = scanner.nextLine();
                        User.setCity(newCity);
                    default:
                        logger.warning("Invalid menu choice: " + choice);
                        logger.info("Invalid choice. Please select a valid option.");
                }


            case "3":
                return;
        }}

    }

    private void GiveAfeedBack() {
        while (true) {
        logger.info("Which one you want to give feed backfor");
        logger.info("1. Product");
        logger.info("2. Posted Recipe");
        logger.info("3. Go Back ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                LinkedList<Order> orders = User.getOrders();
                Map<String, product> productMap = new HashMap<>();

                logger.info("Here's the Product you have ordered:");
                logger.info(String.format("%-15s %-20s", "Product Id", "Name"));

                for (Order order : orders) {
                    LinkedList<OrderDetails> orderDetailsList = order.getOrderDetails();
                    for (OrderDetails orderDetails : orderDetailsList) {
                        product product = orderDetails.getProduct();
                        String productId = product.getId();
                        productMap.put(productId, product); // Map product ID to the product
                        logger.info(String.format("%-15s %-20s", productId, product.getName()));
                    }
                }

                logger.info("Enter the product ID you want to give feedback for: ");
                String productId = scanner.nextLine().trim();

                while (productId.isEmpty()) {
                    logger.warning("You entered an empty ID. Please enter a valid product ID:");
                    productId = scanner.nextLine().trim();
                }

                if (productMap.containsKey(productId)) {
                    logger.info("Enter the feedback content: ");
                    String feedbackContent = scanner.nextLine().trim();

                    // Add feedback to the product retrieved from the map
                    productMap.get(productId).addFeedback(feedbackContent);
                    logger.info("Feedback added successfully.");
                } else {
                    logger.warning("Invalid product ID entered.");
                }
                break;
            case 2 :
                recipeManager.ShowAllRecipes();
                logger.info("Enter the Recipe id you want give a feedback for ");
                int RecipeID = scanner.nextInt();
                logger.info("Enter the feedback Content ");
                String feedbackContent2 = scanner.nextLine();
                recipeManager.searchRecipeById(RecipeID).addFeedback(feedbackContent2);
                break;
            case 3 :
                return;
        }}
    }

    private void Messaging() {
    }

    private void BrowseRecipes() {
        recipeManager.ShowAllRecipes();
    }

    private void PostaRecipe() {
        logger.info("New Recipe :");
        logger.info("Enter Recipe Name");
        String recipeName = scanner.nextLine();
        logger.info("Enter Recipe Ingrediants Number");
        int ingrediantsNumber = scanner.nextInt();

        StringBuilder ingredients = new StringBuilder();
        for (int i = 0; i < ingrediantsNumber; i++) {
            logger.info("Enter Recipe Ingredients ");
             ingredients.append(scanner.nextLine());
        }

        logger.info("Enter Recipe Steps ");
        String steps = scanner.nextLine();
        Recipe recipe= new Recipe(recipeName,ingrediantsNumber, ingredients.toString(),steps,User.getUserName());
        recipeManager.postRecipe(recipe);

    }

    private void PurchaseDesserts() {
      logger.info("Our Shops :");
        List<supplier> OurSuppliers =supplierManager.getSuppliers();
        for (supplier supplier : OurSuppliers){
            logger.info(supplier.getShopName());
        }
        logger.info("Enter shop name ");
        String shopName = scanner.nextLine();
        supplier Supplier = supplierManager.getTheSupplierByUsingShopName(shopName);
        ProductManager prodManager= Supplier.getProductManager();
        prodManager.showProducts();
        LinkedList<OrderDetails> orderDetailsList = new LinkedList<>();
        while (true) {
            logger.info("Enter the product ID to add to your order or type 'done' to finish: ");
            String productId = scanner.nextLine();

            if (productId.equalsIgnoreCase("done")) {
                break; // Exit the loop if the user is done adding products
            }

            product product = prodManager.findProduct(productId);
            if (product != null) {
                logger.info("Enter the quantity for " + product.getName() + ": ");
                int quantity = Integer.parseInt(scanner.nextLine());
                orderDetailsList.add(new OrderDetails(product, quantity));
                logger.info(quantity + " units of " + product.getName() + " added to your order.");
            } else {
                logger.warning("Invalid product ID entered. Please try again.");
            }
        }
        if (!orderDetailsList.isEmpty()) {

            String orderId = User.getUserName() + (Supplier.getOrders().size() + 1);
            Order newOrder = new Order(orderId, User.getUserName(), orderDetailsList);
            Supplier.getOrderManager().addOrder(newOrder);

            logger.info("Order placed successfully with Order ID: " + orderId);
        } else {
            logger.warning("No products were added to the order.");
        }

    }


}
