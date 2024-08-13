package sweet.acceptance_tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet.dev.Recipe;
import sweet.dev.SweetApp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeManage {

    SweetApp obj = new SweetApp();
    Recipe Recipetosearch;
    Recipe NewRecipe;
    String Dtr;
    String recipetosearch ;
    String Content;

    @When("the user {string} fills in the recipe details including name {string}, number of ingredients {int}, ingredients {string}, steps {string}")
    public void the_user_fills_in_the_recipe_details_including_name_number_of_ingredients_ingredients_steps(String string, String string2, int string3, String string4, String string5) {
        NewRecipe = new Recipe(string2,string3,string4,string5,string);
        obj.getRecipemanager().postRecipe(NewRecipe);

    }

    @Then("the system validates the inputs")
    public void the_system_validates_the_inputs() {
        Recipe NotValidated= obj.getRecipemanager().getnotValidatedRecipes().get(0);
        assertTrue("Succeed", obj.getRecipemanager().ValidateRecipe(NotValidated));

    }

    @Then("if the details are valid, the recipe is created successfully")
    public void if_the_details_are_valid_the_recipe_is_created_successfully() {
       assertEquals(NewRecipe,obj.getRecipemanager().searchRecipes(NewRecipe.getName()).get(0));
    }

    @Then("a  message is displayed")
    public void a_message_is_displayed() {
        obj.Messageaftervaledationoftherecipe();
        assertTrue("success",obj.isMessageDisplayedforVlidationaRecipe());
        obj.clearMessageDisplayedAfterRecipeValidation ();
        obj.getRecipemanager().ValidateRecipe(NewRecipe);
    }

    @When("the user select Browse Recipe")
    public void theUserSelectBrowseRecipe() {
        //obj.chooseFromUserRecipePage(2);
    }

    @Then("Display all recipes from all users")
    public void display_all_recipes_from_all_users() {
        assertTrue("succeed", obj.getRecipemanager().ShowAllRecipes());

    }

    @When("the user enter the {string}")
    public void the_user_enter_the(String string) {

        Dtr= string ;
    }

    @Then("Display all recipes match")
    public void display_all_recipes_match() {
        assertTrue("succeed",obj.RecipoeTosearch(Dtr));
    }

    @Then("Display all recipes based on allergies")
    public void display_all_recipes_based_on_allergies() {
        Set<String> allergies = new HashSet<String>();
        allergies.add(Dtr);
        assertTrue("succeed", obj.getRecipemanager().filterRecipesByAllergies(obj.getRecipemanager().getValidatedRecipes(),allergies));
    }

    @Then("Display all recipes based on dietary")
    public void display_all_recipes_based_on_dietary() {
        String Dietray = new String();
        Dietray = "Milk";
        assertTrue("succeed", obj.getRecipemanager().filterRecipesByDietaryRestrictions(obj.getRecipemanager().getValidatedRecipes(), Dietray));

    }



    @When("the user enters the recipe Name {string} and feedback content {string}")
    public void theUserEntersTheRecipeNameAndFeedbackContent(String string, String string2) {
        recipetosearch = string;
        Content = string2;
    }

    @Then("the feedback is sent successfully")
    public void the_feedback_is_sent_successfully() {
        Recipe RE1 =  new Recipe("cake",3,"egge milk flour","mix","haya");
        obj.getRecipemanager().postRecipe(RE1);
        obj.getRecipemanager().ValidateRecipe(RE1);
        assertTrue("Succeed",obj.getRecipemanager().searchRecipes("cake").get(0).addFeedback(Content) );
    }



}
