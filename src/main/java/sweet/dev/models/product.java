package sweet.dev.models;

import sweet.format.PrettyFormatter;

import java.util.LinkedList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class product {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
    private Double cost;
    private Integer day;
    private Integer month;
    private Integer year;
    private Double discountPercentage;
    public LinkedList<String> feedbacks;
    private final Logger logger=Logger.getLogger("MyProduct");
    public product(String id, String name, Integer quantity, Double price, Double cost, Integer day, Integer month, Integer year, Double discountPercentage) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.cost = cost;
        this.day = day;
        this.month = month;
        this.year = year;
        this.discountPercentage = discountPercentage;
        this.feedbacks = new LinkedList();

        logger.setUseParentHandlers(false);

        Handler[] handlers = logger.getHandlers();
        for (Handler handler : handlers) {
            logger.removeHandler(handler);
        }

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new PrettyFormatter());
        logger.addHandler(consoleHandler);


    }


    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double getCost() {
        return cost;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getYear() {
        return year;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public boolean addFeedback(String feedback) {
        feedbacks.add(feedback);
        return true;
    }

    public String formatFeedbacks() {
        if (feedbacks == null || feedbacks.isEmpty()) {
            return "No feedbacks";
        }
        StringBuilder feedbackBuilder = new StringBuilder();
        int count = 1;
        for (String feedback : feedbacks) {
            feedbackBuilder.append(count).append(". ").append(feedback).append(".....");
            count++;
        }
        return feedbackBuilder.toString().trim();
    }

}
