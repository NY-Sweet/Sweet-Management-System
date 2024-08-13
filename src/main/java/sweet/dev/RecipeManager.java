package sweet.dev;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipeManager {
    private LinkedList<Recipe> Validatedrecipes = new LinkedList<Recipe>();
    private  LinkedList<Recipe> NotValidatedRecipes  = new LinkedList<Recipe>();
    private  List <user> users ;
    private static final Logger logger = Logger.getLogger(RecipeManager.class.getName());

    public RecipeManager(  List <user> users) {
        this.users = users;
    }

    public  void postRecipe(Recipe recipe) {
        NotValidatedRecipes.add(recipe);
        logger.setLevel(Level.INFO);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.warning("The recipe has not been validated ");
    }


    public  List<Recipe> getValidatedRecipes() {
        return Validatedrecipes;
    }
    public  List<Recipe> getnotValidatedRecipes() {
        return NotValidatedRecipes;
    }
    public boolean ValidateRecipe(Recipe recipetovaledate){
        Validatedrecipes.add(recipetovaledate);
        NotValidatedRecipes.remove(recipetovaledate);
        return true;
    }

    public  LinkedList<Recipe> searchRecipes(String RecipeToSearch) {
        LinkedList<Recipe> results = new LinkedList<>();
        for (Recipe recipe : Validatedrecipes) {
            if (recipe.getName().toLowerCase().contains(RecipeToSearch.toLowerCase())) {
                results.add(recipe);
            }
        }
        return results;

    }
    public  boolean filterRecipesByAllergies(List<Recipe> recipes, Set<String> allergies) {
        List<Recipe> filteredRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            boolean matchesAllergies = true;
            for (String allergen : allergies) {
                if (recipe.getIngredients().contains(allergen)) {
                    matchesAllergies = false;
                    break;
                }}
            if (matchesAllergies) {
                filteredRecipes.add(recipe);
            }
        }
        for (Recipe recipe : filteredRecipes) {

            logger.info(recipe.toString());
        }
        return true;
    }
    public  boolean filterRecipesByDietaryRestrictions(List<Recipe> recipes, Set<String> dietaryRestrictions) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            boolean matchesDietaryRestrictions = true;
            for (String restriction : dietaryRestrictions) {
                if (!matchesDietaryRestriction(recipe, restriction)) {
                    matchesDietaryRestrictions = false;
                    break;
                }
            }
            if (matchesDietaryRestrictions) {
                filteredRecipes.add(recipe);
            }
        }
        for (Recipe recipe : filteredRecipes) {

            logger.info(recipe.toString());
        }
        return true;
    }


    private  boolean matchesDietaryRestriction(Recipe recipe, String restriction) {
        return !recipe.getIngredients().contains(restriction);
    }
    public Recipe searchRecipeById(int id) {
        for (Recipe recipe : this.Validatedrecipes) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        return null;
    }
    public Recipe searchRecipeByIdNotvalidated(int id) {
        for (Recipe recipe : this.NotValidatedRecipes) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        return null;
    }
    public boolean ShowAllRecipes (){
        logger.info("Recipe Id "+" "+" Recipe Name "+" "+"Feedbacks "+" "+"Ingrediants");
        for (Recipe recipe : Validatedrecipes) {

            logger.info(recipe.getId() + " " + recipe.getName()+" "+recipe.getFeedbacks()+" "+recipe.getIngredients());
        }
        return true;
    }
    public boolean deleteRecipeByIndex(int index){
        this.Validatedrecipes.remove(index);
        return true ;

    }
    public boolean DeleteaFeedofaRecipe(int recipeid , int feedbackid){

        Validatedrecipes.get(recipeid).feedbacks.remove(feedbackid);
        return true ;

    }
}
