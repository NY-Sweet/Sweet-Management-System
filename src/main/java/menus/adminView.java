package menus;

import sweet.dev.AdminManager;
import sweet.dev.SupplierManager;
import sweet.dev.UserManager;

import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class adminView {
    private SupplierManager supplierManager;
    private UserManager userManager;
    private AdminManager adminManager;
    private final Logger logger;
    private final Scanner scanner;

    private static final String ANSI_PURPLE = "\n\u001B[95m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE = "\u001B[37m";
    private static final String CHOICE_PROMPT = ANSI_WHITE + "Enter the number of your choice: " + ANSI_RESET;


    public adminView(SupplierManager supplierManager, UserManager userManager,AdminManager adminManager) {
        this.supplierManager=supplierManager;
        this.userManager=userManager;
        this.adminManager=adminManager;

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
                    ║ 5- Manage the content shared on the system,             ║
                    ║    including recipes and posts.                         ║
                    ║ 6- Manage user feedback.                                ║
                    ║                                                         ║
                    ║ 7- Exit                                                 ║
                    ╚═════════════════════════════════════════════════════════╝
                    """ + ANSI_RESET + "\n" + CHOICE_PROMPT;

            logger.info(menuOptions);
           String choice = scanner.nextLine();

            switch (choice) {
                case "1":
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

                default:
                    logger.info("Invalid choice. Please try again.");
            }
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
