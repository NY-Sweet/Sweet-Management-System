package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import sweet.dev.*;

import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ownerReports {

    public SweetApp obj;
    private supplier owner;
    private boolean  isValidOption;

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
        assertTrue(isValidOption);
    }


    @When("the owner enters the option to Reports page {int}")
    public void theOwnerEntersTheOptionToReportsPage(Integer option) {

        obj.ownerOptionToReportsPage(option);
    }
    @Then("the owner enters the Reports page")
    public void the_owner_enters_the_reports_page() {
     assertTrue(obj.isOwnerOptionToReportsPage());
    }

    @Then("the owner doesn't enter the Reports page")
    public void the_owner_doesn_t_enter_the_reports_page() {
        assertFalse(obj.isOwnerOptionToReportsPage());
    }


    @Given("the owner in Reports page")
    public void the_owner_in_reports_page() {
        obj.setOwnerInReprotsPage(true);

    }



    @When("the owner {string} enters the option to view Daily Sales and Profits {string}")
    public void the_owner_enters_the_option_to_view_daily_sales_and_profits(String string, String string2) {
      isValidOption=obj.ownerViewDailySales(string2);
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

    @When("the owner {string} enters the option to View Monthly Sales and Profits {string}")
    public void the_owner_enters_the_option_to_view_monthly_sales_and_profits(String string, String string2) {
        isValidOption=obj.ownerViewMonthlySales(string2);
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


    @When("the owner {string} enters the option to View the Best Selling Products {string}")
    public void the_owner_enters_the_option_to_view_the_best_selling_products(String string,String string2) {
        isValidOption=obj.ownerViewBestSellingProducts(string2);
        owner=supplierManager.getTheSupplier(string);
        owner.getOrderManager().showBestProducts();

    }
    @Then("View the Best Selling Products successfully")
    public void view_the_best_selling_products_successfully() {
        assertion();

    }

    @When("the owner enters the option to exit reports page  {string}")
    public void the_owner_enters_the_option_to_exit_reports_page(String string) {
        isValidOption=obj.exitOwnerReportPage(string);
    }
    @Then("exit owner reports page and go back to owner page")
    public void exitOwnerReportsPageAndGoBackToOwnerPage() {
        assertTrue( isValidOption);
    }


    @When("the owner enters an invalid option in reports page {string}")
    public void the_owner_enters_an_invalid_option_in_reports_page(String string) {

        isValidOption=obj.invalidOptionInReportsPage(string);

    }
    @Then("invalid option in report page and  message is displayed")
    public void invalid_option_in_report_page_and_message_is_displayed() {
       assertTrue( isValidOption);
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
