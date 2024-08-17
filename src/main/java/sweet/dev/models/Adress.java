package sweet.dev.models;

public class Adress {
    private String city;
    private String street;
    private String homeNum;

    public Adress(String city, String street, String homeNum) {
        this.city = city;
        this.street = street;
        this.homeNum = homeNum;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHomeNum(String homeNum) {
        this.homeNum = homeNum;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHomeNum() {
        return homeNum;
    }
}
