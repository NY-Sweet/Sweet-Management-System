package sweet.dev;

import java.util.List;

public class LoginManager {
    private boolean validation;
    private Integer roleInSys;
    private boolean forget;
    private String enteredUsername;
    private List<user> users;
    private List<supplier> suppliers;
    private String LoggedInSupplier;
    private String LoggedInUser;

    private supplier LoggedSupplierObj;
    private user LoggedUserObj;

    public LoginManager(List<user> users, List<supplier> suppliers) {
        this.users = users;
        this.suppliers = suppliers;
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

    public void setForget(boolean forget) {
        this.forget = forget;
    }

    public String getEnteredUsername() {
        return enteredUsername;
    }

    public void setEnteredUsername(String enteredUsername) {
        this.enteredUsername = enteredUsername;
    }

    public void setUsernameAndPasswordFromSystem(String userName, String password) {
        validation = false;
        for (user u : users) {
            if (u.getUserName().equals(userName) && u.getPassword().equals(password)) {
                validation = true;
                roleInSys = 0;
                LoggedInUser = userName;
                return;
            }
        }
        for (supplier s : suppliers) {
            if (s.getUserName().equals(userName) && s.getPassword().equals(password)) {
                validation = true;
                LoggedInSupplier = userName;
                roleInSys = 1;
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
        for (user u : users) {
            if (u.getUserName().equals(username) && forget.equals("Forget")) {
                this.forget = true;
                this.enteredUsername = username;
                LoggedInUser = username;
                return;
            }
        }
    }

    public void updatePassword(String newPassword) {
        if (isForget()) {
            for (user u : users) {
                if (u.getUserName().equals(enteredUsername)) {
                    u.setPassword(newPassword);
                    roleInSys = 0;
                    break;
                }
            }
        }
    }

    public user getTheUser() {
        if (LoggedInUser != null) {
            for (user u : users) {
                if (u.getUserName().equals(LoggedInUser)) {
                    return u;
                }
            }
        }
        return null;
    }

}
