/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import data.MovieDB;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author tmdel
 */
public class Validation {
    public static String isValidUsername (String username, String label, HashMap<String, String> errors) {
        // username must be between 4-30 characters inclusive and must be unique for all users
        
        boolean result = false;
        try {
            result = MovieDB.validateUsername(username);
        } catch (SQLException e) {
            errors.put(label, "SQL error while checking username.");
        }
        
        if ("".equals(username) || username.length() < 4 || username.length() > 30) {
            errors.put(label, Validation.capFirstLetter(label) + " must be between 4-30 characters long.");
            username = null;
        } else if (result) { // this is where it will check to see if the username is already in database.
            errors.put(label, Validation.capFirstLetter(label) + " is already taken. Please select another username.");
            username = null;
        }
        return username;
    }
    
    public static String isValidEmail (String email, String label, HashMap<String, String> errors) {
        // must have more than 5 characters and contain a @ and a . after the @ and must be unique for all users
        boolean result = false;
        try {
            result = MovieDB.validateEmail(email);
        } catch (SQLException e) {
            errors.put(label, "SQL error while checking email.");
        }
        // gets int values for the index of the @ symbol and the last index of the . symbol
        int atIndex = email.indexOf("@");
        int dotIndex = email.lastIndexOf(".");

        if (email.length() <= 5) {
            errors.put(label, Validation.capFirstLetter(label) +  " must be longer than 5 characters.");
            email = null;
        } else if (atIndex == -1 || dotIndex == -1) { //if -1 no @ or . was in email
            errors.put(label, Validation.capFirstLetter(label) + " must have a @ symbol and a . after the @ symbol.");
            email = null;
        } else if (dotIndex < atIndex) { //if dotIndex < atIndex the last . came before the @ symbol
            errors.put(label, Validation.capFirstLetter(label) + " must follow the a xxxx@xxx.com structure");
            email = null;
        } else if (result) { // this is where it will check to see if the email is already in the database.
             errors.put(label, Validation.capFirstLetter(label) + " already tied to an account please use a different email.");
             email = null;
        }
        
        return email;
    }
    
    public static String isValidPassword (String password, String label, HashMap<String, String> errors) {
        // password must be more than 10 characters long
        
        if (password.length() <= 10) {
            errors.put(label, Validation.capFirstLetter(label) + " must be longer than 10 characters.");
            password = null;
        }
        
        return password;
    }
    
    private static String capFirstLetter(String input) {
        if (input == null) {
            return null;
        } else if (input.length() == 1) {
            return input.toUpperCase();
        } else {
            return input.substring(0, 1).toUpperCase() + input.substring(1);
        }
    }
}

