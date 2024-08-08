package sweet.dev;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Recipe {
    private int id;
    private String name;
    private int numberOfIngredients;
    private String ingredients;
    private String steps;
    private String Publisher ;
    public LinkedList<String> feedbacks;
    private static final Logger logger = Logger.getLogger(Recipe.class.getName());

    public Recipe(String name, int numberOfIngredients, String ingredients, String steps, String Publisher) {
        this.name = name;
        this.numberOfIngredients = numberOfIngredients;
        this.ingredients = ingredients;
        this.steps = steps;
        this.Publisher = Publisher;
        this.feedbacks = new LinkedList();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {this.id= id;}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumberOfIngredients() {
        return numberOfIngredients;
    }
    public void setNumberOfIngredients(int numberOfIngredients) {
        this.numberOfIngredients = numberOfIngredients;
    }
    public String getIngredients() {
        return ingredients;
    }
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public String getSteps() {
        return steps;
    }
    public void setSteps(String steps) {
        this.steps = steps;
    }
    public boolean validate() {
        boolean valid = true;
        //  I will Ask Noor About this part
        return valid;
    }
    public List<String> getFeedbacks() {
        return feedbacks;
    }
    public boolean addFeedback(String feedback) {
        feedbacks.add(feedback);
        return true;
    }

    @Override
    public String toString() {
        logger.info("Recipe Details:");
        logger.info("  Name: " + name);
        logger.info("  Number of Ingredients: " + numberOfIngredients);
        logger.info("  Ingredients: " + ingredients);
        logger.info("  Steps: " + steps);
        logger.info("  Publisher: " + Publisher);
        logger.info("  Feedbacks: " + feedbacks);
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberOfIngredients=" + numberOfIngredients +
                ", ingredients='" + ingredients + '\'' +
                ", steps='" + steps + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", feedbacks=" + feedbacks +
                '}';
    }
    public void showFeedbaks (){
        int i =0;
        for (final String feedback : feedbacks) {
         System.out.println(i+ "   "+feedback);
        }

    }}




