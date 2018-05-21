package util;

import model.User;

public class AuthUtils {
    public static boolean authenticateUser(User user, String enteredPassword) {
        return user != null && PasswordUtils.verifyUserPassword(enteredPassword, user.getPassword(), user.getSalt());
    }
}
