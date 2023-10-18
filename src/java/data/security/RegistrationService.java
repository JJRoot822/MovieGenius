package data.security;

import java.sql.SQLException;

public class RegistrationService {
    public static RegistrationService shared = new RegistrationService();
    
    private RegistrationService() {}
    
    public void register(User user) throws SQLException {
        try {
            MovieDB.insertUser(user);
        } catch (SQLException e) {
            throw e;
        }
    }
}
