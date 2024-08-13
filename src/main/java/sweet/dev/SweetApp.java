package sweet.dev;

import menus.loginView;

import java.time.LocalDate;
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

    public LinkedList<user> users;
    private LinkedList<Admin> admins;
    private LinkedList<supplier> suppliers;
    private MessageManager messageManager;
    private RecipeManager Recipemanager ;
    private boolean IsMessageDisplayedforVlidationaRecipe= false;
    private Admin admin = new Admin("Yara","123456789");
    public boolean isMessageDisplayedforVlidationaRecipe() {
        return IsMessageDisplayedforVlidationaRecipe;
    }


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

       this.Recipemanager = new RecipeManager(users);
        LinkedList<OrderDetails> orderDetails = new LinkedList<>();
        product milk = s1.getProductManager().findProduct("101");
        product bread =s1.getProductManager().findProduct("102");
        orderDetails.add(new OrderDetails(milk, 5)); // 5 units of milk
        orderDetails.add(new OrderDetails(bread, 2)); // 2 units of bread
        Order order1 = new Order("order001", "sara", orderDetails);
        Order order2 = new Order("order002", "haya", orderDetails);
        Order order3 = new Order("order003", "haya", orderDetails);

        s1.getOrderManager().addOrder(order1);
        s1.getOrderManager().addOrder(order2);
        s1.getOrderManager().addOrder(order3);
       suppliers.add(s1);

       admins.add(new Admin("admin","123456"));

       this.loginManager = new LoginManager(users, suppliers,admins);
        this.userManager = new UserManager(users);
        this.supplierManager = new SupplierManager(suppliers);

        this.adminManager=new AdminManager(admins,supplierManager,userManager);

        this.messageManager=new MessageManager(userManager,supplierManager);
        messageManager.sendMessage("noor","haya","hello haya", LocalDate.now());
        s1.getOrderManager().updateOrderStatus("order002","shipped",userManager);
        s1.getOrderManager().updateOrderStatus("order003","delivered",userManager);

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
    public RecipeManager getRecipemanager(){
        return Recipemanager;
    }

    public boolean RecipoeTosearch (String recipe){
        LinkedList <Recipe>SearchedRecipes =Recipemanager.searchRecipes(recipe);
        return true ;

    }
    public void Messageaftervaledationoftherecipe (){
        System.out.println("The Selected Recipe Has been Validated Successfulley ");
        IsMessageDisplayedforVlidationaRecipe= true;
    }
    public void clearMessageDisplayedAfterRecipeValidation (){
        IsMessageDisplayedforVlidationaRecipe= false;
    }

    public boolean  addStoreOwner(String userName, String password, String phoneNum, String email,
                              String city, String street, String homeNum, String shopName,
                              int employeeNum) {
        if (admin.AdminPermession()){
            this.supplierManager.createAccountForSupplier(userName, password, phoneNum, email, city,
                    street, homeNum, "s", shopName, employeeNum);
        }
        return false;
    }
    public boolean DeleteAccount (String userName) {

       if (null !=this.supplierManager.getTheSupplier(userName)) {
           supplierManager.deleteSupplier(userName);
           return true;
       } else if (null != this.userManager.getTheUser(userName)) {
           userManager.deleteUser(userName);
           return true;
       }
       return false;
    }
    public boolean addFeedbackforaProductByitsId (String ID , String FeedBack){
        for (supplier Sup : suppliers) {
            if (Sup.getProductManager().findProduct(ID)!= null) {
                Sup.getProductManager().findProduct(ID).addFeedback(FeedBack);
                return true;
            }
        }
        return false;
    }



    public void userInPurchasePage(boolean b) {
        userInPurchasePage=true;

    }

    public AdminManager getAdminManager() {
        return adminManager;
    }

    public void run() {
        loginView loginView = new loginView( loginManager,userManager, supplierManager,messageManager,adminManager,getRecipemanager());
        loginView.displayMenu();
    }

}
