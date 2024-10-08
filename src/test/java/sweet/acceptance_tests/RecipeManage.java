package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.models.Recipe;
import sweet.dev.system.SweetApp;

import java.util.*;

import static org.junit.Assert.*;

public class RecipeManage {

    SweetApp obj = new SweetApp();
    Recipe newRecipe;
    String dtr;
    String recipeToSearch;
    String string;

    @When("the user {string} fills in the recipe details including name {string}, number of ingredients {int}, ingredients {string}, steps {string}")
    public void the_user_fills_in_the_recipe_details_including_name_number_of_ingredients_ingredients_steps(String string, String string2, int string3, String string4, String string5) {
        newRecipe = new Recipe(string2,string3,string4,string5,string);
        obj.getRecipeManager().postRecipe(newRecipe);

    }

    @Then("the system validates the inputs")
    public void the_system_validates_the_inputs() {
        Recipe notValidated= obj.getRecipeManager().getnotValidatedRecipes().get(0);
        assertTrue("Succeed", obj.getRecipeManager().validateRecipe(notValidated));

    }

    @Then("if the details are valid, the recipe is created successfully")
    public void if_the_details_are_valid_the_recipe_is_created_successfully() {
       assertEquals(obj.getRecipeManager().findRecipesByName(newRecipe.getName()).get(0).getName(), newRecipe.getName());
    }

    @Then("a  message is displayed")
    public void a_message_is_displayed() {
        obj.messageAfterValedationOfTheRecipe();
        assertTrue("success",obj.isMessageDisplayedforVlidationaRecipe());
        obj.clearMessageDisplayedAfterRecipeValidation ();
        obj.getRecipeManager().validateRecipe(newRecipe);
    }

    @When("the user select Browse Recipe")
    public void theUserSelectBrowseRecipe() {
        //the user select Browse Recipe
    }

    @Then("Display all recipes from all users")
    public void display_all_recipes_from_all_users() {
        assertTrue("succeed", obj.getRecipeManager().showAllRecipes());

    }

    @When("the user enter the {string}")
    public void the_user_enter_the(String string) {
        dtr = string ;
    }

    @Then("Display all recipes match")
    public void display_all_recipes_match() {
      //  Display all recipes match
    }

    @Then("Display all recipes based on allergies")
    public void display_all_recipes_based_on_allergies() {
        Set<String> allergies = new HashSet<String>();
        allergies.add(dtr);
        assertTrue("succeed", obj.getRecipeManager().filterRecipesByAllergies(obj.getRecipeManager().getValidatedRecipes(),allergies));
    }

    @Then("Display all recipes based on dietary")
    public void display_all_recipes_based_on_dietary() {
        assertTrue("succeed", obj.getRecipeManager().filterRecipesByDietaryRestrictions(obj.getRecipeManager().getValidatedRecipes(), dtr));

    }

    @When("the user enter the {string} and not found any for him")
    public void the_user_enter_the_and_not_found_any_for_him(String string) {
        dtr = string;
    }


    @Then("Print Nothing found suitable for you")
    public void print_nothing_found_suitable_for_you() {
        assertFalse( obj.getRecipeManager().filterRecipesByDietaryRestrictions(obj.getRecipeManager().getValidatedRecipes(), dtr));

    }



    @When("the user enters the recipe Name {string} and feedback content {string}")
    public void theUserEntersTheRecipeNameAndFeedbackContent(String string, String string2) {
        recipeToSearch = string;
        this.string = string2;
    }

    @Then("the feedback is sent successfully")
    public void the_feedback_is_sent_successfully() {
        Recipe re1 =  new Recipe("Pancake",3,"Milk, flour, oil","Mix them all","haya");

        obj.getRecipeManager().postRecipe(re1);
        obj.getRecipeManager().validateRecipe(re1);
        assertTrue("Succeed",obj.getRecipeManager().findRecipesByName("cake").get(0).addFeedback(string) );
    }



}
