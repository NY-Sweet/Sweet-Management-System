package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import sweet.dev.managers.LoginManager;
import sweet.dev.managers.SupplierManager;
import sweet.dev.managers.UserManager;
import sweet.dev.models.supplier;
import sweet.dev.system.SweetApp;

import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ownerReports {

    public SweetApp obj;
    private supplier owner;
private boolean success;
    public LoginManager loginManager;
    public UserManager userManager;
    public SupplierManager supplierManager;

    private static final Logger logger = Logger.getLogger(ownerReports.class.getName());
    public ownerReports(SweetApp obj) {
        super();
        this.obj=obj;

        userManager = obj.getUserManager();
        supplierManager = obj.getSupplierManager();
        loginManager = obj.getLoginManager();

    }

    private void assertion() {
        assertTrue(owner.getOrderManager().isSuccessOperation());
    }



    @Given("the owner in Reports page")
    public void the_owner_in_reports_page() {
        obj.setOwnerInReprotsPage(true);

    }



    @When("the owner {string} enters the option to view Daily Sales and Profits")
    public void the_owner_enters_the_option_to_view_daily_sales_and_profits(String string) {
      owner=supplierManager.getTheSupplier(string);
    }
    @When("set date {int}-{int}-{int}")
    public void set_date(Integer int1, Integer int2, Integer int3) {
      owner.getOrderManager().viewDailySalesAndProfits(int1,int2,int3);
    }
    @Then("View Daily Sales and Profits successfully")
    public void view_daily_sales_and_profits_successfully() {
        assertion();
    }

    @When("the owner {string} enters the option to View Monthly Sales and Profits")
    public void the_owner_enters_the_option_to_view_monthly_sales_and_profits(String string) {
        owner=supplierManager.getTheSupplier(string);
    }
    @When("set date month and year {int}-{int}")
    public void set_date_month_and_year(Integer month, Integer year) {
        owner.getOrderManager().viewMonthlySalesAndProfits(month,year);

    }
    @Then("View Monthly Sales and Profits successfully")
    public void view_monthly_sales_and_profits_successfully() {
        assertion();
    }


    @When("the owner {string} enters the option to View the Best Selling Products")
    public void the_owner_enters_the_option_to_view_the_best_selling_products(String string) {
        owner=supplierManager.getTheSupplier(string);
        owner.getOrderManager().showBestProducts();

    }
    @Then("View the Best Selling Products successfully")
    public void view_the_best_selling_products_successfully() {
        assertion();

    }

    @When("the owner {string} selects the financial reports set year {int}")
    public void the_owner_selects_the_financial_reports_set_year(String string, Integer int1) {
        owner=supplierManager.getTheSupplier(string);
        success=owner.getOrderManager().showFinancialReports(int1);
    }
    @Then("the owner financial reports will display all shops' financial details")
    public void the_owner_financial_reports_will_display_all_shops_financial_details() {
      assertTrue(success);
    }

}
