package sweet.dev.models;

import java.util.LinkedList;
import java.util.List;

public class User {

    private   String userName;
    private  String password;
    private String phoneNum;
   private Adress adress;
    private String email;
    private String role;
    private List<Order> orders;

    public User(String userName, String password, String phoneNum, String email, Adress adress, String role) {
        this.userName = userName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
       this.adress=adress;
        this.role=role;
        this.orders=new LinkedList<>();
    }

    public Adress getAdress() {
        return adress;
    }

    public User() {
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


    public List<Order> getOrders() {
        return orders;
    }


    public String getEmail() {
        return email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }

    public boolean setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
        return true;
    }

    public boolean setEmail(String email) {
        this.email = email;
        return true;
    }



    public boolean addOrder(Order order)
    {
        orders.add(order);
        return true;
    }

}
