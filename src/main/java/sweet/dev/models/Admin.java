package sweet.dev.models;

public class Admin {
    private String adminName;
    private String password;
    public Admin(String username, String password) {
        this.adminName = username;
        this.password = password;
    }
    public String getAdminName() {
        return adminName;
    }
    public Object getPassword() {
        return password;
    }
}


