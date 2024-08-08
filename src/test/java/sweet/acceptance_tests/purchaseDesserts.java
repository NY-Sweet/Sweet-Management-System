package sweet.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.*;

import java.util.LinkedList;
import java.util.UUID;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.logging.Logger;

public class purchaseDesserts {

    public SweetApp obj;
    private static final Logger logger = Logger.getLogger(purchaseDesserts.class.getName());

    private LoginManager loginManager;
    private UserManager userManager;
    private ProductManager productManager;
    private OrderManager orderManager;

    private SupplierManager supplierManager;
    private supplier owner;
    private Order currentOrder;
    private user user;
    public purchaseDesserts(SweetApp obj) {
        super();
        this.obj=obj;
        userManager = obj.getUserManager();
        supplierManager = obj.getSupplierManager();
        loginManager = obj.getLoginManager();
    }
    @Given("I am a user {string} on the Purchase desserts page")
    public void i_am_a_user_on_the_purchase_desserts_page(String string) {
        obj.userInPurchasePage(true);
        user=userManager.getTheUser(string);

    }
    @When("I set valid shop name {string}")
    public void i_set_valid_shop_name(String shopName) {
        owner=supplierManager.getTheSupplierByUsingShopName(shopName);
        productManager=owner.getProductManager();
        orderManager=owner.getOrderManager();
    }
    @When("I select products with ids {string} and quantities {string}")
    public void i_select_products_with_ids_and_quantities(String productIds, String quantities) {
        makeOrderObject(productIds, quantities);

    }
    @Then("the order is successfully placed")
    public void the_order_is_successfully_placed() {
        StringBuilder table=new StringBuilder();
        orderManager.printProduct(currentOrder, table);
        logger.info(table.toString());
        assertTrue(orderManager.addOrder(currentOrder));
        assertTrue(supplierManager.isValidShopName());
        assertFalse(productManager.isInvalidProductId());
        logger.info("your order is in pending state we will send you soon an email when it's placed");
    }

    @When("I set invalid shop name {string}")
    public void i_set_invalid_shop_name(String shopName) {
        owner=supplierManager.getTheSupplierByUsingShopName(shopName);
    }

    @When("I select products with ids {string} and unavailable quantities {string}")
    public void i_select_products_with_ids_and_unavailable_quantities(String productIds, String quantities) {
        makeOrderObject(productIds, quantities);
    }
    @Then("an error message the order is rejected due to insufficient stock")
    public void an_error_message_the_order_is_rejected_due_to_insufficient_stock() {
        assertFalse(orderManager.addOrder(currentOrder));
        assertTrue(supplierManager.isValidShopName());
        assertFalse(productManager.isInvalidProductId());
        logger.info("the order is rejected due to insufficient stock");

    }


    @Then("an error message Invalid shop Name is displayed")
    public void an_error_message_invalid_shop_Name_is_displayed() {
        assertFalse(supplierManager.isValidShopName());
        logger.info("invalid shop name");
    }

    @Then("an error message Invalid product ID is displayed")
    public void an_error_message_invalid_product_id_is_displayed() {
        assertTrue(productManager.isInvalidProductId());
        logger.info("invalid product id");
    }


    @When("I select invalid products with ids {string} and quantities {string}")
    public void i_select_invalid_products_with_ids_and_quantities(String productIds, String quantities) {
        makeOrderObject(productIds, quantities);
    }

    private void makeOrderObject(String productIds, String quantities) {
        String[] productIdArray = productIds.split(",");
        String[] quantityArray = quantities.split(",");
        LinkedList<OrderDetails> orderDetailsList = new LinkedList<>();

        for (int i = 0; i < productIdArray.length; i++) {
            product product = productManager.findProduct(productIdArray[i]);
            if(productManager.isInvalidProductId())
                return;
            int quantity = Integer.parseInt(quantityArray[i]);
            OrderDetails orderDetails = new OrderDetails(product, quantity);

            orderDetailsList.add(orderDetails);
        }
        if(!productManager.isInvalidProductId())
            currentOrder = new Order(UUID.randomUUID().toString(), user.getUserName(), orderDetailsList);
    }

}
