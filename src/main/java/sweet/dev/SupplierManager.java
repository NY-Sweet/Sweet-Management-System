package sweet.dev;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SupplierManager {
    private boolean supplierCreated;
    private boolean validShopName;
    private List<supplier> suppliers;
    private static final Logger logger = Logger.getLogger(SupplierManager.class.getName());

    public SupplierManager(List<supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public boolean isSupplierCreated() {
        return supplierCreated;
    }

    public List<supplier> getSuppliers() {
        return suppliers;
    }

    public boolean createAccountForSupplier(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role, String shopName, int emplyeeNum) {
        supplierCreated = false;
        boolean flag = true;
        for (supplier s : suppliers) {
            if (s.getUserName().equals(userName)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            addSupplier(userName, password, phneNum, email, city, street, homeNum, role, shopName, emplyeeNum);
            supplierCreated = true;
        }
        return flag;

    }

    public void addSupplier(String userName, String password, String phneNum, String email, String city, String street, String homeNum, String role, String shopName, int emplyeeNum) {
        supplier newSupplier = new supplier(userName, password, phneNum, email, city, street, homeNum, role, shopName, emplyeeNum);
        suppliers.add(newSupplier);
    }

    public supplier getTheSupplier(String userName){
        for (supplier s : suppliers) {
            if (s.getUserName().equals(userName)) {
                return s;
            }
        }

        return null;
    }
<<<<<<< HEAD
    public boolean deleteSupplier (String supplierName){
        supplier userToRemove = getTheSupplier(supplierName);
        if (userToRemove != null) {
            suppliers.remove(userToRemove);
            return true;
        }
        return false ;

    }
    public boolean displayallsuplliers (){
        for (supplier s : suppliers) {
            logger.info(s.toString());
        }
        return true;
    }
=======

    public boolean isValidShopName() {
        return validShopName;
    }

    public supplier getTheSupplierByUsingShopName(String shopName) {
        for (supplier s : suppliers) {
            if (s.getShopName().equals(shopName)) {
                validShopName=true;
                return s;
            }
        }
        validShopName=false;
        return null;

    }
>>>>>>> c8a7d80c0c32f2ba785796d45731ea4ece821b63
}





