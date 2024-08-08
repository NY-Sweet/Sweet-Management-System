package sweet.dev;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

public class SweetApp {

    private boolean isLogged;
    private LoginManager loginManager;
    private UserManager userManager;
    private SupplierManager supplierManager;
    private boolean InOwnerPage;
    private boolean OwnerInReprotsPage;
    private boolean InMessagePage;
    private boolean inOwnerTrackOrderPage;
    private boolean showProductPage;
    private boolean showAddProductPage;
    private boolean EditProductName;
    private boolean EditProductQuantity;
    private boolean EditProductExpirationDate;
    private boolean InProductPage;
    private boolean EditProductPrice;
    private boolean EditProductCost;
    private boolean DeleteProductOption;
    private boolean invalidOptionInProductPage;
    private boolean ExitProductPage;
    private boolean ViewDiscountProduct;
    private boolean setDiscountRuleOption;
    private boolean ownerOptionToReportsPage;
    public LinkedList<user> users;
    private LinkedList<supplier> suppliers;
    private MessageManager messageManager;
    private RecipeManager Recipemanager ;
    private boolean IsMessageDisplayedforVlidationaRecipe= false;
    private Admin admin = new Admin("Yara","123456789");
    public boolean isMessageDisplayedforVlidationaRecipe() {
        return IsMessageDisplayedforVlidationaRecipe;
    }


    public boolean isSetDiscountRuleOption() {
        return setDiscountRuleOption;
    }

    public boolean isExitProductPage() {
        return ExitProductPage;
    }

    public boolean isViewDiscountProduct() {
        return ViewDiscountProduct;
    }

    public boolean isEditProductExpirationDate() {
        return EditProductExpirationDate;
    }

    public void setInProductPage(boolean inProductPage) {
        InProductPage = inProductPage;
    }

    public boolean isDeleteProductOption() {
        return DeleteProductOption;
    }

    public void setShowProductPage(boolean showProductPage) {
        this.showProductPage = showProductPage;
    }

    public boolean isEditProductName() {
        return EditProductName;
    }

    public boolean isEditProductPrice() {
        return EditProductPrice;
    }

    public void setShowAddProductPage(boolean showAddProductPage) {
        this.showAddProductPage = showAddProductPage;
    }

    public void setInOwnerPage(boolean inOwnerPage) {
        InOwnerPage = inOwnerPage;
    }

    public boolean isInProductPage() {
        return InProductPage;
    }

    public void setOwnerInReprotsPage(boolean ownerInReprotsPage) {
        OwnerInReprotsPage = ownerInReprotsPage;
    }

