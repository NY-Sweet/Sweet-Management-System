package sweet.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.managers.LoginManager;
import sweet.dev.managers.SupplierManager;
import sweet.dev.managers.UserManager;
import sweet.dev.models.Supplier;
import sweet.dev.system.SweetApp;

import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class orderStatus {

    public SweetApp obj;
    private Supplier owner;
    private boolean  isValidOption;


    public LoginManager loginManager;
    public UserManager userManager;
    public SupplierManager supplierManager;

    private static final Logger logger = Logger.getLogger(orderStatus.class.getName());
    public orderStatus(SweetApp obj) {
        super();
        this.obj=obj;

        userManager = obj.getUserManager();
        supplierManager = obj.getSupplierManager();
        loginManager = obj.getLoginManager();

    }

    @Given("I'm the owner in track order page")
    public void i_m_the_owner_in_track_order_page() {
        obj.inOwnerTrackOrderPage(true);
    }
    @When("the owner {string} choose option  change pending set id {string} and states {string}")
    public void theOwnerChooseOptionChangePendingSetIdOrderIdAndStates(String string, String id,String state) {
        orderUpdateCall(string, id, state);

        owner.getOrderManager().showPendingOrders();
    }
    @Then("the order status should be updated and email sent")
    public void the_order_status_should_be_updated_and_email_sent() {
       assertTrue(owner.getOrderManager().isSuccessOperation());

    }

    @When("the owner {string} choose option change shipped set id {string} and states {string}")
    public void theOwnerChooseOptionChangeShippedSetIdOrderIdAndStates(String string, String id,String state) {
        orderUpdateCall(string, id, state);

        owner.getOrderManager().showShippedOrders();

    }

    private void orderUpdateCall(String string, String id, String state) {
        owner=supplierManager.getTheSupplier(string);
        owner.getOrderManager().updateOrderStatus(id, state,userManager);
    }

    @When("the owner {string} chooses option Show delivered orders")
    public void the_owner_chooses_option_show_delivered_orders(String string) {
        owner=supplierManager.getTheSupplier(string);
        owner.getOrderManager().showDeliveredOrders();

    }
    @Then("the delivered orders should be displayed")
    public void the_delivered_orders_should_be_displayed() {
        assertTrue(owner.getOrderManager().isSuccessOperation());

    }

    @When("the owner {string} choose update option set invalid id {string}")
    public void the_owner_choose_update_option_set_invalid_id(String string, String id) {
        orderUpdateCall(string, id,"");
    }
    @Then("the order status failed to updated")
    public void the_order_status_failed_to_updated() {
        assertFalse(owner.getOrderManager().isSuccessOperation());

    }





}
