package sweet.dev.system;

import main.format.PrettyFormatter;
import main.menus.LoginView;
import sweet.dev.managers.*;
import sweet.dev.models.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class SweetApp {

    private LoginManager loginManager;
    private UserManager userManager;
    private SupplierManager supplierManager;

    private boolean inMessagePage;

    private List<User> users;
    private LinkedList<Admin> admins;
    private LinkedList<Supplier> suppliers;
    private MessageManager messageManager;
    private RecipeManager recipeManager;
    private boolean isMessageDisplayedforVlidationaRecipe = false;

    private static final String PASS ="123456";
    private static final String EMAIL_EX="s12112422@stu.najah.edu";

    private static final String CITY_EX="NABLUS";

    private final Logger logger;


    public boolean isMessageDisplayedforVlidationaRecipe() {
        return isMessageDisplayedforVlidationaRecipe;
    }


    private AdminManager adminManager;





    public SweetApp() {
        users = new LinkedList<>();
        suppliers = new LinkedList<>();
        admins=new LinkedList<>();
        this.logger = Logger.getLogger(SweetApp.class.getName());

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);

        User u1=new User("haya", PASS,"",EMAIL_EX,new Adress(CITY_EX,"",""),"u");
        users.add(u1);
        User u2=new User("sara", PASS,"",EMAIL_EX,new Adress(CITY_EX,"",""),"u");
        users.add(u2);
       Supplier s1=new Supplier("noor", PASS,"",EMAIL_EX,new Adress(CITY_EX,"",""),"s","sweetee",4);
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

       admins.add(new Admin("admin", PASS));

       this.loginManager = new LoginManager(users, suppliers,admins);
        this.userManager = new UserManager(users);
        this.supplierManager = new SupplierManager(suppliers);

        this.adminManager=new AdminManager(admins,supplierManager,userManager);

        this.messageManager=new MessageManager(userManager,supplierManager);
        messageManager.sendMessage("noor","haya","hello haya", LocalDate.now());
        s1.getOrderManager().updateOrderStatus("order002","shipped",userManager);
        s1.getOrderManager().updateOrderStatus("order003","delivered",userManager);

        this.recipeManager =new RecipeManager();
        Recipe rec1=new Recipe("Pancake", 3, "Milk, flour, oil", "Mix them all", u1.getUserName());
        recipeManager.postRecipe(rec1);
        rec1.addFeedback("0-by: haya nice and easy");

        rec1.setId(1);
        Recipe rec2= new Recipe("Chocolate Cake", 4, "Flour, sugar, cocoa powder, eggs, butter", "Bake it at 350°F for 30 minutes", u2.getUserName());
        recipeManager.postRecipe(rec2 );
        recipeManager.validateRecipe(rec2);
        rec2.addFeedback("0-by: sara delicious and rich");
        rec2.setId(2);

        Recipe rec3 = new Recipe("Vegetable Soup", 5, "Carrots, potatoes, celery, onions, water, salt", "Simmer all ingredients for 40 minutes", u1.getUserName());
        recipeManager.postRecipe(rec3);
        recipeManager.validateRecipe(rec3);
        rec3.addFeedback("0-by: haya healthy and comforting");
        rec3.setId(3);

        Recipe rec4 = new Recipe("Spaghetti Bolognese", 4, "Spaghetti, ground beef, tomato sauce, onions, garlic", "Cook spaghetti and sauce separately and then mix", u1.getUserName());
        recipeManager.postRecipe(rec4);
        recipeManager.validateRecipe(rec4);
        rec4.addFeedback("0-by: sara classic and tasty");
        rec4.setId(0);

        Recipe rec5= new Recipe("Caesar Salad", 2, "Lettuce, croutons, Parmesan cheese, Caesar dressing", "Toss all ingredients together", u2.getUserName());
        recipeManager.postRecipe(rec5 );
        recipeManager.validateRecipe(rec5);
        rec5.addFeedback("0-by: haya fresh and crisp");
        rec5.setId(4);

        Recipe rec6 = new Recipe("Lemonade", 1, "Lemons, sugar, water", "Mix lemon juice, sugar, and water", u1.getUserName());
        recipeManager.postRecipe(rec6);
        recipeManager.validateRecipe(rec6);
        rec6.addFeedback("0-by: sara refreshing and sweet");
        rec6.setId(5);

        Recipe rec7= new Recipe("French Toast", 2, "Bread, eggs, milk, cinnamon, butter", "Dip bread in egg mixture and cook in butter", u1.getUserName());
        recipeManager.postRecipe(rec7 );
        recipeManager.validateRecipe(rec7);
        rec7.addFeedback("0-by: haya easy and delicious");
        rec7.setId(6);

        Recipe rec8 = new Recipe("Chicken Curry", 4, "Chicken, curry powder, coconut milk, onions, garlic", "Cook chicken with spices and add coconut milk", u2.getUserName());
        recipeManager.postRecipe(rec8);
        recipeManager.validateRecipe(rec8);
        rec8.addFeedback("0-by: sara spicy and flavorful");
        rec8.setId(7);

        Recipe rec9= new Recipe("Fruit Salad", 2, "Apples, bananas, grapes, oranges, honey", "Mix all fruits and drizzle with honey", u2.getUserName());
        recipeManager.postRecipe(rec9 );
        recipeManager.validateRecipe(rec9);
        rec9.addFeedback("0-by: haya sweet and tangy");
        rec9.setId(8);

        Recipe rec10 = new Recipe("Grilled Cheese Sandwich", 1, "Bread, cheese, butter", "Butter bread, add cheese, and grill", u1.getUserName());
        recipeManager.postRecipe(rec10);
        recipeManager.validateRecipe(rec10);
        rec10.addFeedback("0-by: sara gooey and cheesy");
        rec10.setId(9);



    }



    public MessageManager getMessageManager() {
        return messageManager;
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


    public List<User> getUsers() {
        return users;
    }



    public boolean isInMessagePage() {
        return inMessagePage;
    }

    public void inMessagePage(boolean b) {
        this.inMessagePage =b;
    }
    public RecipeManager getRecipeManager(){
        return recipeManager;
    }


    public void messageAfterValedationOfTheRecipe(){
       logger.info("The Selected Recipe Has been Validated Successfulley ");
        isMessageDisplayedforVlidationaRecipe = true;
    }
    public void clearMessageDisplayedAfterRecipeValidation (){
        isMessageDisplayedforVlidationaRecipe = false;
    }

    public boolean deleteAccount(String userName) {

       if (null !=this.supplierManager.getTheSupplier(userName)) {
           supplierManager.deleteSupplier(userName);
           return true;
       } else if (null != this.userManager.getTheUser(userName)) {
           userManager.deleteUser(userName);
           return true;
       }
       return false;
    }
    public boolean addFeedbackforaProductByitsId (String id , String feedBack){
        for (Supplier Sup : suppliers) {
            if (Sup.getProductManager().findProduct(id)!= null) {
                Sup.getProductManager().findProduct(id).addFeedback(feedBack);
                return true;
            }
        }
        return false;
    }



    public AdminManager getAdminManager() {
        return adminManager;
    }

    public void run() {
        LoginView loginView = new LoginView( loginManager,userManager, supplierManager,messageManager,adminManager, recipeManager);
        loginView.displayMenu();
    }

}
