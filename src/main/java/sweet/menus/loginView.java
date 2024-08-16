package sweet.menus;

import sweet.format.PrettyFormatter;
import sweet.dev.managers.*;
import sweet.dev.models.Admin;
import sweet.dev.models.supplier;
import sweet.dev.models.user;

import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class loginView {
    private static final String ANSI_PURPLE = "\n\u001B[95m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String CHOICE_PROMPT = ANSI_WHITE + "Enter the number of your choice: " + ANSI_RESET;
    private RecipeManager recipeManager;
    private final LoginManager loginManager;
    private final UserManager userManager;
    private final MessageManager messageManager;
    private final SupplierManager supplierManager;
    private final Scanner scanner;
    private final Logger logger;
    private AdminManager adminManager;


    public loginView(LoginManager loginManager, UserManager userManager, SupplierManager supplierManager,MessageManager messageManager,AdminManager adminManager,RecipeManager recipeManager) {
        this.loginManager = loginManager;
        this.userManager = userManager;
        this.supplierManager = supplierManager;
        this.adminManager=adminManager;
        this.scanner = new Scanner(System.in);
        this.logger = Logger.getLogger(loginView.class.getName());
        this.recipeManager = recipeManager;
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
        this.messageManager=messageManager;
    }

    public void displayMenu() {
        while (true) {
            String menuOptions = ANSI_PURPLE + """
                ╔════════════════════════════╗
                ║          Welcome!          ║
                ╠════════════════════════════╣
                ║ 1. Login                   ║
                ║ 2. Signup                  ║
                ║ 3. Forget password         ║
                ║ 4. Log out                 ║
                ╚════════════════════════════╝
                """ + ANSI_RESET + "\n" + CHOICE_PROMPT;
            logger.info(menuOptions);
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    signup();
                    break;
                case "3":
                    updatePassword();
                    break;
                case "4":
                    logger.info("Exiting application");
                    return;
                default:
                    logger.warning("Invalid menu choice: " + choice);
                    logger.info(ANSI_WHITE + "Invalid choice. Please select a valid option." + ANSI_RESET);
            }
        }
    }

    private void login() {
        logger.info("====== Login ======");
        String username = promptForNonEmptyInput("Enter username: ");
        String password = promptForNonEmptyInput("Enter password: ");

        loginManager.setUsernameAndPasswordFromSystem(username, password);



        if (loginManager.isValidation()) {
            if(loginManager.getRoleInSys()==1)
            {
                supplier supplier=supplierManager.getTheSupplier(loginManager.getEnteredUsername());
                ownerView ownerView=new ownerView(supplier,userManager,messageManager);
                ownerView.displayMenu();
            }

            else if(loginManager.getRoleInSys()==2)
            {

                Admin admin=adminManager.getTheAdmin(loginManager.getEnteredUsername());
                adminView adminView=new adminView(supplierManager,userManager,adminManager,recipeManager);
                adminView.displayMenu();

            }

            else if(loginManager.getRoleInSys()==0)
            {

                user user=userManager.getTheUser(loginManager.getEnteredUsername());
                UserView userView=new UserView(user,userManager,recipeManager,supplierManager,messageManager);
                userView.displayMenu();

            }

            handleSuccessfulLogin();
        } else {
            logger.warning("Login failed for username: " + username);
            logger.info(ANSI_WHITE + "Invalid username or password. Please try again." + ANSI_RESET);
        }
    }

    private void handleSuccessfulLogin() {
        int role = loginManager.getRoleInSys();
        switch (role) {
            case 0:
                logger.info("User logged in successfully: " + loginManager.getEnteredUsername());
                logger.info(ANSI_WHITE + "User logged in successfully!" + ANSI_RESET);
                break;
            case 1:
                logger.info("Supplier logged in successfully: " + loginManager.getEnteredUsername());
                logger.info(ANSI_WHITE + "Supplier logged in successfully!" + ANSI_RESET);
                break;
            case 2:
                logger.info("Admin logged in successfully: " + loginManager.getEnteredUsername());
                logger.info(ANSI_WHITE + "Admin logged in successfully!" + ANSI_RESET);
                break;
            default:
                logger.severe("Unknown role for username: " + loginManager.getEnteredUsername());
                logger.info(ANSI_WHITE + "Unknown role. Please contact support." + ANSI_RESET);
        }
    }

    private void signup() {
        logger.info("====== Signup ======");
        logger.info(ANSI_PURPLE + "Signup as:" + ANSI_RESET);
        logger.info(ANSI_PURPLE + "1. User" + ANSI_RESET);
        logger.info(ANSI_PURPLE + "2. Supplier" + ANSI_RESET);
        logger.info(CHOICE_PROMPT);

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                handleUserSignup();
                break;
            case "2":
                handleSupplierSignup();
                break;
            default:
                logger.warning("Invalid signup choice: " + choice);
                logger.info(ANSI_WHITE + "Invalid choice. Returning to main menu." + ANSI_RESET);
        }
    }

    private void handleUserSignup() {
        logger.info("=== User Signup ===");
        String username = promptForNonEmptyInput("Enter username: ");
        String password = promptForNonEmptyInput("Enter password: ");
        String phoneNumber = promptForNonEmptyInput("Enter phone number: ");
        String email = promptForNonEmptyInput("Enter email: ");
        String city = promptForNonEmptyInput("Enter city: ");
        String street = promptForNonEmptyInput("Enter street: ");
        String homeNumber = promptForNonEmptyInput("Enter home number: ");
        userManager.addUser(username, password, phoneNumber, email, city, street, homeNumber, "u");
        logger.info("User registered successfully: " + username);
        login();

    }

    private void handleSupplierSignup() {
        logger.info("=== Supplier Signup ===");
        String username = promptForNonEmptyInput("Enter username: ");
        String password = promptForNonEmptyInput("Enter password: ");
        String phoneNumber = promptForNonEmptyInput("Enter phone number: ");
        String email = promptForNonEmptyInput("Enter email: ");
        String city = promptForNonEmptyInput("Enter city: ");
        String street = promptForNonEmptyInput("Enter street: ");
        String homeNumber = promptForNonEmptyInput("Enter home number: ");
        String shopName = promptForNonEmptyInput("Enter shop name: ");
        int employeeNum = promptForIntegerInput("Enter employee number: ");

        supplierManager.addSupplier(username, password, phoneNumber, email, city, street, homeNumber, "s", shopName, employeeNum);
        logger.info("Supplier registered successfully: " + username);
        login();
    }

    private void updatePassword() {
        logger.info("====== Update Password ======");
        String username = promptForNonEmptyInput("Enter username: ");
        String newPassword = promptForNonEmptyInput("Enter new password: ");
        loginManager.validUsernameAndForgetPassword(username,"Forget");
        if (loginManager.isForget()) {
            loginManager.updatePassword(newPassword);
            logger.info("Password updated successfully for username: " + username);
        } else {
            logger.warning("Password update failed for username: " + username);
            logger.info(ANSI_WHITE + "Failed to update password. User may not be valid or not in forget mode." + ANSI_RESET);

        }
    }

    private String promptForNonEmptyInput(String prompt) {
        String input = "";
        while (input.isEmpty()) {
            logger.info(ANSI_WHITE + prompt + ANSI_RESET);
            input = scanner.nextLine();
            if (input.isEmpty()) {
                logger.warning("Input field was empty.");
                logger.info(ANSI_WHITE + "This field cannot be empty. Please enter a value." + ANSI_RESET);
            }
        }
        return input;
    }

    private int promptForIntegerInput(String prompt) {
        int input = -1;
        while (input < 0) {
            logger.info(ANSI_WHITE + prompt + ANSI_RESET);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input < 0) {
                    logger.warning("Entered negative integer.");
                    logger.info(ANSI_WHITE + "Please enter a positive integer." + ANSI_RESET);
                }
            } catch (NumberFormatException e) {
                logger.warning("Invalid number format.");
                logger.info(ANSI_WHITE + "Invalid input. Please enter a number." + ANSI_RESET);
            }
        }
        return input;
    }
}
