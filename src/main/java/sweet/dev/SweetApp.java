package sweet.dev;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

public class SweetApp {

    private boolean isLogged;
    private LoginManager loginManager;
    private boolean userInPurchasePage;
    private UserManager userManager;
    private SupplierManager supplierManager;

    private boolean OwnerInReprotsPage;
    private boolean InMessagePage;
    private boolean inOwnerTrackOrderPage;

    private boolean InProductPage;

    private LinkedList<user> users;

    private LinkedList<Admin> admins;
    private LinkedList<supplier> suppliers;
    private MessageManager messageManager;

    private AdminManager adminManager;




    public void setInProductPage(boolean inProductPage) {
        InProductPage = inProductPage;
    }



    public void setOwnerInReprotsPage(boolean ownerInReprotsPage) {
        OwnerInReprotsPage = ownerInReprotsPage;
    }

    public SweetApp() {
        users = new LinkedList<>();
        suppliers = new LinkedList<>();
        admins=new LinkedList<>();
        user u1=new user("haya","123456","","","nablus","","","u");
        users.add(u1);
        user u2=new user("sara","123456","","s12112422@stu.najah.edu","nablus","","","u");
        users.add(u2);
       supplier s1=new supplier("noor","123456","","s12112422@stu.najah.edu","","","","s","sweetee",4);
       s1.getProductManager().addProduct("101","milk",100,10.5,8.0,28,7,2024,0.0);
       s1.getProductManager().addProduct("102", "bread", 50, 3.0, 2.0, 25, 7, 2024, 0.0);

        LinkedList<OrderDetails> orderDetails = new LinkedList<>();
        product milk = s1.getProductManager().findProduct("101");
        product bread =s1.getProductManager().findProduct("102");
        orderDetails.add(new OrderDetails(milk, 5)); // 5 units of milk
        orderDetails.add(new OrderDetails(bread, 2)); // 2 units of bread
        Order order1 = new Order("order001", "sara", orderDetails);
        s1.getOrderManager().addOrder(order1);
       suppliers.add(s1);

       admins.add(new Admin("admin","123456"));

       this.loginManager = new LoginManager(users, suppliers);
        this.userManager = new UserManager(users);
        this.supplierManager = new SupplierManager(suppliers);

        this.adminManager=new AdminManager(admins,supplierManager,userManager);

        this.messageManager=new MessageManager(userManager,supplierManager);
        messageManager.sendMessage("noor","haya","hello haya", LocalDate.now());
    }



    public MessageManager getMessageManager() {
        return messageManager;
    }

    public void iAmNotInSystem(SweetApp obj) {
        obj.setLogged(false);
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public SupplierManager getSupplierManager() {
        return supplierManager;
    }








    public void inOwnerTrackOrderPage(boolean inOwnerTrackOrderPage ) {
        this.inOwnerTrackOrderPage=inOwnerTrackOrderPage;
    }

    public boolean isInMessagePage() {
        return InMessagePage;
    }

    public void InMessagePage(boolean b) {
        this.InMessagePage=b;
    }

    public void userInPurchasePage(boolean b) {
        userInPurchasePage=true;

    }

    public AdminManager getAdminManager() {
        return adminManager;
    }
}
