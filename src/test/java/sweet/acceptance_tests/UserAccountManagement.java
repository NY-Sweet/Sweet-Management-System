package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.system.SweetApp;
import sweet.dev.models.User;

import static org.junit.Assert.*;

public class UserAccountManagement {
    SweetApp obj = new SweetApp();
    String x;
    String newCityName;
    User user1 = new User();
    @When("the user select edit city {string}")
    public void the_user_select_edit_city(String string) {
        x = string;
    }

    @When("the user updates the {string} field with a new city name")
    public void the_user_updates_the_field_with_a_new_city_name(String string) {
        newCityName = string;

    }

    @Then("the system saves the new city")
    public void the_system_saves_the_new_city() {
        String oldCity=obj.getUsers().get(0).getAdress().getStreet();
        obj.getUsers().get(0).getAdress().setCity(newCityName);
        assertNotEquals(oldCity,obj.getUsers().get(0).getAdress().getCity());

    }

    @When("the user select edit street {string}")
    public void the_user_select_edit_street(String string) {
        x=string;
    }
    String newStreetName;
    @When("the user updates the {string} field with a new street name")
    public void the_user_updates_the_field_with_a_new_street_name(String string) {
        newStreetName = string;
    }

    @Then("the system saves the new street")
    public void the_system_saves_the_new_street() {
        String oldStreet=obj.getUsers().get(0).getAdress().getStreet();
       obj.getUsers().get(0).getAdress().setStreet(newStreetName);
        assertNotEquals(oldStreet,obj.getUsers().get(0).getAdress().getStreet());
    }
    String newHomeNumber;
    @When("the user select edit home number {string}")
    public void the_user_select_edit_home_number(String string) {
        x=string;
    }

    @When("the user updates the {string} field with a new home number name")
    public void the_user_updates_the_field_with_a_new_home_number_name(String string) {
        newHomeNumber = string;
    }

    @Then("the system saves the new home number")
    public void the_system_saves_the_new_home_number() {
        String oldHome=obj.getUsers().get(0).getAdress().getHomeNum();
       obj.getUsers().get(0).getAdress().setHomeNum(newHomeNumber);
        assertNotEquals(oldHome,obj.getUsers().get(0).getAdress().getHomeNum());
    }
    String newPhoneNumber;
    @When("the user select edit phone number {string}")
    public void the_user_select_edit_phone_number(String string) {
       x=string;
    }

    @When("the user updates the {string} field with a new phone number name")
    public void the_user_updates_the_field_with_a_new_phone_number_name(String string) {
        newPhoneNumber = string;
    }

    @Then("the system saves the new phone number")
    public void the_system_saves_the_new_phone_number() {
        String oldPhone=obj.getUsers().get(0).getPhoneNum();
        assertTrue("Success",obj.getUsers().get(0).setPhoneNum(newPhoneNumber));
        assertNotEquals(oldPhone,obj.getUsers().get(0).getPhoneNum());
    }

    @When("the user select edit email {string}")
    public void the_user_select_edit_email(String string) {
        x=string;
    }
    String newEmail;
    @When("the user updates the {string} field with a new email name")
    public void the_user_updates_the_field_with_a_new_email_name(String string) {
        newEmail = string;
    }


    @Then("the system saves the new email")
    public void theSystemSavesTheNewEmail() {
        assertTrue("Success",obj.getUsers().get(0).setEmail(newEmail));

    }
    @When("the user select edit password {string}")
    public void the_user_select_edit_password(String string) {
        x=string;
    }
    String newPassword;
    String oldPassword;
    String confirmPassword;
    @When("the user enters old password  {string} new password {string} and confirm password {string}")
    public void the_user_enters_old_password_new_password_and_confirm_password(String string, String string2, String string3) {
        oldPassword = obj.getUsers().get(0).getPassword();
        newPassword = string2;
        confirmPassword = string3;

    }
    @Then("the system validates the inputs and sets the new password")
    public void theSystemValidatesTheInputsAndSetsTheNewPassword() {
        assertTrue("Success",obj.getUserManager().isValidPassword(oldPassword, newPassword, confirmPassword, obj.getUsers().get(0)));
       obj.getUsers().get(0).setPassword(newPassword);

    }
    boolean areEqual;

    @When("the user enters  invalid inputs old password  {string} new password {string} and confirm password {string}")
    public void the_user_enters_invalid_inputs_old_password_new_password_and_confirm_password(String string, String string2, String string3) {
        oldPassword = obj.getUsers().get(0).getPassword();
        newPassword = string2;
        confirmPassword = string3;
        if (oldPassword.equals(newPassword)){
            areEqual=true;
        }
    }

    @Then("the system validates the inputs and password change is not valid")
    public void the_system_validates_the_inputs_and_password_change_is_not_valid() {
        assertFalse(obj.getUserManager().isValidPassword(oldPassword, newPassword, confirmPassword, obj.getUsers().get(0)));

    }



}