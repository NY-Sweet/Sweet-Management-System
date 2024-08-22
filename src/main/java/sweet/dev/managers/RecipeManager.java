package sweet.dev.managers;

import main.format.PrettyFormatter;
import sweet.dev.models.Recipe;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecipeManager {
    private LinkedList<Recipe> validatedRecipes = new LinkedList<>();
    private  LinkedList<Recipe> notValidatedRecipes = new LinkedList<>();
    private static final Logger logger = Logger.getLogger(RecipeManager.class.getName());

    public RecipeManager(  ) {
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
        notValidatedRecipes.add(recipe);
        logger.setLevel(Level.INFO);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.INFO);
        logger.warning("The recipe has not been validated ");

    }


    public  List<Recipe> getValidatedRecipes() {
        return validatedRecipes;
    }
    public  List<Recipe> getnotValidatedRecipes() {
        return notValidatedRecipes;
    }
    public boolean validateRecipe(Recipe recipetovaledate){
        validatedRecipes.add(recipetovaledate);
        notValidatedRecipes.remove(recipetovaledate);
        return true;
    }

    public List <Recipe> findRecipesByName(String searchTerm) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : validatedRecipes) {
            if (recipe.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(recipe);
            }
        }
        return results;

    }
    public boolean filterRecipesByAllergies(List<Recipe> recipes, Set<String> allergies) {
        List<Recipe> filteredRecipes = new ArrayList<>();

        Set<String> normalizedAllergies = new HashSet<>();
        for (String allergen : allergies) {
            normalizedAllergies.add(allergen.trim().toLowerCase());
        }

        for (Recipe recipe : recipes) {
            boolean hasAllergy = false;

            String[] ingredients = recipe.getIngredients().split(",\\s*");
            for (String ingredient : ingredients) {
                if (normalizedAllergies.contains(ingredient.trim().toLowerCase())) {
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
            return false;
        } else {
            print(filteredRecipes);
            return true;
        }


    }


    public boolean filterRecipesByDietaryRestrictions(List<Recipe> recipes, String ingredient) {
        List<Recipe> filteredRecipes = new ArrayList<>();
        String normalizedIngredient = ingredient.trim().toLowerCase();

        for (Recipe recipe : recipes) {
            String[] ingredients = recipe.getIngredients().split(",\\s*");

            for (String recipeIngredient : ingredients) {
                if (recipeIngredient.trim().equalsIgnoreCase(normalizedIngredient)) {
                    filteredRecipes.add(recipe);
                    break;
                }
            }
        }
        logger.info("\nHere are the recipes that match your dietary restrictions:\n");
        if (filteredRecipes.isEmpty()) {
            logger.info("Unfortunately, no recipes match your dietary restrictions.\n");
            return false;
        } else {
            return print(filteredRecipes);
        }

    }

    private boolean print(List<Recipe> filteredRecipes) {
        if (filteredRecipes.isEmpty()) {
            logger.info("Unfortunately, no recipes match your dietary restrictions.");
        }

        logger.info("\nHere are the recipes that match your dietary restrictions:\n");
        for (Recipe recipe : filteredRecipes) {
            String formattedRecipe = formatRecipe(recipe);
            logger.info(formattedRecipe);
        }

        return true;
    }

    private String formatRecipe(Recipe recipe) {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe ID: ").append(recipe.getId()).append("\n")
                .append("Recipe Name: ").append(recipe.getName()).append("\n")
                .append("Ingredients # ").append(recipe.getNumberOfIngredients()).append(recipe.getIngredients()).append("\n")
                .append("Steps: ").append(recipe.getSteps()).append("\n")
                .append("Feedbacks: ").append(formatFeedbacks(recipe.getFeedbacks())).append("\n")
                .append("Publisher: ").append(recipe.getPublisher()).append("\n")
                .append("Total Feedbacks: ").append(recipe.getFeedbacks().size()).append("\n")
                .append("─────────────────────────────────────────\n");

        return sb.toString();
    }

    private String formatFeedbacks(List<String> feedbacks) {
        if (feedbacks.isEmpty()) {
            return "No feedbacks available.";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < feedbacks.size(); i++) {
            sb.append(feedbacks.get(i));
            if (i != feedbacks.size() - 1) {
                sb.append("; ");
            }
        }
        return sb.toString();
    }




    public Recipe searchRecipeById(int id) {
        for (Recipe recipe : this.validatedRecipes) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        return null;
    }
    public Recipe searchRecipeByIdNotvalidated(int id) {
        for (Recipe recipe : this.notValidatedRecipes) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        return null;
    }
    public boolean showAllRecipes() {
        return print(validatedRecipes);
    }


    public boolean deleteRecipeByIndex(int index){
        this.validatedRecipes.remove(index);
        return true ;

    }
    public boolean deleteaFeedofaRecipe(int recipeid , int feedbackid){
        if(recipeid< validatedRecipes.size() && recipeid >=0) {
         List<String> feedBacks= validatedRecipes.get(recipeid).getFeedbacks();
          if(feedBacks.size()>feedbackid && feedbackid >=0) {
              validatedRecipes.get(recipeid).getFeedbacks().remove(feedbackid);
              return true;
          }
            System.out.println("l"+recipeid+feedbackid);
          return false;
        }
        System.out.println("n"+recipeid+feedbackid+validatedRecipes.size());

        return false;

    }
}
