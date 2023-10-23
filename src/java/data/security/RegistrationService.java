package data.security;

import business.User;
import data.MovieDB;
import data.security.SecurityUtil;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistrationService {
    public static RegistrationService shared = new RegistrationService();
    
    private RegistrationService() {}
    
    public void register(String email, String username, String password) throws SQLException {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        try {
            user.setPassword(SecurityUtil.hashPassword(password));
        } catch (Exception ex) {
            Logger.getLogger(RegistrationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            MovieDB.insertUser(user);
        } catch (SQLException e) {
            throw e;
        }
    }
}
