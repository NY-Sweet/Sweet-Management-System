package sweet.dev;

import io.cucumber.java.sl.In;

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
    }

    public void setId(String id) {
        this.id = id;
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
}
