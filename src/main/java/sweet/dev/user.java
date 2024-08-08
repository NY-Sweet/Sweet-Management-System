package sweet.dev;

public class user {

    private  String userName;
    private  String password;
    private String phoneNum;
    private String email;
    private String city;
    private String street;
    private String homeNum;
    private String role;

    public user(String userName, String password, String phoneNum, String email, String city, String street, String homeNum, String role) {
        this.userName = userName;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.city = city;
        this.street = street;
        this.homeNum = homeNum;
        this.role=role;
    }

    public user() {
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

    public boolean setUserName(String userName) {
        this.userName = userName;
        return true;
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

    public boolean setCity(String city) {
        this.city = city;
        return true;
    }

    public boolean setStreet(String street) {
        this.street = street;
        return true;
    }

    public boolean setHomeNum(String homeNum) {
        this.homeNum = homeNum;
        return true;
    }

    public boolean setRole(String role) {
        this.role = role;
        return true;
    }
}
