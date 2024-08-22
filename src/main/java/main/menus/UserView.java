package main.menus;
import main.format.PrettyFormatter;
import sweet.dev.managers.*;
import sweet.dev.models.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class UserView {
    private static final String ANSI_PURPLE = "\n\u001B[95m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String CHOICE_PROMPT = ANSI_WHITE + "Enter the number of your choice: " + ANSI_RESET;
    private final Scanner scanner;
    private final Logger logger;

    private User user;
    private RecipeManager recipeManager;
    private SupplierManager supplierManager ;
    private MessageManager messageManager ;
    private static boolean isLoggerConfigured = false;

    public UserView(User user , RecipeManager recipeManager , SupplierManager supplierManager, MessageManager messageManager) {
        this.scanner = new Scanner(System.in);
        this.logger = Logger.getLogger("UserView");
        if (!isLoggerConfigured) {
            logger.setUseParentHandlers(false);
            logger.info("Setting up logger...");

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new PrettyFormatter());
            logger.addHandler(consoleHandler);

            isLoggerConfigured = true; // Prevent further configuration
        }
        this.user = user;
        this.supplierManager = supplierManager;
        this.recipeManager = recipeManager;
        this.messageManager=messageManager;
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
                ║ 7.Find A suitable Recipe   ║
                ║ 8. Go Back                 ║
                ╚════════════════════════════╝
                """ + ANSI_RESET + "\n" + CHOICE_PROMPT;
            logger.info(menuOptions);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    purchaseDesserts();
                    break;
                case "2":
                    postaRecipe();
                    break;
                case "3":
                    browseRecipes();
                    break;
                case "4":
                    messagingSys();
                    break;
                case "5":
                    giveAfeedBack();
                    break;
                case "6":
                    userAccountManagement();
                    break;
                case "7":
                    findaRecipe();
                    break;
                case "8":
                    logger.info("Exiting User menu");
                    return;
                default:
                    printInvalidMenuChoice(choice);
            }
        }
    }
    private void findaRecipe() {
        while (true) {
            String menuOptions = ANSI_PURPLE + """
                ╔══════════════════════════════════════════╗
                ║                 Recipes                  ║
                ╠══════════════════════════════════════════╣
                ║ 1. Find A Recipe Based On Dietary        ║
                ║ 2. Find A Recipe Based On Alergry        ║
                ║ 3. Go Back                               ║
                ║                                          ║
                ╚══════════════════════════════════════════╝
                """ + ANSI_RESET + "\n" + CHOICE_PROMPT;
            logger.info(menuOptions);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    logger.info("Enter the Dietary Your looking for");
                    String dietary = scanner.nextLine();
                    recipeManager.filterRecipesByDietaryRestrictions(recipeManager.getValidatedRecipes(),dietary);
                    break;
                case "2":
                    logger.info("Enter the allergy Your looking for");
                    String allergy = scanner.nextLine();
                    Set<String> allergrySet = new HashSet<>();
                    allergrySet.add(allergy);
                    recipeManager.filterRecipesByAllergies(recipeManager.getValidatedRecipes(),allergrySet);
                    break;
                case "3":
                    return;
                default:
                    if (logger.isLoggable(Level.WARNING)) {
                        logger.warning("Invalid menu choice: " + choice);

                    }
            }

        }
    }

    private void userAccountManagement() {

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
                    logger.info("1.UserName: " + user.getUserName());
                    logger.info("2.Password: " + user.getPassword());
                    logger.info("3.Email: " + user.getEmail());
                    logger.info("4. City:  "+ user.getAdress().getCity());
                    logger.info("5.Phone Number: "+ user.getPhoneNum());
                    logger.info("6.Home Number: " + user.getAdress().getHomeNum());
                    logger.info("7.Street: " + user.getAdress().getStreet());
                    break;
                case "2":
                    logger.info("Enter the Number of info you want to update ");
                    int number = scanner.nextInt();
                    scanner.nextLine();
                    switch (number) {
                        case 1:
                            logger.info("Enter Your New User Name");
                            String newUserName = scanner.nextLine();
                            user.setUserName(newUserName);
                            break;

                        case 2:
                            logger.info("Enter Your New Password");
                            String newPassword = scanner.nextLine();
                            user.setPassword(newPassword);
                            break;
                        case 3 :
                            logger.info("Enter Your New Email");
                            String newEmail = scanner.nextLine();
                            user.setEmail(newEmail);
                            break;
                        case 4 :
                            logger.info("Enter Your New City");
                            String newCity = scanner.nextLine();
                            user.getAdress().setCity(newCity);
                            break;
                        case 5:
                            logger.info("Enter Your New Phone Number");
                            String newPhone = scanner.nextLine();
                            user.setPhoneNum(newPhone);
                            break;
                        case 6:
                            logger.info("Enter Your New Home Number");
                            String newHome = scanner.nextLine();
                            user.getAdress().setHomeNum(newHome);
                            break;

                        case 7:
                            logger.info("Enter Your New Street");
                            String newStreet = scanner.nextLine();
                            user.getAdress().setStreet(newStreet);
                            break;

                        default:
                            printInvalidMenuChoice(choice);
                    }
break;

                case "3":
                    return;
                default:
                    printInvalidMenuChoice(choice);
            }}

    }

    private void printInvalidMenuChoice(String choice) {
        if (logger.isLoggable(Level.WARNING)) {
            logger.warning("Invalid menu choice: " + choice);

        }

        logger.info("Invalid choice. Please select a valid option.");
    }

    private void giveAfeedBack() {
        while (true) {
            int choice = getChoiceForFeedbackType();

            switch (choice) {
                case 1:
                    handleProductFeedback();
                    break;
                case 2:
                    handleRecipeFeedback();
                    break;
                case 3:
                    return;
                default:
                    logger.warning("Invalid choice. Please select a valid option.");
            }
        }
    }

    private int getChoiceForFeedbackType() {
        logger.info("Which one you want to give feedback for:");
        logger.info("1. Product");
        logger.info("2. Posted Recipe");
        logger.info("3. Go Back ");
        return scanner.nextInt();
    }

    private void handleProductFeedback() {

      List<Order> orders = user.getOrders();
        Map<String, Product> productMap = buildProductMapFromOrders((LinkedList<Order>) orders);

        if (productMap.isEmpty()) {
            logger.info("You haven't ordered any products yet.");
            return;
        }

        logOrderedProducts(productMap);
    }

    private Map<String, Product> buildProductMapFromOrders(LinkedList<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getOrderDetails().stream())
                .map(OrderDetails::getProduct)
                .distinct()
                .collect(Collectors.toMap(Product::getId, Function.identity(), (existing, replacement) -> existing));
    }

    private void logOrderedProducts(Map<String, Product> productMap) {
        StringBuilder report = new StringBuilder("Here's the Product you have ordered:\n");
        report.append(String.format("%-15s %-20s %n", "Product Id", "Name"));

        for (Product product : productMap.values()) {
            report.append(String.format("%-15s %-20s %n", product.getId(), product.getName()));
        }
        if (logger.isLoggable(Level.INFO)) {
            logger.info(report.toString());

        }
    }

    private void handleRecipeFeedback() {
        recipeManager.showAllRecipes();
        logger.info("Enter the Recipe id you want give a feedback for ");
        int recipeId = scanner.nextInt();
        scanner.nextLine();
        logger.info("Enter the feedback Content ");
        String feedbackContent = scanner.nextLine();
        recipeManager.searchRecipeById(recipeId).addFeedback("\n by:" + user.getUserName() + feedbackContent);
    }




    private void messagingSys() {
        String menuOptions = ANSI_PURPLE + """
            ╔════════════════════════════════════╗
            ║        Message Management Menu     ║
            ╠════════════════════════════════════╣
            ║ 1. Send a Message                  ║
            ║ 2. View Inbox                      ║
            ║ 3. Go Back                         ║
            ╚════════════════════════════════════╝
            """ + ANSI_RESET + "\n" + CHOICE_PROMPT;
        logger.info(menuOptions);
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                logger.info("Enter receiver username: ");
                String receiver = scanner.nextLine();
                logger.info("Enter message content: ");
                String content = scanner.nextLine();
                LocalDate date = LocalDate.now();
                boolean success = messageManager.sendMessage(user.getUserName(), receiver, content, date);
                if (success) {
                    logger.info("Message sent successfully.");
                } else {
                    logger.info("Failed to send message. Invalid receiver.");
                }
                break;
            case "2":
                logger.info("Viewing inbox...");
                messageManager.viewInbox(user.getUserName());
                break;
            case "3":
                logger.info("Going back to the previous menu...");
                break;
            default:
                logger.info("Invalid choice. Please try again.");
        }



    }

    private void browseRecipes() {
        recipeManager.showAllRecipes();
    }

    private void postaRecipe() {
        logger.info("New Recipe :");
        logger.info("Enter Recipe Name");
        String recipeName = scanner.nextLine();
        logger.info("Enter Recipe Ingredients Number");
        int ingrediantsNumber = scanner.nextInt();
        scanner.nextLine();
        StringBuilder ingredients = new StringBuilder();
        logger.info("Enter Recipe Ingredients ");
        for (int i = 0; i < ingrediantsNumber; i++) {
            String ingredient=scanner.nextLine();
            ingredients.append(ingredient+" , ");
        }
        logger.info("Enter Number Recipe's Steps");
        int stepsNumber = scanner.nextInt();
        scanner.nextLine();

        StringBuilder steps = new StringBuilder();
        logger.info("Enter Recipe Steps ");
        for (int i = 0; i < stepsNumber; i++) {
            String step=scanner.nextLine();
            step+="\n";
            steps.append(step);
        }

        Recipe recipe= new Recipe(recipeName,ingrediantsNumber, ingredients.toString(),steps.toString(), user.getUserName());
        recipe.setId(recipeManager.getValidatedRecipes().size()+recipeManager.getnotValidatedRecipes().size());
        recipeManager.postRecipe(recipe);

    }


    private void purchaseDesserts() {
      logger.info("Our Shops :");
        List<Supplier> ourSuppliers =supplierManager.getSuppliers();
        for (Supplier supplier : ourSuppliers){
            logger.info(supplier.getShopName());
        }
        logger.info("Enter shop name ");
        String shopName = scanner.nextLine();

        Supplier supplier = supplierManager.getTheSupplierByUsingShopName(shopName);

        if(supplier!=null) {
            ProductManager prodManager = supplier.getProductManager();

            prodManager.showProductsForCustomer();

            LinkedList<OrderDetails> orderDetailsList = new LinkedList<>();
            while (true) {
                logger.info("Enter the product ID to add to your order or type 'done' to finish: ");
                String productId = scanner.nextLine();

                if (productId.equalsIgnoreCase("done")) {
                    break; // Exit the loop if the user is done adding products
                }

                Product product = prodManager.findProduct(productId);
                if (product != null) {
                    logger.info("Enter the quantity for " + product.getName() + ": ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    orderDetailsList.add(new OrderDetails(product, quantity));
                    if (logger.isLoggable(Level.INFO)) {
                        logger.info(quantity + " units of " + product.getName() + " added to your order.");

                    }
                } else {
                    logger.warning("Invalid product ID entered. Please try again.");
                }
            }
            if (!orderDetailsList.isEmpty()) {

                String orderId = user.getUserName() + (supplier.getOrders().size() + 1);
                Order newOrder = new Order(orderId, user.getUserName(), orderDetailsList);
                supplier.getOrderManager().addOrder(newOrder);
                if (logger.isLoggable(Level.INFO)) {
                    logger.info("Order placed successfully with Order ID: " + orderId);

                }
            } else {
                logger.warning("No products were added to the order.");
            }
        }
        else {
            logger.warning("enter valid shop name");
        }

    }



}
