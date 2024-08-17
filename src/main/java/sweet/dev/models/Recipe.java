package sweet.dev.models;
import sweet.format.PrettyFormatter;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recipe {
    private int id;
    private String name;
    private int numberOfIngredients;
    private String ingredients;
    private String steps;
    private String publisher;
    private List<String> feedbacks;
    private static final Logger logger = Logger.getLogger(Recipe.class.getName());

    public String getIngredients() {
        return ingredients;
    }

    public int getNumberOfIngredients() {
        return numberOfIngredients;
    }

    public String getSteps() {
        return steps;
    }

    public Recipe(String name, int numberOfIngredients, String ingredients, String steps, String publisher) {
        this.name = name;
        this.numberOfIngredients = numberOfIngredients;
        this.ingredients = ingredients;
        this.steps = steps;
        this.publisher = publisher;
        this.feedbacks = new LinkedList<>();

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

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }


    public void showFeedbaks (){
        int i =0;
        for (final String feedback : feedbacks) {
        logger.log(Level.INFO, "{0}   {1}", new Object[]{i, feedback});

        }

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}




