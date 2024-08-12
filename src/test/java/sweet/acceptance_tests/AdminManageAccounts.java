package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.SweetApp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdminManageAccounts {
    SweetApp obj = new SweetApp();
    String NewName, NewPassword, phoneNum,  email,city;
    String street,  homeNum, shopName;
    int employeeNum;
    String role;
    @When("admin add new supplier by  setting  new username {string}, password {string}, city={string},street={string},home number={string}, phone number={string} , email={string}  role={string}  shop name {string} and employeeNumber {int}")
    public void admin_add_new_supplier_by_setting_new_username_password_city_street_home_number_phone_number_email_role_shop_name_and_employee_number(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, Integer int1) {
        NewName = string;
        NewPassword = string2;
        city = string3;
        street = string4;
        homeNum = string5;
        phoneNum = string6;
        email = string7;
        role= string8;
        shopName = string9;
        employeeNum = int1;
    }
    @Then("a new supplier will be created")
    public void a_new_supplier_will_be_created() {
        assertTrue("Succeed",obj.getSupplierManager().createAccountForSupplier(NewName, NewPassword,city,street,homeNum,phoneNum,email,"s",shopName,employeeNum));

    }

    @When("admin add new user by setting  new username {string}, password {string}, city={string},street={string},home number={string}, phone number={string} , email={string}  role={string}")
    public void admin_add_new_user_by_setting_new_username_password_city_street_home_number_phone_number_email_role(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8) {
       NewName = string;
       NewPassword = string2;
       city = string3;
       street = string4;
       homeNum = string5;
       phoneNum = string6;
       email = string7;
       role= string8;

    }
    @Then("a new user will be created")
    public void a_new_user_will_be_created() {
        assertTrue("Succeed",obj.getUserManager().addUser(NewName, NewPassword,city,street,homeNum,phoneNum,email,role));

    }
    String UserNametobedeleted;
    @When("the admin set  username {string} to delete this user")
    public void theAdminSetUsernameToDeleteThisUser(String string) {
        UserNametobedeleted = string;
    }
    @Then("the user deleted and a message displayed")
    public void the_user_deleted_and_a_message_displayed() {
        assertTrue("Succeed",obj.DeleteAccount(UserNametobedeleted));
    }
    @Then("a failed message displayed")
    public void a_failed_message_displayed() {
        assertFalse("Succeed",obj.DeleteAccount(UserNametobedeleted));
    }
    @Then("the Supplier deleted and a message displayed")
    public void the_supplier_deleted_and_a_message_displayed() {
        assertTrue("Succeed",obj.DeleteAccount(UserNametobedeleted));
    }


    @When("the admin set option to show supplier")
    public void the_admin_set_option_to_show_supplier() {

    }

    @Then("all supplier details displayed")
    public void all_supplier_details_displayed() {
        assertTrue("Succeed",obj.getSupplierManager().displayallsuplliers());
    }

    @When("the admin set option to show users")
    public void the_admin_set_option_to_show_users() {

    }

    @Then("all users details displayed")
    public void all_users_details_displayed() {
        assertTrue("Succeed",obj.getUserManager().DisplayAllUsers());

    }


}
