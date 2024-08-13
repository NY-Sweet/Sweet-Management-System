package menus;

import sweet.dev.*;

import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class adminView {
    private SupplierManager supplierManager;
    private UserManager userManager;
    private AdminManager adminManager;
    private final Logger logger;
    private final Scanner scanner;
    private RecipeManager recipeManager;

    private static final String ANSI_PURPLE = "\n\u001B[95m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String CHOICE_PROMPT = ANSI_WHITE + "Enter the number of your choice: " + ANSI_RESET;


    public adminView(SupplierManager supplierManager, UserManager userManager,AdminManager adminManager,RecipeManager recipeManager) {
        this.supplierManager=supplierManager;
        this.userManager=userManager;
        this.adminManager=adminManager;
        this.recipeManager=recipeManager;
        this.scanner = new Scanner(System.in);

        this.logger = Logger.getLogger(adminView.class.getName());

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
    }

    public void displayMenu() {

        while (true) {
            String menuOptions = ANSI_PURPLE + """
                    ╔═════════════════════════════════════════════════════════╗
                    ║                    Main Menu                            ║
                    ╠═════════════════════════════════════════════════════════╣
                    ║ 1- Manage user accounts including store owners          ║
                    ║    and raw material suppliers.                          ║
                    ║                                                         ║
                    ║ 2- Monitor profits and generate financial reports.      ║
                    ║ 3- Identify best-selling products in each store.        ║
                    ║ 4- Gather and display statistics on registered users    ║
                    ║    by City (Nablus/Jenin etc...).                       ║
                    ║                                                         ║
                    ║ 5- Manage Users FeedBacks                               ║
                    ║ 6-Manage the content shared on the system,              ║
                    ║    including recipes and posts.                         ║
                    ║ 7- Exit                                                 ║
                    ╚═════════════════════════════════════════════════════════╝
                    """ + ANSI_RESET + "\n" + CHOICE_PROMPT;

            logger.info(menuOptions);
           String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    ManageUsersAccounts();
                    break;
                case "2":
                    monitorAndReportFinancial();
                    break;
                case "3":
                    adminManager.showBestSellingProducts();
                    break;
                case "4":
                    adminManager.showStatisticsOnRegisteredUsersByCity();
                    break;
                case "5":
                    break;
                case "6":
                    ManageContent();
                    break;
                 case "7":
                     return;
                default:
                    logger.info("Invalid choice. Please try again.");
            }
        }
    }

    private void ManageUsersAccounts() {
        Scanner scanner = new Scanner(System.in); // Create scanner outside loop (resource management)

        while (true) {
            String menuOptions = ANSI_PURPLE + """
                ╔═════════════════════════════════════════════════════════╗
                ║                   App Management                        ║
                ╠═════════════════════════════════════════════════════════╣
                ║ 1. Users                                                ║
                ║ 2. Go Back                                              ║
                ╚═════════════════════════════════════════════════════════╝
                """ + ANSI_RESET;

            logger.info(menuOptions);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    manageUsers(); // Delegate user management logic to a separate method
                    break;
                case "2":
                    return; // Exit the loop
                default:
                    logger.warning("Invalid choice. Please enter 1 or 2.");
                    break;
            }
        }
    }

    private void manageUsers() {
        String menuOptions1 = ANSI_PURPLE + """
                ╔═════════════════════════════════════════════════════════╗
                ║              App Users Management                       ║
                ╠═════════════════════════════════════════════════════════╣
                ║ 1. Store Owners                                         ║
                ║ 2. Normal Users                                         ║
                ║ 3. Go Back                                              ║
                ╚═════════════════════════════════════════════════════════╝
                """ + ANSI_RESET;

        logger.info(menuOptions1);
        String choice1 = scanner.nextLine();

        switch (choice1) {
            case "1":
                manageStoreOwners();
                break;
            case "2":
                manageNormalUsers();
                break;
            case "3":
                break;
            default:
                logger.warning("Invalid choice. Please enter 1, 2, or 3.");
                break;
        }
    }

    private void manageStoreOwners() {
        List<supplier> suppliers = supplierManager.getSuppliers();

        // Display store owners with informative message
        if (suppliers.isEmpty()) {
            logger.info("There are currently no store owners.");
        } else {
            logger.info("List of Store Owners:");
            for (supplier supplier : suppliers) {
                logger.info(supplier.getUserName());
            }
        }

        // Prompt with clear options and handle user input correctly
        logger.info("Do you want to delete a store owner? (yes/no)");
        String choice2 = scanner.nextLine().toLowerCase();

        switch (choice2) {
            case "yes":
                logger.info("Enter the name of the store owner to delete:");
                String supplierToDelete = scanner.nextLine();
                if (supplierManager.deleteSupplier(supplierToDelete)) {
                    logger.config("Successfully deleted store owner: " + supplierToDelete);
                } else {
                    logger.warning("Store owner not found: " + supplierToDelete);
                }
                break;
            case "no":
                break;
            default:
                logger.warning("Invalid choice. Please enter yes or no.");
                break;
        }
    }

    private void manageNormalUsers() {
        List<user> users = userManager.getUsers();

        // Display normal users with informative message
        if (users.isEmpty()) {
            logger.info("There are currently no normal users.");
        } else {
            logger.info("List of Normal Users:");
            for (user user : users) {
                logger.info(user.getUserName());
            }
        }

        // Prompt with clear options and handle user input correctly
        logger.info("Do you want to delete a normal user? (yes/no)");
        String choice3 = scanner.nextLine().toLowerCase();

        switch (choice3) {
            case "yes":
                logger.info("Enter the name of the normal user to delete:");
                String userToDelete = scanner.nextLine();
                if (userManager.deleteUser(userToDelete)) {
                    logger.config("Successfully deleted normal user: " + userToDelete);
                } else {
                    logger.warning("Normal user not found: " + userToDelete);
                }
                break;
            case "no":
                break;
            default:
                logger.warning("Invalid choice. Please enter yes or no.");
                break;
        }
    }


    private void ManageContent() {
        String menuOptions = ANSI_PURPLE + """
        ╔═════════════════════════════════════════════════════════╗
        ║              App Content Management                     ║
        ╠═════════════════════════════════════════════════════════╣
        ║ 1.Dispaly Validated Recipies                            ║
        ║ 2.Manage Not Validated Recipes                          ║
        ║ 3.Manage Content FeedBacks                              ║
        ╚═════════════════════════════════════════════════════════╝
        """ + ANSI_RESET;

        logger.info(menuOptions);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                recipeManager.ShowAllRecipes();
                break;
            case "2":
                logger.info("Recipes Need Validation ");
                List<Recipe> recipestovalidate = recipeManager.getnotValidatedRecipes();
                for (Recipe recipe : recipestovalidate) {
                    logger.info(recipe.getId()+"      "+recipe.getName());
                }
                logger.info("Do you want to validate any of them ?? Yes/No ");
                String validationChoice  = scanner.nextLine();
                if (validationChoice.equalsIgnoreCase("Yes")) {
                    logger.info("Enter the recipe Id to validate it ");
                    int  recipeId = scanner.nextInt();
                    recipeManager.ValidateRecipe(recipeManager.searchRecipeByIdNotvalidated(recipeId));
                    logger.info("Its Done successfully");
                } else if (validationChoice.equalsIgnoreCase("No")) {
                logger.info("Recipe validation skipped.");

                }break;
                case "3":
                    logger.info(" Recipes and thier feedbacks :");
                    List<Recipe> showrecipes = recipeManager.getValidatedRecipes();
                    for (Recipe recipe : showrecipes) {
                        logger.info(recipe.getId()+"      "+recipe.getName());
                    }
                    logger.info("Do you want to check feedbacks on  a recipe ?? Yes/No ");
                    String feedbackChoice = scanner.nextLine().toLowerCase();
                    if (feedbackChoice.equalsIgnoreCase("yes")) {
                        logger.info("Enter the recipe Id to check feedbacks on  a recipe ");
                        int  recipeId = scanner.nextInt();
                         Recipe recipe = recipeManager.searchRecipeById(recipeId);
                         recipeManager.ValidateRecipe(recipeManager.searchRecipeByIdNotvalidated(recipeId));
                         List<String>Feeds=  recipe.getFeedbacks();
                         int I = 0;
                         for (String feed : Feeds) {
                             logger.info(I+" "+feed);
                         }
                         logger.info("Enter the Id of feedback that you want to delete");
                         int FeedId= scanner.nextInt();
                         recipeManager.searchRecipeById(recipeId).DeleteFeedback(Feeds.get(FeedId));
                        logger.info("Its Done successfully");
                        break;
                    }
                    break;
            default:
                logger.info("Invalid choice. Please try again.");


        }
    }

    private void monitorAndReportFinancial() {

        String menuOptions = ANSI_PURPLE + """
        ╔═════════════════════════════════════════════════════════╗
        ║              Financial Monitoring & Reporting           ║
        ╠═════════════════════════════════════════════════════════╣
        ║ Please enter the year for which you want to see the     ║
        ║ annual financial report:                                ║
        ╚═════════════════════════════════════════════════════════╝
        """ + ANSI_RESET;

        logger.info(menuOptions);

        String yearInput = scanner.nextLine();

        try {
            int year = Integer.parseInt(yearInput);

            boolean success = adminManager.showAnnualReport(year);

            if (success) {
                logger.info("Annual financial reports displayed successfully.");
            } else {
                logger.info("Failed to display annual financial reports.");
            }
        } catch (NumberFormatException e) {
            logger.warning("Invalid year entered. Please enter a valid year.");
        }

    }





}
