package sweet.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.*;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class orderStatus {

    public SweetApp obj;
    private supplier owner;
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
        owner=supplierManager.getTheSupplier(string);
      owner.getOrderManager().showPendingOrders();
       owner.getOrderManager().updateOrderStatus(id,state);
    }
    @Then("the order status should be updated and email sent")
    public void the_order_status_should_be_updated_and_email_sent() {
       assertTrue(owner.getOrderManager().isSuccessOperation());

    }

    @When("the owner {string} choose option change shipped set id {string} and states {string}")
    public void theOwnerChooseOptionChangeShippedSetIdOrderIdAndStates(String string, String id,String state) {
        owner=supplierManager.getTheSupplier(string);
        owner.getOrderManager().showShippedOrders();
        owner.getOrderManager().updateOrderStatus(id,state);

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




}
