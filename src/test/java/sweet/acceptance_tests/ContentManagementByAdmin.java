package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.Recipe;
import sweet.dev.SweetApp;

import static org.junit.Assert.assertTrue;

public class ContentManagementByAdmin {
    SweetApp obj = new SweetApp();

    int RecipeTodelete;
    Recipe addedre = new Recipe("Egg",3,"Egg salt Oil","Heat and mix",obj.users.get(0).getUserName());

    @When("the admin  choose recipe id to delete {int}")
    public void the_admin_choose_recipe_id_to_delete(Integer int1) {
        RecipeTodelete= int1;
        obj.getRecipemanager().postRecipe(addedre);
        obj.getRecipemanager().ValidateRecipe(addedre);
    }

    @Then("the recipe is deleted and a message is shown")
    public void the_recipe_is_deleted_and_a_message_is_shown() {
        assertTrue("Succeed",obj.getRecipemanager().deleteRecipeByIndex(RecipeTodelete));
    }

    int recipeid;
    int feedid;

    @When("the admin chooses a recipe to display its feedbacks by  its id {int} and FeedbackId {int}")
    public void the_admin_chooses_a_recipe_to_display_its_feedbacks_by_its_id_and_feedback_id(Integer int1, Integer int2) {
        obj.getRecipemanager().postRecipe(addedre);
        obj.getRecipemanager().ValidateRecipe(addedre);
        obj.getRecipemanager().getValidatedRecipes().get(0).addFeedback("what an easy recipe");
        obj.getRecipemanager().getValidatedRecipes().get(0).addFeedback("Excellent!!!");
        obj.getRecipemanager().ShowAllRecipes();
        recipeid= int1 ;
        obj.getRecipemanager().searchRecipeById(recipeid).showFeedbaks();
        feedid=int2;
    }

    @Then("the feedback is deleted")
    public void the_feedback_is_deleted() {
       assertTrue("Succeed",obj.getRecipemanager().DeleteaFeedofaRecipe(recipeid,feedid));
    }


}
