package sweet.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.*;

import static org.junit.Assert.*;


public class login {

    public SweetApp obj;
    private LoginManager loginManager;
    private UserManager userManager;
    private SupplierManager supplierManager;
    private String username;
    private String newPassword;
    private String resetToken;


    public login(SweetApp obj) {
        super();
        this.obj=obj;

        userManager = obj.getUserManager();
        userManager.addUser("haya","123456","","","","","","u");
        supplierManager = obj.getSupplierManager();
        loginManager = obj.getLoginManager();

    }

    @Given("I am not in sweet management system")
    public void i_am_not_in_sweet_management_system() {
        obj.iAmNotInSystem(obj);
    }

    @When("set username {string} and password {string}")
    public void set_username_and_password(String username, String password) {
        loginManager.setUsernameAndPasswordFromSystem(username, password);
    }

    @Then("login succeed")
    public void login_succeed() {
        assertTrue("Login should succeed", loginManager.isValidation());
    }

    @When("set invalid username {string} and password {string}")
    public void set_invalid_username_and_password(String username, String password) {
        loginManager.setUsernameAndPasswordFromSystem(username, password);
    }

    @Then("login failed")
    public void login_failed() {
        assertFalse("Login should fail", loginManager.isValidation());
    }

    @When("set valid username {string} and invalid password {string}")
    public void set_valid_username_and_invalid_password(String username, String password) {
        loginManager.setUsernameAndPasswordFromSystem(username, password);
    }

    @When("set empty username {string} and valid password {string}")
    public void set_empty_username_and_valid_password(String username, String password) {
        loginManager.setEmptyUsernameAndPasswordFromSystem(username, password);
    }

    @When("set valid username {string} and empty password {string}")
    public void set_valid_username_and_empty_password(String username, String password) {
        loginManager.setUsernameAndEmptyPasswordFromSystem(username, password);
    }

    @When("set valid username {string} and  password {string}")
    public void set_valid_username_and_password(String username, String forget) {
        loginManager.validUsernameAndForgetPassword(username,forget);
    }

    @Then("take new password {string}")
    public void take_new_password(String newPassword) {
        loginManager.updatePassword(newPassword);
    }

    @Given("I don't have an account")
    public void i_don_t_have_an_account() {
        obj.setLogged(false);
    }

    @When("set new username {string}, password {string}, city={string},street={string},home number={string}, phone number={string} , email={string} and role={string}")
    public void set_new_username_password_city_street_home_number_phone_number_email_and_role(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role) {
        userManager.createAccountForUser(userName,password,city,street,homeNum,phneNum,email,role);
    }

    @When("if the details are valid, the system saves the new user details")
    public void if_the_details_are_valid_the_system_saves_the_new_user_details() {
        if (userManager.isUserCreated()) {
            System.out.println("User details are valid and saved.");
        } else {
            System.out.println("User details are not valid and were not saved.");
        }
    }

    @Then("user createed succeed")
    public void user_createed_succeed() {
        assertTrue("New user successfully created ",userManager.isUserCreated());

    }
    @Then("owner created succeed")
    public void owner_created_succeed() {
        assertTrue("New supplier successfully created ",supplierManager.isSupplierCreated());
    }

    @When("set new username {string}, password {string}, city={string},street={string},home number={string}, phone number={string} , email={string}  role={string}  shop name {string} and employee number {int}")
    public void set_new_username_password_city_street_home_number_phone_number_email_role_shop_name_and_employee_number6(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role, String shopName, int emplyeeNum) {
        supplierManager.createAccountForSupplier(userName,password,city,street,homeNum,phneNum,email,role,shopName,emplyeeNum);

    }

}
