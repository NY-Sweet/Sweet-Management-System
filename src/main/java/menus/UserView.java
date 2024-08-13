package menus;
import sweet.dev.UserManager;
import sweet.dev.*;

import java.time.LocalDate;
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
    private  MessageManager messageManager ;

    public UserView(user User,UserManager userManager ,RecipeManager recipeManager ,SupplierManager supplierManager,MessageManager messageManager) {
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
                    findarecipe();
                    break;
                case "8":
                    logger.info("Exiting User menu");
                    return;
                default:
                    logger.warning("Invalid menu choice: " + choice);
                    logger.info("Invalid choice. Please select a valid option.");
            }
        }
    }
    private void findarecipe() {
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
                    logger.info("Enter the Alergry Your looking for");
                    String alergry = scanner.nextLine();
                    Set<String> Alergry = new HashSet<>();
                    Alergry.add(alergry);
                    recipeManager.filterRecipesByAllergies(recipeManager.getValidatedRecipes(),Alergry);
                    break;
                case "3":
                    return;
                default:
                    logger.warning("Invalid menu choice: " + choice);
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
                    logger.info("4. City:  "+User.getCity());
                    logger.info("5.Phone Number: "+ User.getPhoneNum());
                    logger.info("6.Home Number: " + User.getHomeNum());
                    logger.info("7.Street: " + User.getStreet());
                    break;
                case "2":
                    logger.info("Enter the Number of info you want to update ");
                    int number = scanner.nextInt();
                    scanner.nextLine();
                    switch (number) {
                        case 1:
                            logger.info("Enter Your New User Name");
                            String newUserName = scanner.nextLine();
                            User.userName=newUserName;
                            break;

                        case 2:
                            logger.info("Enter Your New Password");
                            String newPassword = scanner.nextLine();
                            User.setPassword(newPassword);
                            break;
                        case 3 :
                            logger.info("Enter Your New Email");
                            String newEmail = scanner.nextLine();
                            User.setEmail(newEmail);
                            break;
                        case 4 :
                            logger.info("Enter Your New City");
                            String newCity = scanner.nextLine();
                            User.setCity(newCity);
                            break;
                        case 5:
                            logger.info("Enter Your New Phone Number");
                            String newPhone = scanner.nextLine();
                            User.setPhoneNum(newPhone);
                            break;
                        case 6:
                            logger.info("Enter Your New Home Number");
                            String newHome = scanner.nextLine();
                            User.setHomeNum(newHome);
                            break;

                        case 7:
                            logger.info("Enter Your New Street");
                            String newStreet = scanner.nextLine();
                            User.setStreet(newStreet);
                            break;

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
                    feedbackContent+= "  by: "+User.getUserName();
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
                scanner.nextLine();
                logger.info("Enter the feedback Content ");
                String feedbackContent2 = scanner.nextLine();
                feedbackContent2+=" by:"+User.getUserName();
                recipeManager.searchRecipeById(RecipeID).addFeedback(feedbackContent2);
                break;
            case 3 :
                return;
        }}
    }

    private void Messaging() {
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
                boolean success = messageManager.sendMessage(User.getUserName(), receiver, content, date);
                if (success) {
                    logger.info("Message sent successfully.");
                } else {
                    logger.info("Failed to send message. Invalid receiver.");
                }
                break;
            case "2":
                logger.info("Viewing inbox...");
                messageManager.viewInbox(User.getUserName());
                break;
            case "3":
                logger.info("Going back to the previous menu...");
                break;
            default:
                logger.info("Invalid choice. Please try again.");
        }



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
        scanner.nextLine();
        StringBuilder ingredients = new StringBuilder();
        logger.info("Enter Recipe Ingredients ");
        for (int i = 0; i < ingrediantsNumber; i++) {
            String ingredient=scanner.nextLine();
            ingredients.append(ingredient+" /");
        }
        logger.info("Enter Number Recipe's Steps");
        int stepsNumber = scanner.nextInt();
        scanner.nextLine();

        StringBuilder steps = new StringBuilder();
        logger.info("Enter Recipe Steps ");
        for (int i = 0; i < stepsNumber; i++) {
            String step=scanner.nextLine();

            steps.append(step);
        }

        Recipe recipe= new Recipe(recipeName,ingrediantsNumber, ingredients.toString(),steps.toString(),User.getUserName());
        recipe.setId(recipeManager.getValidatedRecipes().size()+recipeManager.getnotValidatedRecipes().size());
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

        prodManager.showProductsForCustomer();
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
