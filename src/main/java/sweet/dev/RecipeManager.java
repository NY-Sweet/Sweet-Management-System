package sweet.dev;

import menus.PrettyFormatter;

import java.util.*;
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
        } else {
            print(filteredRecipes);
        }

        return true;
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
        } else {
            return print(filteredRecipes);
        }
        return true;
    }

    private boolean print(List<Recipe> filteredRecipes) {
        if (filteredRecipes.isEmpty()) {
            logger.info("Unfortunately, no recipes match your dietary restrictions.");
            return true;
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
                .append("Ingredients: ").append(recipe.getIngredients()).append("\n")
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
        return print(Validatedrecipes);
    }


    public boolean deleteRecipeByIndex(int index){
        this.Validatedrecipes.remove(index);
        return true ;

    }
    public boolean DeleteaFeedofaRecipe(int recipeid , int feedbackid){
        if(recipeid<Validatedrecipes.size()) {
          LinkedList<String> feedBacks= Validatedrecipes.get(recipeid).feedbacks;
          if(feedBacks.size()>feedbackid) {
              Validatedrecipes.get(recipeid).feedbacks.remove(feedbackid);
              return true;
          }
          return false;
        }
       return false;

    }
}
