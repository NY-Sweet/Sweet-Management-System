package sweet.dev;

import java.util.LinkedList;

public class user {

    private  String userName;
    private  String password;
    private String phoneNum;
    private String email;
    private String city;
    private String street;
    private String homeNum;
    private String role;
    private LinkedList<Order> orders;

    public user(String userName, String password, String phoneNum, String email, String city, String street, String homeNum, String role) {
        this.userName = userName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.city = city;
        this.street = street;
        this.homeNum = homeNum;
        this.role=role;
        this.orders=new LinkedList<>();
    }

    public user() {
        this.orders=new LinkedList<>();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
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

    public String getRole() {
        return role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setRole(String role) {
        this.role = role;
    }

    public boolean addOrder(Order order)
    {
        orders.add(order);
        return true;
    }
}
