package business;

import data.MovieDB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validation {

    private static final Logger LOG = Logger.getLogger(MovieDB.class.getName());

    public static boolean isEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isValidUsername(String username) {
        // must be unique for all users

        boolean result = false;
        boolean rs = false;
        try {
            rs = MovieDB.validateUsername(username);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** SQL error in checking username.");
        }

        if (!rs) {
            result = true;
        }
        return result;
    }

    public static boolean isValidEmail(String email) {
        // must have more than 5 characters and contain a @ and a . after the @ and must be unique for all users
        boolean result = false;
        try {
            result = MovieDB.validateEmail(email);
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** SQL error in checking email.");
        }

        return result;
    }

    public static boolean isValidPassword(String password) {
        // password must be more than 10 characters long

        boolean result = false;

        if (password.length() > 10) {
            result = true;
        }

        return result;
    }
}
