package sweet.dev.managers;

import sweet.dev.models.User;

import java.util.List;
import java.util.logging.Logger;
public class UserManager {
    private boolean userCreated;

    private List<User> users;

    public UserManager(List<User> users) {
        this.users = users;
    }

    public boolean isUserCreated() {
        return userCreated;
    }

    public List<User> getUsers() {
        return users;
    }

    public void createAccountForUser(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role) {
        userCreated = false;
        boolean flag = true;
        for (User u : users) {
            if (u.getUserName().equals(userName)) {
                flag = false;
                userCreated = false;
                break;
            }
        }
        if (flag) {
            addUser(userName, password, phneNum, email, city, street, homeNum, role);
            userCreated = true;
        }
    }

    public boolean addUser(String userName, String password, String phneNum, String email, String city, String street, String homeNum, String role) {
        User newUser = new User(userName, password, phneNum, email, city, street, homeNum, role);
        users.add(newUser);
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
    public boolean isValidPassword(String oldPassword, String newPassword, String confirmpassword , User user) {
         if (oldPassword.equals(user.getPassword())) {
            if (newPassword.equals(confirmpassword)) {
                return true;
            }
        }
        return false ;
    }

    public boolean deleteUser (String userName){
        User userToRemove =  getTheUser(userName);
        users.remove(userToRemove);
        return true;

    }
    private static final Logger logger = Logger.getLogger(RecipeManager.class.getName());
    public boolean DisplayAllUsers (){
        for (sweet.dev.models.User User : this.users) {

            logger.info(User.toString());
        }
        return true;
    }

}
