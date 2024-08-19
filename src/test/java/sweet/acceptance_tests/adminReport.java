package sweet.acceptance_tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.managers.AdminManager;
import sweet.dev.system.SweetApp;
import static org.junit.Assert.assertTrue;

public class adminReport {

    public SweetApp obj;
    private boolean  success;



    private AdminManager adminManager;

    public adminReport(SweetApp obj) {
        super();
        this.obj=obj;
        adminManager=obj.getAdminManager();

    }

    @Given("the admin {string} is on the Reports page")
    public void the_admin_is_on_the_reports_page(String string) {
        adminManager.getTheAdmin(string);

    }
    @When("the admin selects the financial reports set year {int}")
    public void the_admin_selects_the_financial_reports_set_year(Integer int1) {

        success= adminManager.showAnnualReport(int1);
    }
    @Then("the reports will display all shops' financial details")
    public void the_reports_will_display_all_shops_financial_details() {
       assertTrue(success);
    }

    @When("the admin selects the best-selling products in each store")
    public void the_admin_selects_the_best_selling_products_in_each_store() {
        success=adminManager.showBestSellingProducts();
    }
    @Then("the reports will display best-selling products in each store")
    public void the_reports_will_display_best_selling_products_in_each_store() {
        assertTrue(success);
    }

    @When("the admin selects the statistics on registered users by City")
    public void the_admin_selects_the_statistics_on_registered_users_by_city() {
        success=adminManager.showStatisticsOnRegisteredUsersByCity();
    }
    @Then("the reports will display statistics on registered users by City")
    public void the_reports_will_display_statistics_on_registered_users_by_city() {
        assertTrue(success);

    }


}
