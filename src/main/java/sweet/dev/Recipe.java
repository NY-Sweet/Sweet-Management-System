package sweet.dev;
import menus.PrettyFormatter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class Recipe {
    private int id;
    private String name;
    private int numberOfIngredients;
    private String ingredients;
    private String steps;
    private String Publisher ;
    public LinkedList<String> feedbacks;
    boolean valid = false;
    private static final Logger logger = Logger.getLogger(Recipe.class.getName());

    public String getIngredients() {
        return ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public Recipe(String name, int numberOfIngredients, String ingredients, String steps, String Publisher) {
        this.name = name;
        this.numberOfIngredients = numberOfIngredients;
        this.ingredients = ingredients;
        this.steps = steps;
        this.Publisher = Publisher;
        this.feedbacks = new LinkedList();

        logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.addHandler(consoleHandler);

    }

    public List<String> getFeedbacks() {
        return feedbacks;
    }
    public boolean addFeedback(String feedback) {
        feedbacks.add(feedback);
        this.getFeedbacks();
        return true;
    }
    public void DeleteFeedback(String feedback) {
        feedbacks.remove(feedback);
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return Publisher;
    }

    @Override
    public String toString() {
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

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}




