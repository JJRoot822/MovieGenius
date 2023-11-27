package data;

import business.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static int insertReview(Review review) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO reviews (userID, movieID, rating, comment) "
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, review.getUserID());
            ps.setInt(2, review.getMovieID());
            ps.setInt(3, review.getRating());
            ps.setString(4, (review.getComment()));
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
    
    public static Movie SelectedMoive(int ID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT * "
                + "FROM movies "
                + "WHERE movieID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            Movie movie = null;
            if (rs.next()) {
                int movieID = rs.getInt("movieID");
                String title = rs.getString("title");
                String summary = rs.getString("summary");
                LocalDate releaseDate = rs.getDate("releaseDate").toLocalDate();
                int genreID = rs.getInt("genreID");
                movie = new Movie(movieID, title, summary, releaseDate, genreID);
            }
            return movie;
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

    public static void adminUpdateUser(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "UPDATE users "
                + "SET username = ?, password = ?, email = ?, userType = ? "
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

    public static void updateUserUsername(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE users "
                + "SET username = ? "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setInt(2, user.getUserID());
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

    public static void updateUserPassword(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE users "
                + "SET password = ? "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getPassword());
            ps.setInt(2, user.getUserID());
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

    public static void updateUserEmail(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "UPDATE users "
                + "SET email = ? "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setInt(2, user.getUserID());
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
                = "INSERT INTO movies (title, summary, releaseDate, genreID) "
                + "VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getSummary());
            ps.setDate(3, Date.valueOf(movie.getReleaseDate()));
            ps.setInt(4, movie.getGenreID());
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

    public static int getMovieIDByTitle(String title) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT MovieID "
                + "FROM movies "
                + "WHERE title = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();
            int movieID = 0;
            if (rs.next()) {
                movieID = rs.getInt("movieID");
            }
            return movieID;
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
                + "SET title = ?, summary = ?, releaseDate = ?, genreID = ?"
                + "WHERE movieID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getSummary());
            ps.setDate(3, Date.valueOf(movie.getReleaseDate()));
            ps.setInt(4, movie.getMovieID());
            ps.setInt(5, movie.getGenreID());
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

    public static String getMovieGenres(int movieID) throws SQLException {
        String genre = "";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT genreName "
                + "FROM movies "
                + "INNER JOIN genres "
                + "ON movies.genreID = genres.genreID "
                + "WHERE movieID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, movieID);
            rs = ps.executeQuery();
            while (rs.next()) {
                String genreName = rs.getString("genreName");
                genre = genreName;
            }
            return genre;
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
                int genreID = rs.getInt("genreID");
                Movie movie = new Movie(movieID, title, summary, releaseDate, genreID);
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

    public static ArrayList<Double> getTop10Avgs() throws SQLException {
        ArrayList<Double> avgRatings = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query
                = "SELECT AVG(rating) as avgRating "
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
                Double avgRating = rs.getDouble("avgRating");
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
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get password null pointer?", e);
                throw e;
            }
        }
    }
    
    public static ArrayList<Movie> getNewReleases() throws SQLException {
        ArrayList<Movie> movies = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query
                = "SELECT * "
                + "FROM movies "
                + "WHERE DATEDIFF(CURRENT_TIMESTAMP, releaseDate) BETWEEN 0 AND 90 "
                + "ORDER BY releaseDate desc";
        try {

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int movieID = rs.getInt("movieID");
                String title = rs.getString("title");
                String summary = rs.getString("summary");
                LocalDate releaseDate = rs.getDate("releaseDate").toLocalDate();
                int genreID = rs.getInt("genreID");
                Movie movie = new Movie(movieID, title, summary, releaseDate, genreID);
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
                LOG.log(Level.SEVERE, "*** get recent movies null pointer?", e);
                throw e;
            }
        }
    }

    public static ArrayList<Double> getNewReleaseRatings() throws SQLException {
        ArrayList<Double> ratings = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query
                = "SELECT AVG(Rating) as avgRating "
                + "FROM movies "
                + "LEFT OUTER JOIN reviews "
                + "ON movies.movieID = reviews.movieID "
                + "WHERE DATEDIFF(CURRENT_TIMESTAMP, releaseDate) BETWEEN 0 AND 90 "
                + "GROUP BY reviews.movieID "
                + "ORDER BY releaseDate desc";
        try {

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Double rating = rs.getDouble("avgRating");
                ratings.add(rating);
            }
            return ratings;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** get recent movies null pointer?", e);
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
                = "SELECT AVG(Rating) as avgRating "
                + "FROM movies "
                + "INNER JOIN reviews "
                + "ON movies.movieID = reviews.movieID"
                + "GROUP BY reviews.movieID "
                + "ORDER BY AVG(rating) desc "
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
            } catch (SQLException e) {
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
                int genreID = rs.getInt("genreID");
                Movie movie = new Movie(movieID, title, summary, releaseDate, genreID);
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
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get movies null pointer?", e);
                throw e;
            }
        }
    }

    public static ArrayList<Genre> selectAllGenres() throws SQLException {
        ArrayList<Genre> genres = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT * "
                + "FROM genres ";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int genreID = rs.getInt("genreID");
                String genreName = rs.getString("genreName");
                Genre genre = new Genre(genreID, genreName);
                genres.add(genre);
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
            } catch (SQLException e) {
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
                Movie movie = new Movie(movieID, title, summary, releaseDate, genreID);
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
                int genreID = rs.getInt("genreID");
                Movie movie = new Movie(movieID, title, summary, releaseDate, genreID);
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

    public static void updateReview(Review review) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "UPDATE reviews "
                + "SET rating = ?, comment = ?"
                + "WHERE reviewID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, review.getRating());
            ps.setString(2, review.getComment());
            ps.setInt(3, review.getReviewID());
            ps.setInt(4, review.getMovieID());
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

    public static ArrayList<Review> getMovieReviews(int movieID) throws SQLException {
        ArrayList<Review> Reviews = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT * "
                + "FROM reviews "
                + "WHERE movieID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, movieID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int reviewID = rs.getInt("reviewID");
                int userID = rs.getInt("userID");
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
                LOG.log(Level.SEVERE, "*** get reviews for movie null pointer?", e);
                throw e;
            }
        }
    }

    public static LinkedHashMap<Integer, MovieReviewVM> getMovieReviewsForUser(int userId) throws Exception {
        LinkedHashMap<Integer, MovieReviewVM> movieReviewsMap = new LinkedHashMap<>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT r.reviewID AS id, m.title AS title, r.rating AS rating, r.comment AS comment " +
                "FROM reviews AS r " +
                "JOIN movies AS m ON m.movieID = r.movieID "
                + "WHERE userID = ?";

        try {
            ps = connection.prepareStatement(query);

            ps.setInt(1, userId);

            rs = ps.executeQuery();

            while (rs.next()) {
                int reviewID = rs.getInt("id");
                String title = rs.getString("title");
                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");

                MovieReviewVM movieReview = new MovieReviewVM(title, rating, comment);
                movieReviewsMap.put(reviewID, movieReview);
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get movie reviews sql", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get movie reviews close", e);
            }
        }

        return movieReviewsMap;
    }
    
    public static LinkedHashMap<Integer, MovieReviewVM> getMovieReviewsForUser(int userId, String title) throws Exception {
        LinkedHashMap<Integer, MovieReviewVM> movieReviewsMap = new LinkedHashMap<>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT r.reviewID AS id, m.title AS title, r.rating AS rating, r.comment AS comment " +
                "FROM reviews AS r " +
                "JOIN movies AS m ON m.movieID = r.movieID "
                + "WHERE userID = ? AND title LIKE %?%";

        try {
            ps = connection.prepareStatement(query);

            ps.setInt(1, userId);
            ps.setString(2, title);

            rs = ps.executeQuery();

            while (rs.next()) {
                int reviewID = rs.getInt("id");
                String movieTitle = rs.getString("title");
                int rating = rs.getInt("rating");
                String comment = rs.getString("comment");

                MovieReviewVM movieReview = new MovieReviewVM(title, rating, comment);
                movieReviewsMap.put(reviewID, movieReview);
            }
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get movie reviews sql", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                pool.freeConnection(connection);
            } catch (SQLException e) {
                LOG.log(Level.SEVERE, "*** get movie reviews close", e);
            }
        }

        return movieReviewsMap;
    }
    public static ArrayList<userReview> getMovieUserReview(int ID) throws SQLException {
        ArrayList<userReview> userReviews = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "SELECT reviewID, movieID, users.username, rating, comment "
                + "FROM reviews "
                + "INNER JOIN users "
                + "ON reviews.userID = users.userID "
                + "WHERE movieID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int reviewID = rs.getInt("reviewID");
                int movieID = rs.getInt("movieID");
                int rating = rs.getInt("rating");
                String username = rs.getString("username");
                String comment = rs.getString("comment");
                userReview userReview = new userReview(reviewID, movieID, rating, username, comment);
                userReviews.add(userReview);
            }
            return userReviews;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "*** get password", e);
            throw e;
        } finally {
            try {
                ps.close();
                rs.close();
                pool.freeConnection(connection);
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "*** get reviews for movie null pointer?", e);
                throw e;
            }
        }
    }

    public static int deleteReview(int reviewID) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "DELETE FROM reviews "
                + "WHERE reviewID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, reviewID);
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
    
    public static Review getReviewById(int reviewId) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM reviews WHERE reviewID = ?";
        try {
            ps = connection.prepareStatement(query);

            ps.setInt(1, reviewId);
            
            rs = ps.executeQuery();
            Review review = null;
            if (rs.next()) {
                review = new Review();
                review.setReviewID(rs.getInt("reviewID"));
                review.setRating(rs.getInt("rating"));
                review.setUserID(rs.getInt("userID"));
                review.setMovieID(rs.getInt("movieID"));
                review.setComment(rs.getString("comment"));
                
                return review;
            }
            
            return null;
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
}