    public SweetApp() {
        users = new LinkedList<>();
        suppliers = new LinkedList<>();
        user u1=new user("haya","123456","","","","","","u");
        users.add(u1);
        user u2=new user("sara","123456","","s12112422@stu.najah.edu","","","","u");
        users.add(u2);
       supplier s1=new supplier("noor","123456","","s12112422@stu.najah.edu","","","","s","",4);
       s1.getProductManager().addProduct("101","milk",100,10.5,8.0,28,7,2024,0.0);
       s1.getProductManager().addProduct("102", "bread", 50, 3.0, 2.0, 25, 7, 2024, 0.0);
        this.Recipemanager = new RecipeManager(users);
        LinkedList<OrderDetails> orderDetails = new LinkedList<>();
        product milk = s1.getProductManager().findProduct("101");
        product bread =s1.getProductManager().findProduct("102");
        orderDetails.add(new OrderDetails(milk, 5)); // 5 units of milk
        orderDetails.add(new OrderDetails(bread, 2)); // 2 units of bread
        Order order1 = new Order("order001", "sara", orderDetails);
        s1.getOrderManager().addOrder(order1);
       suppliers.add(s1);
       this.loginManager = new LoginManager(users, suppliers);
        this.userManager = new UserManager(users);
        this.supplierManager = new SupplierManager(suppliers);
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

    public void IamInOwnerPage(SweetApp obj) {
        obj.InOwnerPage=true;
    }

    public boolean isInOwnerPage() {
        return InOwnerPage;
    }

    public boolean isShowProductPage() {
        return showProductPage;
    }

    public boolean isShowAddProductPage() {
        return showAddProductPage;
    }

    public boolean isEditProductQuantity() {
        return EditProductQuantity;
    }

    public boolean isEditProductCost() {
        return EditProductCost;
    }

    public boolean isInvalidOptionInProductPage() {
        return invalidOptionInProductPage;
    }

    public boolean isOwnerOptionToReportsPage() {
        return ownerOptionToReportsPage;
    }

    public void ownerChooseToEnterProductPage(Integer int1) {
        if(int1==1)
            setInProductPage(true);
        else
            setInProductPage(false);
    }

    public void showProductPage(String string) {
        if(string.equals("0"))
            setShowProductPage(true);
        else
            setShowProductPage(false);
    }

    public void optionToAddProducts(String string2) {
        if(string2.equals("1"))
            setShowAddProductPage(true);
        else
            setShowAddProductPage(false);

    }

    public void EditProductName(String string) {
        if(string.equals("2a"))
        EditProductName=true;
        else
            EditProductName=false;

    }

    public void EditProductQuantity(String string) {
        if(string.equals("2b"))
            EditProductQuantity=true;
        else
            EditProductQuantity=false;


    }
    public void EditProductPrice(String string) {
        if(string.equals("2c"))
            EditProductPrice=true;
        else
            EditProductPrice=false;


    }

    public void EditProductCost(String option) {
        if(option.equals("2d"))
            EditProductCost=true;
        else
            EditProductCost=false;

    }
    public void EditProductExpirationDate(String option) {
        if(option.equals("2e"))
            EditProductExpirationDate=true;
        else
            EditProductExpirationDate=false;
    }
    public void DeleteProduct(String option) {
        if(option.equals("3"))
            DeleteProductOption=true;
        else
            DeleteProductOption=false;

    }


    public void invalidOptionInProductPage(String option) {
        String[] validOptions = {"0", "1", "2a", "2b", "2c", "2d", "2e", "3", "4a", "4b", "5"};
        invalidOptionInProductPage=false;
        for (String validOption : validOptions) {
            if (validOption.equals(option)) {
                invalidOptionInProductPage = false; // Option is valid
                break;
            }
        }
        invalidOptionInProductPage=true;
    }

    public void ExitProductPage(String option) {
        if("5".equals(option)) {
            ExitProductPage = true;
        }
        else
            ExitProductPage=false;
    }

    public void ViewDiscountProduct(String option) {
        if(option.equals("4a"))
            ViewDiscountProduct=true;
        else
            ViewDiscountProduct=false;

    }

    public void setDiscountRuleOption(String option) {
        if(option.equals("4b"))
            setDiscountRuleOption=true;
        else
            setDiscountRuleOption=false;
    }

    public void ownerOptionToReportsPage(int option) {
        if(option==2)
            ownerOptionToReportsPage=true;
        else
            ownerOptionToReportsPage=false;
    }

    public boolean invalidOptionInReportsPage(String option) {
        String[] validOptions = {"0", "1", "2","3","4"};

        for (String validOption : validOptions) {
            if (validOption.equals(option)) {
               return false;
            }
        }
        return true;
    }

    public boolean exitOwnerReportPage(String string) {
        if(string.equals("4"))
            return true;
            return false;

    }

    public boolean ownerViewDailySales(String string2) {
        if(string2.equals("1"))
            return true;
            return false;
    }

    public boolean ownerViewMonthlySales(String string2) {
        if(string2.equals("2"))
            return true;
        return false;
    }

    public boolean ownerViewBestSellingProducts(String string2) {
        if(string2.equals("3"))
            return true;
        return false;
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

}
