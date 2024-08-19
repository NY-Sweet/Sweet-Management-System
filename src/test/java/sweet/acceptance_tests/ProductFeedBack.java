package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.system.SweetApp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductFeedBack {
    SweetApp obj = new SweetApp();
    String productid;
    String feedBackContent;
    @When("the user enters  product ID {string}, and feedback content {string}")
    public void the_user_enters_product_id_and_feedback_content(String int1, String string) {
        productid = int1;
        feedBackContent = string;
    }
    @Then("the feedback is sent and a confirmation message appears")
    public void the_feedback_is_sent_and_a_confirmation_message_appears() {
     assertTrue("Succed",obj.addFeedbackforaProductByitsId(productid, feedBackContent));
    }

    @When("the user enters  product ID {string} and this product is not asigned , and feedback content {string}")
    public void the_user_enters_product_id_and_this_product_is_not_asigned_and_feedback_content(String string, String string2) {
        productid = string;
        feedBackContent = string2;
    }
    @Then("a failed message appears")
    public void a_failed_message_appears() {
        assertFalse("Failed",obj.addFeedbackforaProductByitsId(productid, feedBackContent));

    }



}
