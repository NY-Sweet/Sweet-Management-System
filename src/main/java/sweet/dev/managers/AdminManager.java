package sweet.dev.managers;

import sweet.dev.models.Supplier;
import sweet.dev.models.User;
import main.format.PrettyFormatter;
import sweet.dev.models.Admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class AdminManager {

    private  List<Admin> admins;
    private SupplierManager supplierManager;
    private UserManager userManager;


    private static final Logger logger = Logger.getLogger(AdminManager.class.getName());

    static { logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.addHandler(consoleHandler); }
    public AdminManager(List<Admin> admins, SupplierManager supplierManager, UserManager userManager) {
        this.admins = admins;
        this.supplierManager = supplierManager;
        this.userManager = userManager;


    }

    public Admin getTheAdmin(String string) {
        for(Admin admin:admins)
        {
            if(admin.getAdminName().equals(string))
            {
                return admin;
            }
        }
        return null;
    }
    public boolean showAnnualReport(int year)
    {
        List<Supplier> suppliers =supplierManager.getSuppliers();
        for(Supplier supplier:suppliers)
        {
            logger.info("Annual Report "+supplier.getShopName()+" manage by "+supplier.getUserName()+ " has employee number "+supplier.getEpmloyeeNum());
            supplier.getOrderManager().showFinancialReports(year);
        }
        return true;
    }
    public boolean showBestSellingProducts() {
        List<Supplier> suppliers = supplierManager.getSuppliers();


        for (Supplier supplier : suppliers) {
            logger.info("Best selling products in "+supplier.getShopName()+" manage by "+supplier.getUserName()+ " has employee number "+supplier.getEpmloyeeNum());
            supplier.getOrderManager().showBestProducts();
        }
        return true;
    }

    public boolean showStatisticsOnRegisteredUsersByCity() {
        List<User> users =userManager.getUsers();
        Map<String, Integer> cityUserCountMap = new HashMap<>();

        for (User user : users) {
            String city = user.getAdress().getCity().toLowerCase();
            cityUserCountMap.put(city, cityUserCountMap.getOrDefault(city, 0) + 1);
        }

        printCityUserStatistics(cityUserCountMap);

        return true;
    }
    private void printCityUserStatistics(Map<String, Integer> cityUserCountMap) {
        if (cityUserCountMap.isEmpty()) {
            return;
        }

        logger.info(() -> {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("User Statistics by City %n%n"));
            sb.append(String.format("%-20s %-10s%n", "City", "User Count"));
            sb.append(String.format("%n------------------------------%n%n"));

            for (Map.Entry<String, Integer> entry : cityUserCountMap.entrySet()) {
                sb.append(String.format("%-20s %-10d%n", entry.getKey(), entry.getValue()));
            }

            return sb.toString();
        });
    }
    }

