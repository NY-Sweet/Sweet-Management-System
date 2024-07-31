package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import sweet.dev.*;

import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class productManage {
    public SweetApp obj;
    private supplier owner;
    private String option;

    public LoginManager loginManager;
    public UserManager userManager;
    public SupplierManager supplierManager;

    private static final Logger logger = Logger.getLogger(productManage.class.getName());
    public productManage(SweetApp obj) {
        super();
        this.obj=obj;

        userManager = obj.getUserManager();
        userManager.addUser("haya","123456","","","","","","u");
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


    @Given("the owner in owner page")
    public void the_owner_in_owner_page() {
        obj.IamInOwnerPage(obj);
    }
    @When("the owner enters the {int}")
    public void the_owner_enters_the(Integer int1) {
       obj.ownerChooseToEnterProductPage(int1);
    }
    @Then("the owner enters the Product page")
    public void the_owner_enters_the_product_page() {
        assertTrue("owner succeed enter product page",obj.isInProductPage());
    }

    @Then("the owner doesn't enter the Product page")
    public void the_owner_doesn_t_enter_the_product_page() {
        assertFalse("owner failed to enter product page",obj.isInProductPage());
    }

    @Given("the owner in Product page")
    public void the_owner_in_product_page() {
    obj.setInProductPage(true);
    }
    @When("the {string} enters option to show products {string}")
    public void the_enters_option_to_show_products(String string, String string2) {

        obj.showProductPage(string);
        owner=supplierManager.getTheSupplier(string);
    }
    @Then("product show")
    public void product_show() {
        assertTrue("Owner is not in product page", obj.isInProductPage());
        owner.getProductManager().showProducts();
    }

    @When("the owner {string} enters option to add products {string}")
    public void the_owner_enters_option_to_add_products(String string, String string2) {
        obj.optionToAddProducts(string2);
        owner=supplierManager.getTheSupplier(string);
    }


    @When("set product Id={string} product name ={string} quantity ={int}  price={double}  cost={double} expiration date {int}-{int}-{int} discount percentage={double}")
    public void set_product_id_product_name_quantity_price_cost_expiration_date_discount_percentage(String id, String name, Integer quantity, Double price,Double cost, Integer day, Integer month, Integer year, Double percentage) {
        owner.getProductManager().addProduct(id,name,quantity,price,cost,day,month,year,percentage);
    }
    @Then("the product is created successfully and a  message is displayed")
    public void the_product_is_created_successfully_and_a_message_is_displayed() {
        assertTrue("Owner is option to add product", obj.isShowAddProductPage());
        assertTrue("product created successfully",owner.getProductManager().isOperationSuccess());
        logger.info("the product is created successfully");
    }

    @When("set invalid product Id={string} product name ={string} quantity ={int}  price={double}  cost={double} expiration date {int}-{int}-{int} discount percentage={double}")
    public void setInvalidProductIdProductNameQuantityPriceCostExpirationDateDiscountPercentage(String id, String name, Integer quantity, Double price,Double cost, Integer day, Integer month, Integer year, Double percentage) {
        owner.getProductManager().addProduct(id,name,quantity,price,cost,day,month,year,percentage);
    }
    @Then("message is displayed the product is already added")
    public void message_is_displayed_the_product_is_already_added() {
        assertTrue("Owner is option to add product", obj.isShowAddProductPage());
        assertFalse("product created failed",owner.getProductManager().isOperationSuccess());
        logger.info("the product created failed as it's already in the system");
    }


    @When("the owner {string} enters option to edit product name {string}")
    public void the_owner_enters_option_to_edit_product_name(String string,String string2) {
        obj.EditProductName(string2);
        owner=supplierManager.getTheSupplier(string);
    }

    @When("set product Id={string} new product name ={string}")
    public void set_product_id_new_product_name(String id, String name) {
       owner.getProductManager().editProductName(id,name);
    }

    @Then("the product name update successfully message is displayed")
    public void the_product_name_update_successfully_message_is_displayed() {

        assertTrue( obj.isEditProductName());
        messageSuccessToEdit();
    }


    @When("set product invalid Id={string} new product name ={string}")
    public void set_product_invalid_id_new_product_name(String id, String name) {
        owner.getProductManager().editProductName(id,name);
    }
    @Then("failed to edit the product name and message is displayed")
    public void failed_to_edit_the_product_name_and_message_is_displayed() {
        assertTrue(obj.isEditProductName());
        messageFiledToEdit();
    }


    @When("the owner {string} enters the option to edit product quantity  {string}")
    public void the_owner_enters_the_option_to_edit_product_quantity(String string,String string2) {
        obj.EditProductQuantity(string2);
        owner=supplierManager.getTheSupplier(string);
    }

    @When("set product Id={string} new product quantity ={int}")
    public void set_product_id_new_product_quantity(String string, int quantity) {
        owner.getProductManager().editProductQuantity(string,quantity);
    }

    @Then("the product quantity update successfully and a message is displayed")
    public void the_product_quantity_update_successfully_and_a_message_is_displayed() {
        assertTrue( obj.isEditProductQuantity());
        messageSuccessToEdit();
    }

    @When("set invalid product Id={string} new product quantity ={int}")
    public void set_invalid_product_id_new_product_quantity(String string, int quantity) {
        owner.getProductManager().editProductQuantity(string,quantity);

    }
    @Then("failed to edit the product quantity  and a message is displayed")
    public void failed_to_edit_the_product_quantity_and_a_message_is_displayed() {
        assertTrue( obj.isEditProductQuantity());
        messageFiledToEdit();
    }

    @When("the owner {string} enters the option to edit the product price {string}")
    public void the_owner_enters_the_option_to_edit_the_product_price(String string,String option) {
        obj.EditProductPrice(option);
        owner=supplierManager.getTheSupplier(string);
    }
    @When("set product Id={string} new product price ={double}")
    public void set_product_id_new_product_price(String string, double price) {
        owner.getProductManager().editProductPrice(string,price);

    }
    @Then("the product price update successfully and a message is displayed")
    public void the_product_price_update_successfully_and_a_message_is_displayed() {
        assertTrue( obj.isEditProductPrice());
        messageSuccessToEdit();
    }

    @When("set invalid product Id={string} new product price ={double}")
    public void set_invalid_product_id_new_product_price(String string, double price) {
        owner.getProductManager().editProductPrice(string,price);

    }
    @Then("failed to edit the product price and a message is displayed")
    public void failed_to_edit_the_product_price_and_a_message_is_displayed() {
        assertTrue( obj.isEditProductPrice());
      messageFiledToEdit();
    }


    @When("the owner {string} enters the option to edit product cost {string}")
    public void the_owner_enters_the_option_to_edit_product_cost(String string,String option) {
        obj.EditProductCost(option);
        owner=supplierManager.getTheSupplier(string);
    }
    @When("set product Id={string} new product cost ={double}")
    public void set_product_id_new_product_cost(String string, double cost) {
   owner.getProductManager().editProductCost(string,cost);
    }

    @Then("the product cost update successfully And a  message is displayed")
    public void the_product_cost_update_successfully_and_a_message_is_displayed() {
        assertTrue( obj.isEditProductCost());
     messageSuccessToEdit();
    }
    @When("set invalid product Id={string} new product cost ={double}")
    public void set_invalid_product_id_new_product_cost(String string, double cost) {
        owner.getProductManager().editProductCost(string,cost);
    }
    @Then("faild to edit the product cost  And a  message is displayed")
    public void faild_to_edit_the_product_cost_and_a_message_is_displayed() {
        assertTrue( obj.isEditProductCost());
       messageFiledToEdit();
    }
    @When("the owner {string} enters the option to edit product  expiration date {string}")
    public void theOwnerEntersTheOptionToEditProductExpirationDate(String string, String string2) {
       obj.EditProductExpirationDate(string2);
       owner=supplierManager.getTheSupplier(string);

    }
    @When("set product Id={string} new product expiration date ={int}-{int}-{int}")
    public void setProductIdNewProductExpirationDate(String string, Integer day, Integer month, Integer yaer) {
        owner.getProductManager().editProductExpirationDate(string,day,month,yaer);
    }
    @Then("the product expiration date update successfully And a  message is displayed")
    public void theProductExpirationDateUpdateSuccessfullyAndAMessageIsDisplayed() {
        assertTrue(obj.isEditProductExpirationDate());
        messageSuccessToEdit();
    }
    @When("set invalid product Id={string} new product expiration date ={int}-{int}-{int}")
    public void setInvalidProductIdNewProductExpirationDate(String string, Integer day, Integer month, Integer yaer) {
        owner.getProductManager().editProductExpirationDate(string,day,month,yaer);
    }
    @Then("failed to edit the product expiration date  And a  message is displayed")
    public void failedToEditTheProductExpirationDateAndAMessageIsDisplayed() {
        assertTrue(obj.isEditProductExpirationDate());
       messageFiledToEdit();
    }


    @When("the owner {string} enters the option to delete product {string}")
    public void the_owner_enters_the_option_to_delete_product(String string,String string2) {
        obj.DeleteProduct(string2);
        owner=supplierManager.getTheSupplier(string);

    }
    @When("set product Id={string}")
    public void set_product_id(String id) {
        owner.getProductManager().deleteProduct(id);

    }
    @Then("the product delete successfully a  message is displayed")
    public void the_product_delete_successfully_a_message_is_displayed() {
        assertTrue(obj.isDeleteProductOption());
        assertTrue(owner.getProductManager().isOperationSuccess());
        logger.info("product deleted successfully");
    }

    @When("set invalid product Id={string}")
    public void set_invalid_product_id(String id) {
        owner.getProductManager().deleteProduct(id);
    }
    @Then("the product delete failed a  message is displayed")
    public void the_product_delete_failed_a_message_is_displayed() {
        assertTrue(obj.isDeleteProductOption());
        assertFalse(owner.getProductManager().isOperationSuccess());
        logger.info("product delete failed");

    }


    @When("the owner {string} enters the option to View Discount Product {string}")
    public void theOwnerEntersTheOptionToViewDiscountProduct(String string, String string2) {
        obj.ViewDiscountProduct(string2);
        owner=supplierManager.getTheSupplier(string);
    }
    @Then("discount product show")
    public void discount_product_show() {
        owner.getProductManager().showDiscountProducts();
     assertTrue(obj.isViewDiscountProduct());

    }

    @When("the owner {string} enters the option to set Discount rule  {string}")
    public void theOwnerEntersTheOptionToSetDiscountRule(String string, String string2) {
      obj.setDiscountRuleOption(string2);
      owner=supplierManager.getTheSupplier(string);
    }

    @When("set percentage ={double} day before expiration={int}")
    public void set_percentage_day_before_expiration(Double percentage, Integer days) {
        owner.getProductManager().setDiscountRule(percentage,days);
        owner.getProductManager().showDiscountProducts();
    }

    @Then("the Discount rule Applied successfully and a  message is displayed")
    public void the_discount_rule_applied_successfully_and_a_message_is_displayed() {
        assertTrue(obj.isSetDiscountRuleOption());

    }

    @When("the owner enters the option to exit product page  {string}")
    public void the_owner_enters_the_option_to_exit_product_page(String string) {
        obj.ExitProductPage(string);
    }
    @Then("go back to owner page")
    public void go_back_to_owner_page() {
        assertTrue("Owner has exited the product page", obj.isExitProductPage());
    }


    @When("the owner enters an invalid option  {string}")
    public void theOwnerEntersAnInvalidOption(String string) {
       obj.invalidOptionInProductPage(string);
    }


    @Then("invalid option and  message is displayed")
    public void invalidOptionAndMessageIsDisplayed() {
        assertTrue(obj.isInvalidOptionInProductPage());
    }


}
