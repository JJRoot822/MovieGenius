package data;

import business.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import business.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/*
Current methods
----------------------------------
insertUsers(User user) - Inserts a user
deleteUser(User user) - Deletes a user
selectAllUsers() - Selects all of the users, returns LinkedHashMap with username as the Key
getPasswordForUsername(String username) - Returns the password for a given username
getPasswordForEmail(String email) - Returns the password for a given email
selectUserID(String username) - Returns the userID for a given username
adminUpdateUser(User user) - Updates a movie, assumes that the userID is not changing and is based on userID for the user.  Also has the ability to change the userType
updateUser(User user) - Updates a movie, assumes that the userID is not changing and is based on userID for the user
validateEmail(String email) - Returns a boolean on if an email already exists
validateUsername(String username) - Returns a boolean on if an username already exists
getUserInfo(String usernameOrEmail, String password) - Returns a user based on username or email, and the password
getUserInfo(String username) - Returns a user based on the username
insertMovie(Movie movie) - Inserts a movie
deleteMovie(int movieID) - Deletes a movie based on the movieID
updateMovie(Movie movie) - Updates a movie, assumes that the movieID is not changing and is based on movieID for the movie
getMovieGenres(int movieID) - Returns an arraylist of genres assocated with a movie by movieID
getTop10() - Returns an arraylist of the top 10 movies by rating descending
selectAllMovies() - Returns an arraylist of all movies
selectAllGenres() - Returns an arraylist of all genres
getUserReviews(int userID) - Returns an arraylist of all reviews associated with a user
getMoviesByGenreID(int genreID) - Returns an arraylist of movies associated with a genre by genreID
movieSearchByName(String titleSearch) - Returns an arraylist of all the movies that contain the string
 */
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
                = "INSERT INTO users (username, password, email, userType) "
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, (user.getUserType()));
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

    public static int deleteUser(int userID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "DELETE FROM users "
                + "WHERE userID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** delete user sql", e);
            throw e;
        } finally {
            try {
                ps.close();
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
            } catch (SQLException e) {
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

    public static String getPasswordForEmail(String email) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT password "
                + "FROM users "
                + "WHERE email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
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
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** select userID null pointer?", e);
            }
        }
    }

    public static void adminUpdateUser(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "UPDATE users "
                + "SET username = ?, password = ?, email = ?, userType = ?"
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUserType());
            ps.setInt(5, user.getUserID());
            rs = ps.executeQuery();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
            }
        }
    }

    public static void updateUser(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE users "
                + "SET username = ?, password = ?, email = ?"
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get user", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete user null pointer?", e);
                throw e;
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
            } catch (SQLException e) {
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
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** validate username null pointer?", e);
                throw e;
            }
        }
    }

    public static User getUserInfo(String usernameOrEmail, String password) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "";

        if (Validation.isEmail(usernameOrEmail)) {
            query = "SELECT * FROM users WHERE email = ? AND password = ?";
        } else {
            query = "SELECT * FROM users WHERE username = ? AND password = ?";
        }

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usernameOrEmail);
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
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }

    public static User getUserInfo(String usernameOrEmail) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "";

        if (Validation.isEmail(usernameOrEmail)) {
            query = "SELECT * FROM users WHERE email = ?";
        } else {
            query = "SELECT * FROM users WHERE username = ?";
        }

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, usernameOrEmail);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                int userid = rs.getInt("userID");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String userType = rs.getString("userType");
                user = new User(userid, userName, password, email, userType);
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
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }

    public static int insertMovie(Movie movie) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO movies (title, summary, releaseDate) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getSummary());
            ps.setDate(3, Date.valueOf(movie.getReleaseDate()));
            return ps.executeUpdate();

        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** insert sql", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** insert null pointer?", e);
                throw e;
            }
        }
    }

    public static int deleteMovie(int movieID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "DELETE "
                + "FROM movies "
                + "WHERE movieID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, movieID);
            return ps.executeUpdate();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get movie", e);
            throw e;
        } finally {
            try {
                ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete movie null pointer?", e);
                throw e;
            }
        }
    }

    public static void updateMovie(Movie movie) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "UPDATE movies "
                + "SET title = ?, summary = ?, releaseDate = ?"
                + "WHERE movieID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getSummary());
            ps.setDate(3, Date.valueOf(movie.getReleaseDate()));
            ps.setInt(4, movie.getMovieID());
            rs = ps.executeQuery();
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get movie", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** delete movie null pointer?", e);
                throw e;
            }
        }
    }

    public static ArrayList<String> getMovieGenres(int movieID) throws SQLException {
        ArrayList<String> genres = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT genreName "
                + "FROM moviegenre "
                + "INNER JOIN genres"
                + "ON moviegenre.genreID = genres.genreID"
                + "WHERE movieID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, movieID);
            rs = ps.executeQuery();
            while (rs.next()) {
                String genreName = rs.getString("genreName");
                genres.add(genreName);
            }
            return genres;
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

    public static ArrayList<Movie> getTop10() throws SQLException {
        ArrayList<Movie> movies = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query
                = "SELECT * "
                + "FROM movies "
                + "INNER JOIN reviews "
                + "ON movies.movieID = reviews.movieID "
                + "GROUP BY reviews.movieID "
                + "ORDER BY AVG(rating) desc "
                + "LIMIT 10";
        try {

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int movieID = rs.getInt("movieID");
                String title = rs.getString("title");
                String summary = rs.getString("summary");
                LocalDate releaseDate = rs.getDate("releaseDate").toLocalDate();
                Movie movie = new Movie(movieID, title, summary, releaseDate);
                movies.add(movie);
            }
            return movies;
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

    public static ArrayList<Double> getAvgRatingForMovie(ArrayList<Movie> movies) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Double> avgRatings = new ArrayList();
        String query
                = "SELECT AVG(Rating) as avgRating"
                + "FROM movies"
                + "INNER JOIN reviews"
                + "ON movies.movieID = reviews.movieID"
                + "GROUP BY reviews.movieID"
                + "ORDER BY AVG(rating) desc"
                + "LIMIT 10";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                double avgRating = rs.getDouble("avgRating");
                avgRatings.add(avgRating);
            }
            return avgRatings;
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

    public static ArrayList<Movie> selectAllMovies() throws SQLException {
        ArrayList<Movie> movies = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query
                = "SELECT *"
                + " FROM movies";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int movieID = rs.getInt("movieID");
                String title = rs.getString("title");
                String summary = rs.getString("summary");
                LocalDate releaseDate = rs.getDate("releaseDate").toLocalDate();
                Movie movie = new Movie(movieID, title, summary, releaseDate);
                movies.add(movie);
            }
            return movies;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get movies", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** get movies null pointer?", e);
                throw e;
            }
        }
    }

    public static ArrayList<String> selectAllGenres() throws SQLException {
        ArrayList<String> genres = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT genreName "
                + "FROM genres ";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String genreName = rs.getString("genreName");
                genres.add(genreName);
            }
            return genres;
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

    public static ArrayList<Review> getUserReviews(int userID) throws SQLException {
        ArrayList<Review> Reviews = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT * "
                + "FROM reviews "
                + "WHERE userID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int reviewID = rs.getInt("reviewID");
                int movieID = rs.getInt("movieID");
                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");
                Review review = new Review(reviewID, rating, userID, movieID, comment);
                Reviews.add(review);
            }
            return Reviews;
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

    public static ArrayList<Movie> getMoviesByGenreID(int genreID) throws SQLException {
        ArrayList<Movie> Movies = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT * "
                + "FROM movies "
                + "WHERE genreID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, genreID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int movieID = rs.getInt("movieID");
                String title = rs.getString("title");
                String summary = rs.getString("summary");
                LocalDate releaseDate = rs.getDate("releaseDate").toLocalDate();
                Movie movie = new Movie(movieID, title, summary, releaseDate);
                Movies.add(movie);
            }
            return Movies;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }

    public static ArrayList<Movie> movieSearchByName(String titleSearch) throws SQLException {
        ArrayList<Movie> Movies = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT * "
                + "FROM movies "
                + "WHERE title LIKE '%?%'";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, titleSearch);
            rs = ps.executeQuery();
            while (rs.next()) {
                int movieID = rs.getInt("movieID");
                String title = rs.getString("title");
                String summary = rs.getString("summary");
                LocalDate releaseDate = rs.getDate("releaseDate").toLocalDate();
                Movie movie = new Movie(movieID, title, summary, releaseDate);
                Movies.add(movie);
            }
            return Movies;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }
}
