package main.menus;

import sweet.dev.models.Supplier;
import sweet.dev.models.User;
import main.format.PrettyFormatter;
import sweet.dev.managers.AdminManager;
import sweet.dev.managers.RecipeManager;
import sweet.dev.managers.SupplierManager;
import sweet.dev.managers.UserManager;
import sweet.dev.models.Recipe;

import java.util.List;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminView {
    private SupplierManager supplierManager;
    private UserManager userManager;
    private AdminManager adminManager;
    private static final Logger logger =Logger.getLogger(AdminView.class.getName());
    private final Scanner scanner;
    private RecipeManager recipeManager;

    private static final String ANSI_PURPLE = "\n\u001B[95m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String CHOICE_PROMPT = ANSI_WHITE + "Enter the number of your choice: " + ANSI_RESET;

    static { logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.addHandler(consoleHandler); }

    public AdminView(SupplierManager supplierManager, UserManager userManager, AdminManager adminManager, RecipeManager recipeManager) {
        this.supplierManager=supplierManager;
        this.userManager=userManager;
        this.adminManager=adminManager;
        this.recipeManager=recipeManager;
        this.scanner = new Scanner(System.in);





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
                    ║ 3- Identify best-selling Products in each store.        ║
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
                    manageUsersAccounts();
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
                    manageFeeds();
                    break;
                case "6":
                    manageContent();
                    break;
                 case "7":
                     return;
                default:
                    logger.info("Invalid choice. Please try again.");
            }
        }
    }

    private void manageFeeds() {
        logger.info("Here's the recipes in our system and their feedbacks");
        recipeManager.showAllRecipes();

        while (true) {
            int recipeId = promptForRecipeId();
            Recipe recipe = recipeManager.searchRecipeById(recipeId);

            if (recipe != null) {
                showFeedbacks(recipe);
                int feedbackId = promptForFeedbackId();
                handleFeedbackDeletion(recipeId, feedbackId);
                break;
            } else {
                logInvalidRecipeId();
            }
        }
    }
    private int promptForRecipeId() {
        logger.info("Enter the recipe Id you want to modify its feedbacks");
        return Integer.parseInt(scanner.nextLine());
    }
    private void showFeedbacks(Recipe recipe) {
        List<String> feeds = recipe.getFeedbacks();
        for (int i = 0; i < feeds.size(); i++) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(String.format("%d.  %s", i, feeds.get(i)));
            }
        }
    }

    private int promptForFeedbackId() {
        logger.info("Enter the Feedback Id you want to delete");
        return Integer.parseInt(scanner.nextLine());
    }

    private void handleFeedbackDeletion(int recipeId, int feedbackId) {
        if (recipeManager.deleteaFeedofaRecipe(recipeId, feedbackId)) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Deleted the Feedback Id " + feedbackId);
            }
        } else {
            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Feedback Id not valid " + feedbackId);
            }
        }
    }

    private void logInvalidRecipeId() {
        logger.warning("Recipe id not found, enter a valid one!!");
    }

    private void manageUsersAccounts() {
        Scanner scanner0 = new Scanner(System.in); // Create scanner0 outside loop (resource management)

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
            String choice = scanner0.nextLine();

            switch (choice) {
                case "1":
                    manageUsers();
                    break;
                case "2":
                    return;
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
        List<Supplier> suppliers = supplierManager.getSuppliers();

        if (suppliers.isEmpty()) {
            logger.info("There are currently no store owners.");
        } else {
            logger.info("List of Store Owners:");
            for (Supplier supplier : suppliers) {
                logger.info(supplier.getUserName());
            }
        }

        logger.info("Do you want to delete a store owner? (yes/no)");
        String choice2 = scanner.nextLine().toLowerCase();

        switch (choice2) {
            case "yes":
                logger.info("Enter the name of the store owner to delete:");
                String supplierToDelete = scanner.nextLine();
                if (supplierManager.deleteSupplier(supplierToDelete)) {
                    if (logger.isLoggable(Level.CONFIG)) {
                        logger.config("Successfully deleted store owner: " + supplierToDelete);

                    }
                } else {
                    if (logger.isLoggable(Level.WARNING)) {
                        logger.warning("Store owner not found: " + supplierToDelete);
                    }
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
        List<User> users = userManager.getUsers();

        // Display normal users with informative message
        if (users.isEmpty()) {
            logger.info("There are currently no normal users.");
        } else {
            logger.info("List of Normal Users:");
            for (User user : users) {
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
                    if (logger.isLoggable(Level.CONFIG)) {
                        logger.config("Successfully deleted normal user: " + userToDelete);
                    }
                } else {
                    if (logger.isLoggable(Level.WARNING)) {
                        logger.warning("Normal user not found: " + userToDelete);
                    }
                }
                break;
            case "no":
                break;
            default:
                logger.warning("Invalid choice. Please enter yes or no.");
                break;
        }
    }


    private void manageContent() {
        String menuOptions = ANSI_PURPLE + """
        ╔═════════════════════════════════════════════════════════╗
        ║              App Content Management                     ║
        ╠═════════════════════════════════════════════════════════╣
        ║ 1.Display Validated Recipes                             ║
        ║ 2.Manage Not Validated Recipes                          ║
        ╚═════════════════════════════════════════════════════════╝
        """ + ANSI_RESET;

        logger.info(menuOptions);
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                recipeManager.showAllRecipes();
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
                    scanner.nextLine();

                    recipeManager.validateRecipe(recipeManager.searchRecipeByIdNotvalidated(recipeId));
                    logger.info("Its Done successfully");
                } else if (validationChoice.equalsIgnoreCase("No")) {
                logger.info("Recipe validation skipped.");

                }break;

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
