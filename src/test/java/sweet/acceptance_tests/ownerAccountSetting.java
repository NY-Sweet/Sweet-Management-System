package sweet.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.*;

import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ownerAccountSetting {
    public SweetApp obj;
    private supplier owner;


    public LoginManager loginManager;
    public UserManager userManager;
    public SupplierManager supplierManager;

    private static final Logger logger = Logger.getLogger(ownerReports.class.getName());
    public ownerAccountSetting(SweetApp obj) {
        super();
        this.obj=obj;

        userManager = obj.getUserManager();
        supplierManager = obj.getSupplierManager();
        loginManager = obj.getLoginManager();

    }


    @Given("owner {string} is on the account settings Account_Settings")
    public void owner_is_on_the_account_settings_account_settings(String string) {
        owner=supplierManager.getTheSupplier(string);

    }
    @When("the owner selects edit city and updates the field with a new {string}")
    public void the_owner_selects_edit_city_and_updates_the_field_with_a_new(String string) {
       owner.setCity(string);
    }
    @Then("the system displays a message indicating that the city has been updated successfully")
    public void the_system_displays_a_message_indicating_that_the_city_has_been_updated_successfully() {
       assertTrue("city update successfully",owner.isOperationSuccess());
    }

    @When("the owner selects edit street and updates the field with a new {string}")
    public void the_owner_selects_edit_street_and_updates_the_field_with_a_new(String string) {
       owner.setStreet(string);
    }
    @Then("the system displays a message indicating that the street has been updated successfully")
    public void the_system_displays_a_message_indicating_that_the_street_has_been_updated_successfully() {
        assertTrue("strret update successfully",owner.isOperationSuccess());
    }

    @When("the owner selects edit home number and updates the field with a new {string}")
    public void the_owner_selects_edit_home_number_and_updates_the_field_with_a_new(String string) {
        owner.setHomeNum(string);
    }
    @Then("the system displays a message indicating that the home number has been updated successfully")
    public void the_system_displays_a_message_indicating_that_the_home_number_has_been_updated_successfully() {
        assertTrue("home number update successfully",owner.isOperationSuccess());
    }


    @When("the owner selects edit phone number and updates the field with a new {string}")
    public void the_owner_selects_edit_phone_number_and_updates_the_field_with_a_new(String string) {
        owner.setPhoneNum(string);
    }

    @Then("the system displays a message indicating that the phone number has been updated successfully")
    public void the_system_displays_a_message_indicating_that_the_phone_number_has_been_updated_successfully() {
        assertTrue("phone number update successfully",owner.isOperationSuccess());
    }

    @When("the owner selects edit email and updates the field with a new {string}")
    public void the_owner_selects_edit_email_and_updates_the_field_with_a_new(String string) {
      owner.setEmail(string);
    }

    @Then("the system displays a message indicating that the email has been updated successfully")
    public void the_system_displays_a_message_indicating_that_the_email_has_been_updated_successfully() {
        assertTrue("email update successfully",owner.isOperationSuccess());
    }

    @When("the owner selects edit employee number and updates the field with a new {string}")
    public void the_owner_selects_edit_employee_number_and_updates_the_field_with_a_new(String string) {
       owner.setEpmloyeeNum(Integer.parseInt(string));
    }

    @Then("the system displays a message indicating that the employee number has been updated successfully")
    public void the_system_displays_a_message_indicating_that_the_employee_number_has_been_updated_successfully() {
        assertTrue("employee number update successfully",owner.isOperationSuccess());
    }

    @When("the owner select edit password and enters old password  {string} new password {string} and confirm password {string}")
    public void theOwnerSelectEditPasswordAndEntersOldPasswordNewPasswordAndConfirmPassword(String oldPassword, String newPassword, String confirmPassword) {
       owner.updatePassword(oldPassword,newPassword,confirmPassword);
    }
    @Then("the system displays a message indicating that the  password has been updated successfully")
    public void theSystemDisplaysAMessageIndicatingThatThePasswordHasBeenUpdatedSuccessfully() {

        assertTrue("password update successfully",owner.isOperationSuccess());
       assertFalse(owner.isMismatchPass());
        assertFalse(owner.isWrongOldPass());
    }

    @When("the owner select edit password and enters incorrect old password  {string} new password {string} and confirm password {string}")
    public void the_owner_select_edit_password_and_enters_incorrect_old_password_new_password_and_confirm_password(String oldPassword, String newPassword, String confirmPassword) {
        owner.updatePassword(oldPassword,newPassword,confirmPassword);

    }

    @Then("the system displays a message indicating that the old password wrong")
    public void the_system_displays_a_message_indicating_that_the_old_password_wrong() {
        assertFalse("password failed to update ",owner.isOperationSuccess());
        assertFalse(owner.isMismatchPass());
        assertTrue("the old password is wrong",owner.isWrongOldPass());
    }

    @When("the owner select edit password and enters old password  {string} new password {string} and mismatch confirm password {string}")
    public void the_owner_select_edit_password_and_enters_old_password_new_password_and_mismatch_confirm_password(String oldPassword, String newPassword, String confirmPassword) {
        owner.updatePassword(oldPassword,newPassword,confirmPassword);
    }

    @Then("the system displays a message indicating that the  password mismatch")
    public void the_system_displays_a_message_indicating_that_the_password_mismatch() {
        assertFalse("password failed to update ",owner.isOperationSuccess());
        assertTrue("the  password mismatch",owner.isMismatchPass());
        assertFalse(owner.isWrongOldPass());
    }


    @When("the owner select account information")
    public void the_owner_select_account_information() {
        owner.displaySupplierInfo();

    }
    @Then("display owner information")
    public void display_owner_information() {
        assertTrue(owner.displaySupplierInfo());
    }




}
