package sweet.dev;

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
    public void setAdminName(String adminName) {
        AdminName = adminName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean AdminPermession() {
        return true;
    }

}



