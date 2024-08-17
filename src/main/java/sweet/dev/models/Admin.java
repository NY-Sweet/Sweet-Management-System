package sweet.dev.models;

public class Admin {
    private String AdminName;
    private String password;
    public Admin(String username, String password) {
        this.AdminName = username;
        this.password = password;
    }
    public String getAdminName() {
        return AdminName;
    }
    public Object getPassword() {
        return password;
    }
}


