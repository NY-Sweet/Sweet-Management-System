package sweet.dev.managers;

import sweet.dev.models.user;

import java.util.List;
import java.util.logging.Logger;
public class UserManager {
    private boolean userCreated;

    private List<user> users;

    public UserManager(List<user> users) {
        this.users = users;
    }

    public boolean isUserCreated() {
        return userCreated;
    }

    public List<user> getUsers() {
        return users;
    }

    public void createAccountForUser(String userName, String password, String city, String street, String homeNum, String phneNum, String email, String role) {
        userCreated = false;
        boolean flag = true;
        for (user u : users) {
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
        user newUser = new user(userName, password, phneNum, email, city, street, homeNum, role);
        users.add(newUser);
        return true;
    }


    public user getTheUser(String userName) {

        for (user s : users) {
            if (s.getUserName().equals(userName)) {
                return s;
            }
        }

        return null;
    }
    public boolean isValidPassword(String OldPassword, String NewPassword, String confirmPassword ,user User) {
         if (OldPassword.equals(User.getPassword())) {
            if (NewPassword.equals(confirmPassword)) {
                return true;
            }
        }
        return false ;
    }

    public boolean deleteUser (String UserName){
        user userToRemove =  getTheUser(UserName);
        users.remove(userToRemove);
        return true;

    }
    private static final Logger logger = Logger.getLogger(RecipeManager.class.getName());
    public boolean DisplayAllUsers (){
        for (user User : this.users) {

            logger.info(User.toString());
        }
        return true;
    }

}
