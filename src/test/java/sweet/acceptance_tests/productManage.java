package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import sweet.dev.managers.LoginManager;
import sweet.dev.managers.SupplierManager;
import sweet.dev.managers.UserManager;
import sweet.dev.models.*;
import sweet.dev.system.SweetApp;

import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class productManage {
    public SweetApp obj;
    private Supplier owner;
    private boolean result;

    private LoginManager loginManager;
    private UserManager userManager;
    private SupplierManager supplierManager;

    private static final Logger logger = Logger.getLogger(productManage.class.getName());
    public productManage(SweetApp obj) {
        super();
        this.obj=obj;

        userManager = obj.getUserManager();
        userManager.addUser(new User("haya","123456","","",new Adress("","",""),"u"));
        supplierManager = obj.getSupplierManager();
        loginManager = obj.getLoginManager();


    }
    private void messageSuccessToEdit() {
        assertTrue("product edit success",owner.getProductManager().isOperationSuccess());
        logger.info("the product edit successfully");
    }
    private void messageFiledToEdit() {
        assertFalse("product edit failed",owner.getProductManager().isOperationSuccess());
        logger.info("the product edit failed as it's not exist in the system");
    }


    @Given("the owner in Product page")
    public void the_owner_in_product_page() {
   // obj.setInProductPage(true);
    }
    @When("the {string} enters option to show products")
    public void the_enters_option_to_show_products(String string) {
        owner=supplierManager.getTheSupplier(string);
    }
    @Then("product show")
    public void product_show() {
        assertTrue(owner.getProductManager().showProducts());
    }

    @When("the owner {string} enters option to add products")
    public void the_owner_enters_option_to_add_products(String string) {
        owner=supplierManager.getTheSupplier(string);
    }


    @When("set product Id={string} product name ={string} quantity ={int}  price={double}  cost={double} expiration date {int}-{int}-{int} discount percentage={double}")
    public void set_product_id_product_name_quantity_price_cost_expiration_date_discount_percentage(String id, String name, Integer quantity, Double price,Double cost, Integer day, Integer month, Integer year, Double percentage) {
        owner.getProductManager().addProduct(new Product(id,name,quantity,price,cost,new Date(day,month,year),percentage));
    }
    @Then("the product is created successfully and a  message is displayed")
    public void the_product_is_created_successfully_and_a_message_is_displayed() {
        assertTrue("product created successfully",owner.getProductManager().isOperationSuccess());
        logger.info("the product is created successfully");
    }

    @When("set invalid product Id={string} product name ={string} quantity ={int}  price={double}  cost={double} expiration date {int}-{int}-{int} discount percentage={double}")
    public void setInvalidProductIdProductNameQuantityPriceCostExpirationDateDiscountPercentage(String id, String name, Integer quantity, Double price,Double cost, Integer day, Integer month, Integer year, Double percentage) {
        owner.getProductManager().addProduct(new Product(id,name,quantity,price,cost,new Date(day,month,year),percentage));
    }
    @Then("message is displayed the product is already added")
    public void message_is_displayed_the_product_is_already_added() {
        assertFalse("product created failed",owner.getProductManager().isOperationSuccess());
        logger.info("the product created failed as it's already in the system");
    }


    @When("the owner {string} enters option to edit product name")
    public void the_owner_enters_option_to_edit_product_name(String string) {
        owner=supplierManager.getTheSupplier(string);
    }

    @When("set product Id={string} new product name ={string}")
    public void set_product_id_new_product_name(String id, String name) {
       owner.getProductManager().editProductName(id,name);
    }

    @Then("the product name update successfully message is displayed")
    public void the_product_name_update_successfully_message_is_displayed() {
        messageSuccessToEdit();
    }


    @When("set product invalid Id={string} new product name ={string}")
    public void set_product_invalid_id_new_product_name(String id, String name) {
        owner.getProductManager().editProductName(id,name);
    }
    @Then("failed to edit the product name and message is displayed")
    public void failed_to_edit_the_product_name_and_message_is_displayed() {
        messageFiledToEdit();
    }


    @When("the owner {string} enters the option to edit product quantity")
    public void the_owner_enters_the_option_to_edit_product_quantity(String string) {
        owner=supplierManager.getTheSupplier(string);
    }

    @When("set product Id={string} new product quantity ={int}")
    public void set_product_id_new_product_quantity(String string, int quantity) {
        owner.getProductManager().editProductQuantity(string,quantity);
    }

    @Then("the product quantity update successfully and a message is displayed")
    public void the_product_quantity_update_successfully_and_a_message_is_displayed() {
        messageSuccessToEdit();
    }

    @When("set invalid product Id={string} new product quantity ={int}")
    public void set_invalid_product_id_new_product_quantity(String string, int quantity) {
        owner.getProductManager().editProductQuantity(string,quantity);

    }
    @Then("failed to edit the product quantity  and a message is displayed")
    public void failed_to_edit_the_product_quantity_and_a_message_is_displayed() {
        messageFiledToEdit();
    }

    @When("the owner {string} enters the option to edit the product price")
    public void the_owner_enters_the_option_to_edit_the_product_price(String string) {
        owner=supplierManager.getTheSupplier(string);
    }
    @When("set product Id={string} new product price ={double}")
    public void set_product_id_new_product_price(String string, double price) {
        owner.getProductManager().editProductPrice(string,price);

    }
    @Then("the product price update successfully and a message is displayed")
    public void the_product_price_update_successfully_and_a_message_is_displayed() {
        messageSuccessToEdit();
    }

    @When("set invalid product Id={string} new product price ={double}")
    public void set_invalid_product_id_new_product_price(String string, double price) {
        owner.getProductManager().editProductPrice(string,price);

    }
    @Then("failed to edit the product price and a message is displayed")
    public void failed_to_edit_the_product_price_and_a_message_is_displayed() {
      messageFiledToEdit();
    }


    @When("the owner {string} enters the option to edit product cost")
    public void the_owner_enters_the_option_to_edit_product_cost(String string) {
        owner=supplierManager.getTheSupplier(string);
    }
    @When("set product Id={string} new product cost ={double}")
    public void set_product_id_new_product_cost(String string, double cost) {
   owner.getProductManager().editProductCost(string,cost);
    }

    @Then("the product cost update successfully And a  message is displayed")
    public void the_product_cost_update_successfully_and_a_message_is_displayed() {
     messageSuccessToEdit();
    }
    @When("set invalid product Id={string} new product cost ={double}")
    public void set_invalid_product_id_new_product_cost(String string, double cost) {
        owner.getProductManager().editProductCost(string,cost);
    }
    @Then("faild to edit the product cost  And a  message is displayed")
    public void faild_to_edit_the_product_cost_and_a_message_is_displayed() {
       messageFiledToEdit();
    }
    @When("the owner {string} enters the option to edit product  expiration date")
    public void theOwnerEntersTheOptionToEditProductExpirationDate(String string) {
       owner=supplierManager.getTheSupplier(string);

    }
    @When("set product Id={string} new product expiration date ={int}-{int}-{int}")
    public void setProductIdNewProductExpirationDate(String string, Integer day, Integer month, Integer year) {
        owner.getProductManager().editProductExpirationDate(string,day,month,year);
    }
    @Then("the product expiration date update successfully And a  message is displayed")
    public void theProductExpirationDateUpdateSuccessfullyAndAMessageIsDisplayed() {
        messageSuccessToEdit();
    }
    @When("set invalid product Id={string} new product expiration date ={int}-{int}-{int}")
    public void setInvalidProductIdNewProductExpirationDate(String string, Integer day, Integer month, Integer year) {
        owner.getProductManager().editProductExpirationDate(string,day,month,year);
    }
    @Then("failed to edit the product expiration date  And a  message is displayed")
    public void failedToEditTheProductExpirationDateAndAMessageIsDisplayed() {
       messageFiledToEdit();
    }


    @When("the owner {string} enters the option to delete product")
    public void the_owner_enters_the_option_to_delete_product(String string) {
        owner=supplierManager.getTheSupplier(string);

    }
    @When("set product Id={string}")
    public void set_product_id(String id) {
        owner.getProductManager().deleteProduct(id);

    }
    @Then("the product delete successfully a  message is displayed")
    public void the_product_delete_successfully_a_message_is_displayed() {
        assertTrue(owner.getProductManager().isOperationSuccess());
        logger.info("product deleted successfully");
    }

    @When("set invalid product Id={string}")
    public void set_invalid_product_id(String id) {
        owner.getProductManager().deleteProduct(id);
    }
    @Then("the product delete failed a  message is displayed")
    public void the_product_delete_failed_a_message_is_displayed() {
        assertFalse(owner.getProductManager().isOperationSuccess());
        logger.info("product delete failed");

    }


    @When("the owner {string} enters the option to View Discount Product")
    public void theOwnerEntersTheOptionToViewDiscountProduct(String string) {
        owner=supplierManager.getTheSupplier(string);
    }
    @Then("discount product show")
    public void discount_product_show() {
     assertTrue(owner.getProductManager().showDiscountProducts());

    }

    @When("the owner {string} enters the option to set Discount rule")
    public void theOwnerEntersTheOptionToSetDiscountRule(String string) {
      owner=supplierManager.getTheSupplier(string);
    }

    @When("set percentage ={double} day before expiration={int}")
    public void set_percentage_day_before_expiration(Double percentage, Integer days) {
        owner.getProductManager().setDiscountRule(percentage,days);

    }

    @Then("the Discount rule Applied successfully and a  message is displayed")
    public void the_discount_rule_applied_successfully_and_a_message_is_displayed() {
        assertTrue(  owner.getProductManager().showDiscountProducts());

    }
    @When("the customer requests to view all products from shop {string}")
    public void the_customer_requests_to_view_all_products_from_shop(String string) {
        owner=supplierManager.getTheSupplierByUsingShopName(string);
        result=owner.getProductManager().showProductsForCustomer();
    }

    @Then("the product details are displayed with discounts and feedbacks")
    public void the_product_details_are_displayed_with_discounts_and_feedbacks() {
       assertTrue(result);
    }


}
