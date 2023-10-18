package data.security;

import data.security.SecurityUtil;
import java.sql.SQLException;

public class RegistrationService {
    public static RegistrationService shared = new RegistrationService();
    
    private RegistrationService() {}
    
    public void register(String email, String username, String password) throws SQLException {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(SecurityUtil.hashPassword(password));
        
        try {
            MovieDB.insertUser(user);
        } catch (SQLException e) {
            throw e;
        }
    }
}
