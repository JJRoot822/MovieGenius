package data.security;

import business.*;
import data.*;

/**
 *
 * @author joshu
 */
public class AuthenticationService {
    public static AuthenticationService shared = new AuthenticationService();
    
    private AuthenticationService() {
    }
    
    public boolean login(String emailOrUsername, String password) {
        if (Validation.isEmail(emailOrUsername)) {
            return loginUsingEmailAndPassword(emailOrUsername, password);
        }
        
        return loginUsingUsernameAndPassword(emailOrUsername, password);
    }
    
    private boolean loginUsingUsernameAndPassword(String username, String password) {
        String hashedUserPassword ="";
        
        try {
            hashedUserPassword = MovieDB.getPasswordForUsername(username);
        
            return SecurityUtil.isMatchingPassword(password, hashedUserPassword);
        } catch (Exception e) {
            return false;
        }
        
        
    }
    
    private boolean loginUsingEmailAndPassword(String email, String password) {
        String hashedUserPassword = "";
        
        try {
            hashedUserPassword = MovieDB.getPasswordForEmail(email);
            
            return SecurityUtil.isMatchingPassword(password, hashedUserPassword);
        } catch (Exception e) {
            return false;
        }
    }
}
