package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.SweetApp;

import static org.junit.Assert.assertTrue;

public class ProductFeedBack {
    SweetApp obj = new SweetApp();
    String productid;
    String FeedBackContent;
    @When("the user enters  product ID {string}, and feedback content {string}")
    public void the_user_enters_product_id_and_feedback_content(String int1, String string) {
        productid = int1;
        FeedBackContent = string;
    }
    @Then("the feedback is sent and a confirmation message appears")
    public void the_feedback_is_sent_and_a_confirmation_message_appears() {
     assertTrue("Succed",obj.addFeedbackforaProductByitsId(productid,FeedBackContent));
    }

}
