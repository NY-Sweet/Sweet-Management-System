package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import sweet.dev.managers.LoginManager;
import sweet.dev.managers.MessageManager;
import sweet.dev.managers.SupplierManager;
import sweet.dev.managers.UserManager;
import sweet.dev.system.SweetApp;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class messaging {

    public SweetApp obj;
    private boolean  success;
    private boolean failed;

    public LoginManager loginManager;
    public UserManager userManager;
    public SupplierManager supplierManager;
    public MessageManager messageManager;

    public messaging(SweetApp obj) {
        super();
        this.obj=obj;
        userManager = obj.getUserManager();
        supplierManager = obj.getSupplierManager();
        loginManager = obj.getLoginManager();
        messageManager=  obj.getMessageManager();

    }



    @Given("the user in Messages  page")
    public void the_user_in_messages_page() {
        obj.inMessagePage(true);

    }
    @When("user {string} set valid receiver={string} content ={string}  date {int}-{int}-{int} status read={int}")
    public void user_set_valid_receiver_content_date_status_read(String string, String string2, String string3, Integer int1, Integer int2, Integer int3, Integer int4) {
        LocalDate date = LocalDate.of(int3, int2, int1);
        success= messageManager.sendMessage(string,string2,string3,date);
    }
    @Then("success message")
    public void success_message() {
        assertTrue(obj.isInMessagePage());
        assertTrue(success);
    }

    @When("user {string} set invalid receiver={string} content ={string} date {int}-{int}-{int} status read={int}")
    public void user_set_invalid_receiver_content_date_status_read(String string, String string2, String string3, Integer int1, Integer int2, Integer int3, Integer int4) {
        LocalDate date = LocalDate.of(int3, int2, int1);
        failed= messageManager.sendMessage(string,string2,string3,date);

    }
    @Then("Failed message")
    public void failed_message() {
        assertTrue(obj.isInMessagePage());
        assertFalse(failed);
    }

    @When("user {string} View inbox Message")
    public void user_view_inbox_message(String string) {
        success=messageManager.viewInbox(string);

    }
    @Then("message is displayed")
    public void message_is_displayed() {
        assertTrue(success);

    }

}
