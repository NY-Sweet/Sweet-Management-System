package sweet.dev.models;
import main.format.PrettyFormatter;
import sweet.dev.managers.OrderManager;
import sweet.dev.managers.ProductManager;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.LinkedList;

public class Supplier {

    private  String userName;
    private  String password;
    private String phoneNum;
    private String email;
    private Adress adress;
    private String role;
    private String shopName;
    private int epmloyeeNum;
    private LinkedList<Product> products;
    private List<Order> orders;
    private boolean operationSuccess;
    private boolean wrongOldPass;
    private boolean mismatchPass;

    public boolean isMismatchPass() {
        return mismatchPass;
    }

    public boolean isWrongOldPass() {
        return wrongOldPass;
    }

    public List<Order> getOrders() {
        return orders;
    }

    private static final Logger logger = Logger.getLogger(Supplier.class.getName());
    private DiscountRule discountRule;
    private ProductManager productManager;
    private OrderManager orderManager;
    public Supplier(String userName, String password, String phoneNum, String email, Adress adress, String role, String shopName, int epmloyeeNum) {

        this.userName = userName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
       this.adress=adress;
        this.role = role;
        this.shopName = shopName;
        this.epmloyeeNum = epmloyeeNum;
        this.discountRule=new DiscountRule(30,3);
        products =new LinkedList<>();
        orders=new LinkedList<>();
        this.productManager = new ProductManager(this.products);
        this.orderManager=new OrderManager(this);

        logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.addHandler(consoleHandler);



    }


    public OrderManager getOrderManager() {
        return orderManager;
    }


    public ProductManager getProductManager() {
        return productManager;
    }
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public String getShopName() {
        return shopName;
    }

    public int getEpmloyeeNum() {
        return epmloyeeNum;
    }


    public void setPassword(String password) {
        this.password = password;
        operationSuccess=true;

    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        operationSuccess=true;

    }

    public void setEmail(String email) {
        this.email = email;
        operationSuccess=true;

    }



    public void setEpmloyeeNum(int epmloyeeNum) {
        this.epmloyeeNum = epmloyeeNum;
        operationSuccess=true;

    }




    public boolean isOperationSuccess() {
        return operationSuccess;
    }

    public Adress getAdress() {
        return adress;
    }

    public void updatePassword(String oldPassword, String newPassword, String confirmPassword) {
        if (oldPassword.equals(getPassword())) {
            wrongOldPass = false;
            if (newPassword.equals(confirmPassword)) {
                mismatchPass = false;
                setPassword(newPassword);
                operationSuccess = true;
            } else
                mismatchPass = true;

        } else
            wrongOldPass = true;
    }

    public boolean displaySupplierInfo() {
        String supplierInfo = """
            Supplier Information:
            Username: %s
            Phone Number: %s
            Email: %s
            City: %s
            Street: %s
            Home Number: %s
            Role: %s
            Shop Name: %s
            Employee Number: %d
            Discount rule Percentage: %.2f
            Discount rule days before Expiration: %d
            """.formatted(
                this.userName,
                this.phoneNum,
                this.email,
                this.adress.getCity(),
                this.adress.getStreet(),
                this.adress.getHomeNum(),
                this.role,
                this.shopName,
                this.epmloyeeNum,
                this.discountRule.getPercentage(),
                this.discountRule.getDaysBeforeExpiration()
        );

        logger.info(supplierInfo);
        return true;
    }


}

