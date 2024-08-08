package sweet.dev;

import java.util.List;

public class SupplierManager {
    private boolean supplierCreated;
    private List<supplier> suppliers;

    public SupplierManager(List<supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public boolean isSupplierCreated() {
        return supplierCreated;
    }



    public void createAccountForSupplier(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role, String shopName, int emplyeeNum) {
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
}





