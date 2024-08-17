package sweet.dev.models;

public class Adress {
    private String city;
    private String street;
    private String homeNum;
    private boolean operationSuccess=true;

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
        operationSuccess=true;
        return city;

    }

    public String getStreet() {

        operationSuccess=true;

        return street;
    }

    public String getHomeNum() {
        operationSuccess=true;

        return homeNum;
    }

    public boolean isOperationSuccess() {
        return operationSuccess;
    }
}
