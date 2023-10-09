/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import business.User;
import java.util.LinkedHashMap;
/**
 *
 * @author tmdel
 */
public class MovieDB {
    private static final Logger LOG = Logger.getLogger(MovieDB.class.getName());

    public static int insertUser(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO users (userID, username, password, email, userType) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user.getUserID());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, (user.getUserType()));
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }
    
    public static void deleteUser(int userID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "DELETE "
                + "FROM users "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }
    
    public static LinkedHashMap<String, User> selectAllUsers() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM users";
        try {
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();
            User user = null;
            LinkedHashMap<String, User> users = new LinkedHashMap();

            while (rs.next()) {
                int userID = rs.getInt("userID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String userType = rs.getString("userType");
                user = new User(userID, username, password, email, userType);
                users.put(user.getUsername(), user);
            }
            return users;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select all sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** select all null pointer?", e);
                throw e;
            }
        }
    }
    
    public static String getPasswordForUsername(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT password "
                + "FROM users "
                + "WHERE username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            String password = "";
            rs = ps.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }
            return password;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }
    
    public static int selectUserID(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT userID "
                + "FROM users "
                + "WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            int userID = 0;
            if (rs.next()) {
                userID = rs.getInt("userID");
            }
            return userID;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** select userID", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** select userID null pointer?", e);
            }
        }
    }
    
    public static boolean validateEmail(String email) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT email FROM users "
                + "WHERE email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            //String emailCheck = rs.getString("email");
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** validate email sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** validate email null pointer?", e);
                throw e;
            }
        }
    }

    public static boolean validateUsername(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT username FROM users "
                + "WHERE username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            //String usernameCheck = rs.getString("username");
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** validate username sql", e);
            throw e;
        } finally {
            try {
                rs.close();
                ps.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** validate username null pointer?", e);
                throw e;
            }
        }
    }
    
     public static User getUserInfo(String username, String password) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT * "
                + "FROM users "
                + "WHERE username = ? AND password = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                int userid = rs.getInt("userID");
                String userName = rs.getString("username");
                String Password = rs.getString("password"); 
                String email = rs.getString("email");
                String userType = rs.getString("userType");
                user = new User(userid, userName, Password, email, userType);
            }
            return user;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }
    
    public static User getUserInfo(String username) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT * "
                + "FROM users "
                + "WHERE username = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                int userid = rs.getInt("userID");
                String userName = rs.getString("username");
                String Password = rs.getString("password"); 
                String email = rs.getString("email");
                String userType = rs.getString("userType");
                user = new User(userid, userName, Password, email, userType);
            }
            return user;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }
}
