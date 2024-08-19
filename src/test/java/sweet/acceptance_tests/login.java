package sweet.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.managers.AdminManager;
import sweet.dev.managers.LoginManager;
import sweet.dev.managers.SupplierManager;
import sweet.dev.managers.UserManager;
import sweet.dev.models.Admin;
import sweet.dev.models.Adress;
import sweet.dev.models.Supplier;
import sweet.dev.models.User;
import sweet.dev.system.SweetApp;

import static org.junit.Assert.*;


public class login {

    public SweetApp obj;
    private LoginManager loginManager;
    private UserManager userManager;
    private SupplierManager supplierManager;
    private AdminManager adminManager;


    public login(SweetApp obj) {
        super();
        this.obj=obj;
        adminManager=obj.getAdminManager();
        userManager = obj.getUserManager();
        userManager.addUser(new User("haya","123456","","",new Adress("","",""),"u"));
        supplierManager = obj.getSupplierManager();
        loginManager = obj.getLoginManager();

    }

    @Given("I am not in sweet management system")
    public void i_am_not_in_sweet_management_system() {
       // I am not in sweet management system
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
       // I don't have an account
    }

    @When("set new username {string}, password {string}, city={string},street={string},home number={string}, phone number={string} , email={string} and role={string}")
    public void set_new_username_password_city_street_home_number_phone_number_email_and_role(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role) {
        userManager.createAccountForUser(new User(userName,password,phneNum,email,new Adress(city,street,homeNum),role));
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

    @When("set existing username {string}, password {string}, city={string},street={string},home number={string}, phone number={string} , email={string} and role={string}")
    public void set_existing_username_password_city_street_home_number_phone_number_email_and_role(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role) {
        userManager.createAccountForUser(new User(userName,password,phneNum,email,new Adress(city,street,homeNum),role));

    }

    @Then("user failed to create account")
    public void user_failed_to_create_account() {
        assertFalse(userManager.isUserCreated());

    }

    @Then("owner created succeed")
    public void owner_created_succeed() {
        assertTrue("New supplier successfully created ",supplierManager.isSupplierCreated());
    }

    @When("set new username {string}, password {string}, city={string},street={string},home number={string}, phone number={string} , email={string}  role={string}  shop name {string} and employee number {int}")
    public void set_new_username_password_city_street_home_number_phone_number_email_role_shop_name_and_employee_number6(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role, String shopName, int emplyeeNum) {
        supplierManager.createAccountForSupplier(new Supplier(userName,password,phneNum,email,new Adress(city,street,homeNum),role,shopName,emplyeeNum));

    }

    @When("set existing username {string}, password {string}, city={string},street={string},home number={string}, phone number={string} , email={string}  role={string}  shop name {string} and employee number {int}")
    public void set_existing_username_password_city_street_home_number_phone_number_email_role_shop_name_and_employee_number(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role, String shopName, int emplyeeNum) {
        supplierManager.createAccountForSupplier(new Supplier(userName,password,phneNum,email,new Adress(city,street,homeNum),role,shopName,emplyeeNum));

    }

    @Then("owner failed to create")
    public void owner_failed_to_create() {
        assertFalse(supplierManager.isSupplierCreated());

    }

    @Given("a user has entered the username {string}")
    public void a_user_has_entered_the_username(String username) {
        loginManager.setEnteredUsername(username);
    }

    @When("the system retrieves the entered username")
    public void the_system_retrieves_the_entered_username() {
        //the system retrieves the entered username
    }

    @Then("the entered username should be {string}")
    public void the_entered_username_should_be(String expectedUsername) {
        assertEquals(expectedUsername, loginManager.getEnteredUsername());
    }
    int  setedRole;
    @Given("the user has the role {string} in the system")
    public void the_user_has_the_role_in_the_system(String role) {
        setedRole = Integer.parseInt(role);
        loginManager.setRoleInSys(Integer.valueOf(role));
    }

    @When("the system retrieves the user's role")
    public void the_system_retrieves_the_user_s_role() {
        setedRole=loginManager.getRoleInSys();
    }

    @Then("the role should be {string}")
    public void the_role_should_be(String expectedRole) {
        assertEquals(Integer.valueOf(expectedRole), loginManager.getRoleInSys());
    }
    Admin newAdmin;
    @When("Invalid Admin name {string}")
    public void invalid_admin_name(String string) {
       newAdmin =adminManager.getTheAdmin(string);
    }

    @Then("Invalid Name Message")
    public void invalid_name_message() {
       assertNull(newAdmin);
    }

}
