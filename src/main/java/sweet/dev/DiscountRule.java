package sweet.dev;

public class DiscountRule {
    private double percentage;
    private int daysBeforeExpiration;

    public DiscountRule(double percentage, int daysBeforeExpiration) {
        this.percentage = percentage;
        this.daysBeforeExpiration = daysBeforeExpiration;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getDaysBeforeExpiration() {
        return daysBeforeExpiration;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setDaysBeforeExpiration(int daysBeforeExpiration) {
        this.daysBeforeExpiration = daysBeforeExpiration;
    }
}
