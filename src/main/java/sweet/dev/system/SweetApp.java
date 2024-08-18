package sweet.dev.system;

import sweet.menus.loginView;
import sweet.dev.managers.*;
import sweet.dev.models.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SweetApp {

    private boolean isLogged;
    private LoginManager loginManager;
    private UserManager userManager;
    private SupplierManager supplierManager;
    private boolean ownerInReprotsPage;
    private boolean inMessagePage;
    private boolean inProductPage;
    public LinkedList<User> users;
    private LinkedList<Admin> admins;
    private LinkedList<Supplier> suppliers;
    private MessageManager messageManager;
    private RecipeManager recipeManager;
    private boolean isMessageDisplayedforVlidationaRecipe = false;
    private Admin admin = new Admin("Yara","123456789");
    public boolean isMessageDisplayedforVlidationaRecipe() {
        return isMessageDisplayedforVlidationaRecipe;
    }


    private AdminManager adminManager;




    public void setInProductPage(boolean inProductPage) {
        this.inProductPage = inProductPage;
    }



    public void setOwnerInReprotsPage(boolean ownerInReprotsPage) {
        this.ownerInReprotsPage = ownerInReprotsPage;
    }

    public SweetApp() {
        users = new LinkedList<>();
        suppliers = new LinkedList<>();
        admins=new LinkedList<>();
        User u1=new User("haya","123456","","s12112422@stu.najah.edu",new Adress("nablus","",""),"u");
        users.add(u1);
        User u2=new User("sara","123456","","s12112422@stu.najah.edu",new Adress("nablus","",""),"u");
        users.add(u2);
       Supplier s1=new Supplier("noor","123456","","s12112422@stu.najah.edu",new Adress("nablus","",""),"s","sweetee",4);
       s1.getProductManager().addProduct(new Product("101","milk",100,10.5,8.0,new Date(28,7,2024),0.0));
       s1.getProductManager().addProduct(new Product("102", "bread", 50, 3.0, 2.0, new Date(25, 7, 2024), 0.0));
        this.recipeManager = new RecipeManager();
        LinkedList<OrderDetails> orderDetails = new LinkedList<>();
        Product milk = s1.getProductManager().findProduct("101");
        Product bread =s1.getProductManager().findProduct("102");
        milk.addFeedback("Good Milk ");
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

        this.recipeManager =new RecipeManager();
        Recipe Rec1;
        recipeManager.postRecipe(Rec1 = new Recipe("Pancake", 3, "Milk, flour, oil", "Mix them all", u1.getUserName()));
        Rec1.addFeedback("0-by: haya nice and easy");

        Rec1.setId(1);
        Recipe Rec2;
        recipeManager.postRecipe(Rec2 = new Recipe("Chocolate Cake", 4, "Flour, sugar, cocoa powder, eggs, butter", "Bake it at 350°F for 30 minutes", u2.getUserName()));
        recipeManager.validateRecipe(Rec2);
        Rec2.addFeedback("0-by: sara delicious and rich");
        Rec2.setId(2);

        Recipe Rec3;
        recipeManager.postRecipe(Rec3 = new Recipe("Vegetable Soup", 5, "Carrots, potatoes, celery, onions, water, salt", "Simmer all ingredients for 40 minutes", u1.getUserName()));
        recipeManager.validateRecipe(Rec3);
        Rec3.addFeedback("0-by: haya healthy and comforting");
        Rec3.setId(3);

        Recipe Rec4;
        recipeManager.postRecipe(Rec4 = new Recipe("Spaghetti Bolognese", 4, "Spaghetti, ground beef, tomato sauce, onions, garlic", "Cook spaghetti and sauce separately and then mix", u1.getUserName()));
        recipeManager.validateRecipe(Rec4);
        Rec4.addFeedback("0-by: sara classic and tasty");
        Rec4.setId(0);

        Recipe Rec5;
        recipeManager.postRecipe(Rec5 = new Recipe("Caesar Salad", 2, "Lettuce, croutons, Parmesan cheese, Caesar dressing", "Toss all ingredients together", u2.getUserName()));
        recipeManager.validateRecipe(Rec5);
        Rec5.addFeedback("0-by: haya fresh and crisp");
        Rec5.setId(4);

        Recipe Rec6;
        recipeManager.postRecipe(Rec6 = new Recipe("Lemonade", 1, "Lemons, sugar, water", "Mix lemon juice, sugar, and water", u1.getUserName()));
        recipeManager.validateRecipe(Rec6);
        Rec6.addFeedback("0-by: sara refreshing and sweet");
        Rec6.setId(5);

        Recipe Rec7;
        recipeManager.postRecipe(Rec7 = new Recipe("French Toast", 2, "Bread, eggs, milk, cinnamon, butter", "Dip bread in egg mixture and cook in butter", u1.getUserName()));
        recipeManager.validateRecipe(Rec7);
        Rec7.addFeedback("0-by: haya easy and delicious");
        Rec7.setId(6);

        Recipe Rec8;
        recipeManager.postRecipe(Rec8 = new Recipe("Chicken Curry", 4, "Chicken, curry powder, coconut milk, onions, garlic", "Cook chicken with spices and add coconut milk", u2.getUserName()));
        recipeManager.validateRecipe(Rec8);
        Rec8.addFeedback("0-by: sara spicy and flavorful");
        Rec8.setId(7);

        Recipe Rec9;
        recipeManager.postRecipe(Rec9 = new Recipe("Fruit Salad", 2, "Apples, bananas, grapes, oranges, honey", "Mix all fruits and drizzle with honey", u2.getUserName()));
        recipeManager.validateRecipe(Rec9);
        Rec9.addFeedback("0-by: haya sweet and tangy");
        Rec9.setId(8);

        Recipe Rec10;
        recipeManager.postRecipe(Rec10 = new Recipe("Grilled Cheese Sandwich", 1, "Bread, cheese, butter", "Butter bread, add cheese, and grill", u1.getUserName()));
        recipeManager.validateRecipe(Rec10);
        Rec10.addFeedback("0-by: sara gooey and cheesy");
        Rec10.setId(9);



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

    public boolean isInMessagePage() {
        return inMessagePage;
    }

    public void InMessagePage(boolean b) {
        this.inMessagePage =b;
    }
    public RecipeManager getRecipeManager(){
        return recipeManager;
    }

    public boolean RecipoeTosearch (String recipe){
        List<Recipe> SearchedRecipes = recipeManager.findRecipesByName(recipe);
        return true ;

    }
    public void Messageaftervaledationoftherecipe (){
        System.out.println("The Selected Recipe Has been Validated Successfulley ");
        isMessageDisplayedforVlidationaRecipe = true;
    }
    public void clearMessageDisplayedAfterRecipeValidation (){
        isMessageDisplayedforVlidationaRecipe = false;
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
        for (Supplier Sup : suppliers) {
            if (Sup.getProductManager().findProduct(ID)!= null) {
                Sup.getProductManager().findProduct(ID).addFeedback(FeedBack);
                return true;
            }
        }
        return false;
    }





    public AdminManager getAdminManager() {
        return adminManager;
    }

    public void run() {
        loginView loginView = new loginView( loginManager,userManager, supplierManager,messageManager,adminManager, recipeManager);
        loginView.displayMenu();
    }

}
