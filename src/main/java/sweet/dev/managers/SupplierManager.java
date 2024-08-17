package sweet.dev.managers;

import sweet.dev.models.Supplier;

import java.util.List;
import java.util.logging.Logger;
public class SupplierManager {
    private boolean supplierCreated;
    private boolean validShopName;
    private List<Supplier> suppliers;
    private static final Logger logger = Logger.getLogger(SupplierManager.class.getName());

    public SupplierManager(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public boolean isSupplierCreated() {
        return supplierCreated;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public boolean createAccountForSupplier(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role, String shopName, int emplyeeNum) {
        supplierCreated = false;
        boolean flag = true;
        for (Supplier s : suppliers) {
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
        Supplier newSupplier = new Supplier(userName, password, phneNum, email, city, street, homeNum, role, shopName, emplyeeNum);
        suppliers.add(newSupplier);
    }

    public Supplier getTheSupplier(String userName){
        for (Supplier s : suppliers) {
            if (s.getUserName().equals(userName)) {
                return s;
            }
        }

        return null;
    }

    public boolean deleteSupplier (String supplierName){
        Supplier userToRemove = getTheSupplier(supplierName);
        if (userToRemove != null) {
            suppliers.remove(userToRemove);
            return true;
        }
        return false ;

    }
    public boolean displayallsuplliers (){
        for (Supplier s : suppliers) {
            logger.info(s.toString());
        }
        return true;
    }


    public boolean isValidShopName() {
        return validShopName;
    }

    public Supplier getTheSupplierByUsingShopName(String shopName) {
        for (Supplier s : suppliers) {
            if (s.getShopName().equals(shopName)) {
                validShopName=true;
                return s;
            }
        }
        validShopName=false;
        return null;

    }

}





