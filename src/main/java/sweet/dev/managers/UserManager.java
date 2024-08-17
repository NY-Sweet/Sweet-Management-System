package sweet.dev.managers;

import sweet.dev.models.User;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class UserManager {
    private boolean userCreated;

    private List<User> users;
    private static final Logger logger = Logger.getLogger(UserManager.class.getName());
    public UserManager(List<User> users) {
        this.users = users;
    }

    public boolean isUserCreated() {
        return userCreated;
    }

    public List<User> getUsers() {
        return users;
    }

    public void createAccountForUser(User user) {
        userCreated = false;
        boolean flag = true;
        for (User u : users) {
            if (u.getUserName().equals(user.getUserName())) {
                flag = false;
                break;
            }
        }
        if (flag) {
            addUser(user);
            userCreated = true;
        }
    }

    public boolean addUser(User user) {
        users.add(user);
        return true;
    }


    public User getTheUser(String userName) {

        for (User s : users) {
            if (s.getUserName().equals(userName)) {
                return s;
            }
        }

        return null;
    }
    public boolean isValidPassword(String oldPassword, String newPassword, String confirmPassword, User user) {
        return oldPassword.equals(user.getPassword()) && newPassword.equals(confirmPassword);
    }

    public boolean deleteUser (String userName){
        User userToRemove =  getTheUser(userName);
        users.remove(userToRemove);
        return true;

    }
    public boolean displayAllUsers(){
        for (User user : this.users) {

            if (logger.isLoggable(Level.INFO)) {
                logger.info(user::toString);
            }
        }
        return true;
    }

}
