package sweet.dev;

import menus.PrettyFormatter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipeManager {
    private LinkedList<Recipe> Validatedrecipes = new LinkedList<Recipe>();
    private  LinkedList<Recipe> NotValidatedRecipes  = new LinkedList<Recipe>();
    private  List <user> users ;
    private static final Logger logger = Logger.getLogger(RecipeManager.class.getName());

    public RecipeManager(  List <user> users) {
        this.users = users;
        logger.setUseParentHandlers(false);

        Handler[] handlers = logger.getHandlers();
        for (Handler handler : handlers) {
            logger.removeHandler(handler);
        }

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.addHandler(consoleHandler);

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
    public boolean filterRecipesByAllergies(List<Recipe> recipes, Set<String> allergies) {
        List<Recipe> filteredRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            boolean hasAllergy = false;
            for (String allergen : allergies) {
                if (recipe.getIngredients().toLowerCase().contains(allergen.toLowerCase())) {
                    hasAllergy = true;
                    break;
                }
            }
            if (!hasAllergy) {
                filteredRecipes.add(recipe);
            }
        }

        logger.info("\nHere are the recipes that do NOT contain any of your allergies:\n");
        if (filteredRecipes.isEmpty()) {
            logger.info("Unfortunately, no recipes match your dietary restrictions.\n");
        } else {
            for (Recipe recipe : filteredRecipes) {
                logger.info(recipe.toString() + "\n");
            }
        }

        return true;
    }

    public boolean filterRecipesByDietaryRestrictions(List<Recipe> recipes, String ingredient) {
        List<Recipe> filteredRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            String[] ingredients = recipe.getIngredients().split(" ");

            for (String recipeIngredient : ingredients) {
                if (recipeIngredient.equalsIgnoreCase(ingredient)) {
                    filteredRecipes.add(recipe);
                    break;
                }
            }
        }
        logger.info("\nHere are the recipes that match your dietary restrictions:\n");
        if (filteredRecipes.isEmpty()) {
            logger.info("Unfortunately, no recipes match your dietary restrictions.\n");
        } else {
            for (Recipe recipe : filteredRecipes) {
                logger.info(recipe.toString() + "\n");
            }
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
    public boolean ShowAllRecipes() {
        logger.info("\n╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        logger.info("║                              ✨ All Recipes ✨                                                               ║");
        logger.info("╠═══════╦══════════════════╦══════════════════════════════════╦═════════════════════╦═══════════════════════╣");
        logger.info("║  ID   ║    Recipe Name   ║           Feedbacks              ║    Ingredients      ║       Publisher       ║");
        logger.info("╠═══════╬══════════════════╬══════════════════════════════════╬═════════════════════╬═══════════════════════╣");

        for (Recipe recipe : Validatedrecipes) {
            String feedbacksFormatted = formatFeedbacks(recipe.getFeedbacks());
            logger.info(String.format("║ %-5s ║ %-16s ║ %-32s ║ %-19s ║ %-21s ║",
                    recipe.getId(),
                    truncate(recipe.getName(), 16),
                    truncate(feedbacksFormatted, 32),
                    truncate(recipe.getIngredients(), 19),
                    truncate(recipe.getPublisher(), 21)
            ));
        }

        logger.info("╚═══════╩══════════════════╩══════════════════════════════════╩═════════════════════╩═══════════════════════╝\n");
        return true;
    }

    private String truncate(String input, int maxLength) {
        if (input.length() > maxLength) {
            return input.substring(0, maxLength - 3) + "...";
        }
        return input;
    }

    private String formatFeedbacks(List<String> feedbacks) {
        if (feedbacks == null || feedbacks.isEmpty()) {
            return "No feedbacks";
        }

        StringBuilder formattedFeedbacks = new StringBuilder();
        int index = 0;
        for (String feedback : feedbacks) {
            formattedFeedbacks.append(index).append(". ").append(feedback).append("\n");
            index++;
        }
        return formattedFeedbacks.toString();
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
