package sweet.dev.managers;

import sweet.format.PrettyFormatter;
import sweet.dev.models.Admin;
import sweet.dev.models.supplier;
import sweet.dev.models.user;

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


    public AdminManager(List<Admin> admins, SupplierManager supplierManager, UserManager userManager) {
        this.admins = admins;
        this.supplierManager = supplierManager;
        this.userManager = userManager;

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
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
        List<supplier> suppliers=supplierManager.getSuppliers();
        for(supplier supplier:suppliers)
        {
            logger.info("Annual Report "+supplier.getShopName()+" manage by"+supplier.getUserName()+ " has employee number"+supplier.getEpmloyeeNum());
            supplier.getOrderManager().showFinancialReports(year);
        }
        return true;
    }
    public boolean showBestSellingProducts() {
        List<supplier> suppliers = supplierManager.getSuppliers();


        for (supplier supplier : suppliers) {
            logger.info("Best selling products in "+supplier.getShopName()+" manage by"+supplier.getUserName()+ " has employee number"+supplier.getEpmloyeeNum());
            supplier.getOrderManager().showBestProducts();
        }
        return true;
    }

    public boolean showStatisticsOnRegisteredUsersByCity() {
        List<user> users=userManager.getUsers();
        Map<String, Integer> cityUserCountMap = new HashMap<>();

        for (user user : users) {
            String city = user.getCity().toLowerCase();
            cityUserCountMap.put(city, cityUserCountMap.getOrDefault(city, 0) + 1);
        }

        printCityUserStatistics(cityUserCountMap);

        return true;
    }
    private void printCityUserStatistics(Map<String, Integer> cityUserCountMap) {
        if (cityUserCountMap.isEmpty()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("User Statistics by City%n");
        sb.append(String.format("%-20s %-10s%n", "City", "User Count"));
        sb.append("------------------------------%n");

        for (Map.Entry<String, Integer> entry : cityUserCountMap.entrySet()) {
            sb.append(String.format("%-20s %-10d%n", entry.getKey(), entry.getValue()));
        }

        logger.info(sb.toString());
    }
    }

