package sweet.dev.models;

public class Date {
        private Integer day;
        private Integer month;
        private Integer year;

        public Date(Integer day, Integer month, Integer year) {
            this.day = day;
            this.month = month;
            this.year = year;
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
}
