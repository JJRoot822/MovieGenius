package data.security;

import java.util.logging.Level;
import org.apache.catalina.realm.SecretKeyCredentialHandler;

/**
 *
 * @author joshu
 */
public class SecurityUtil {
    private static SecretKeyCredentialHandler getCredentialHandler() throws Exception {
        SecretKeyCredentialHandler credentialHandler = new SecretKeyCredentialHandler();
        
        credentialHandler.setAlgorithm("PBKDF2WithHmacSHA256");
        credentialHandler.setKeyLength(256);
        credentialHandler.setSaltLength(16);
        credentialHandler.setIterations(4096);
        
        return credentialHandler;
    }
    
    public static String hashPassword(String plainTextPassword) throws Exception {
        String hash = "";
        
        try {
            hash = getCredentialHandler().mutate(plainTextPassword);
        } catch (Exception e) {
            throw e;
        }
        
        return hash;
    }
    
    public static boolean isMatchingPassword(String plainTextPassword, String hashedPassword) throws Exception {
        return getCredentialHandler().matches(plainTextPassword, hashedPassword);
    }
}
