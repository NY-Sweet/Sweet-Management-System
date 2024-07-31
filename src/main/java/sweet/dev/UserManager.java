package sweet.dev;

import java.util.List;

public class UserManager {
    private boolean userCreated;

    private List<user> users;

    public UserManager(List<user> users) {
        this.users = users;
    }

    public boolean isUserCreated() {
        return userCreated;
    }



    public void createAccountForUser(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role) {
        userCreated = false;
        boolean flag = true;
        for (user u : users) {
            if (u.getUserName().equals(userName)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            addUser(userName, password, phneNum, email, city, street, homeNum, role);
            userCreated = true;
        }
    }

    public void addUser(String userName, String password, String phneNum, String email, String city, String street, String homeNum, String role) {
        user newUser = new user(userName, password, phneNum, email, city, street, homeNum, role);
        users.add(newUser);
    }


    public user getTheUser(String userName) {

        for (user s : users) {
            if (s.getUserName().equals(userName)) {
                return s;
            }
        }

        return null;
    }
}
