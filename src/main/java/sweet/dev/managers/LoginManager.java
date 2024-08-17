package sweet.dev.managers;

import sweet.dev.models.Admin;
import sweet.dev.models.Supplier;
import sweet.dev.models.User;

import java.util.*;

public class LoginManager {
    private boolean validation;
    private Integer roleInSys;
    private boolean forget;
    private String enteredUsername;
    private List<User> users;
    private List<Supplier> suppliers;
    private List<Admin> admins;


    public LoginManager(List<User> users, List<Supplier> suppliers, List<Admin> admins) {
        this.users = users;
        this.suppliers = suppliers;
        this.admins=admins;
    }


    public void setRoleInSys(Integer roleInSys) {
        this.roleInSys = roleInSys;
    }

    public void setEnteredUsername(String enteredUsername) {
        this.enteredUsername = enteredUsername;
    }

    public boolean isValidation() {
        return validation;
    }

    public Integer getRoleInSys() {
        return roleInSys;
    }

    public boolean isForget() {
        return forget;
    }


    public String getEnteredUsername() {
        return enteredUsername;
    }


    public void setUsernameAndPasswordFromSystem(String userName, String password) {
        validation = false;
        for (User u : users) {
            if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                validation = true;
                roleInSys = 0;
                enteredUsername=userName;
                return;
            }
        }
        for (Supplier s : suppliers) {
            if (s.getUserName().equals(userName) && s.getPassword().equals(password)) {
                validation = true;
                enteredUsername=userName;
                roleInSys = 1;
            }
        }
        for (Admin admin : admins) {
            if (admin.getAdminName().equals(userName) && admin.getPassword().equals(password)) {
                validation = true;
                enteredUsername=userName;
                roleInSys = 2;
            }
        }
    }


    public void setEmptyUsernameAndPasswordFromSystem(String username, String password) {
        if (username.isEmpty() && !password.isEmpty()) {
            validation = false;
        }
    }

    public void setUsernameAndEmptyPasswordFromSystem(String username, String password) {
        if (!username.isEmpty() && password.isEmpty()) {
            validation = false;
        }
    }

    public void validUsernameAndForgetPassword(String username, String forget) {
        this.forget = false;
        for (User u : users) {
            if (u.getUserName().equals(username) && forget.equals("Forget")) {
                this.forget = true;
                this.enteredUsername = username;
                return;
            }
        }
    }

    public void updatePassword(String newPassword) {
        if (isForget()) {
            for (User u : users) {
                if (u.getUserName().equals(enteredUsername)) {
                    u.setPassword(newPassword);
                    roleInSys = 0;
                    break;
                }
            }
        }
    }

}
