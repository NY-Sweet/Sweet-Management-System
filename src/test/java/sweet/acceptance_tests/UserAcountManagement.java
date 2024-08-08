package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.SweetApp;
import sweet.dev.UserManager;
import sweet.dev.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserAcountManagement {
    SweetApp obj = new SweetApp();
    String x;
    String NewCityName;
    user user = new user();
    @When("the user select edit city {string}")
    public void the_user_select_edit_city(String string) {
        x = string;
    }

    @When("the user updates the {string} field with a new city name")
    public void the_user_updates_the_field_with_a_new_city_name(String string) {
        NewCityName = string;

    }

    @Then("the system saves the new city")
    public void the_system_saves_the_new_city() {
       assertTrue("Success",obj.users.get(0).setCity(NewCityName));
    }

    @When("the user select edit street {string}")
    public void the_user_select_edit_street(String string) {
        x=string;
    }
    String NewStreetName;
    @When("the user updates the {string} field with a new street name")
    public void the_user_updates_the_field_with_a_new_street_name(String string) {
        NewStreetName = string;
    }

    @Then("the system saves the new street")
    public void the_system_saves_the_new_street() {
        assertTrue("Success",obj.users.get(0).setStreet(NewStreetName));
    }
    String NewphonwNumber;
    @When("the user select edit home number {string}")
    public void the_user_select_edit_home_number(String string) {
        x=string;
    }

    @When("the user updates the {string} field with a new home number name")
    public void the_user_updates_the_field_with_a_new_home_number_name(String string) {
        NewphonwNumber = string;
    }

    @Then("the system saves the new home number")
    public void the_system_saves_the_new_home_number() {
        assertTrue("Success",obj.users.get(0).setHomeNum(NewphonwNumber));

    }
    String NewPhoneNumber;
    @When("the user select edit phone number {string}")
    public void the_user_select_edit_phone_number(String string) {
       x=string;
    }

    @When("the user updates the {string} field with a new phone number name")
    public void the_user_updates_the_field_with_a_new_phone_number_name(String string) {
        NewPhoneNumber = string;
    }

    @Then("the system saves the new phone number")
    public void the_system_saves_the_new_phone_number() {
        assertTrue("Success",obj.users.get(0).setPhoneNum(NewPhoneNumber));
    }

    @When("the user select edit email {string}")
    public void the_user_select_edit_email(String string) {
        x=string;
    }
    String NewEmail;
    @When("the user updates the {string} field with a new email name")
    public void the_user_updates_the_field_with_a_new_email_name(String string) {
        NewEmail = string;
    }


    @Then("the system saves the new email")
    public void theSystemSavesTheNewEmail() {
        assertTrue("Success",obj.users.get(0).setEmail(NewEmail));

    }
    @When("the user select edit password {string}")
    public void the_user_select_edit_password(String string) {
        x=string;
    }
    String NewPassword;
    String OldPassword;
    String ConfirmPassword;
    @When("the user enters old password  {string} new password {string} and confirm password {string}")
    public void the_user_enters_old_password_new_password_and_confirm_password(String string, String string2, String string3) {
        OldPassword = string;
        NewPassword = string2;
        ConfirmPassword = string3;
    }
    @Then("the system validates the inputs and sets the new password")
    public void theSystemValidatesTheInputsAndSetsTheNewPassword() {
        assertTrue("Success",obj.getUserManager().isValidPassword(OldPassword,NewPassword,ConfirmPassword,obj.users.get(0)));
        assertTrue("Success",obj.users.get(0).setPassword(NewPassword));
    }



}