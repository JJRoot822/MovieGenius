package controllers;

import business.Genre;
import business.Movie;
import business.Review;
import business.User;
import business.Validation;
import data.MovieDB;
import data.security.SecurityUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Private extends HttpServlet {

    String url = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser == null) {
            response.sendRedirect("Public");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "default";
        }

        switch (action) {
            case "gotoUserPage": {
                url = "/userPage.jsp";

                break;
            }
            case "gotoMovieFilter": {
                url = "/movieFilter.jsp";
                try {
                    ArrayList<Genre> genres = MovieDB.selectAllGenres();
                    request.setAttribute("genres", genres);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "filter": {
                url = "/movieFilter.jsp";
                ArrayList<Movie> movies = new ArrayList();
                ArrayList<Genre> genres = new ArrayList();
                try {
                    genres = MovieDB.selectAllGenres();
                    int genreID = Integer.parseInt(request.getParameter("genreID"));
                    movies = MovieDB.getMoviesByGenreID(genreID);
                    request.setAttribute("movies", movies);
                    request.setAttribute("genres", genres);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "gotoUpdatePage": {
                url = "/updateUser.jsp";

                break;
            }
            case "gotoUserReview": {
                url = "/userReview.jsp";
                try {
                    ArrayList<Movie> movies = MovieDB.selectAllMovies();
                    request.setAttribute("movies", movies);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
            }
            case "logout": {
                logout(request);

                break;
            }
            case "updateUser": {
                try {
                    String newEmail = request.getParameter("email");
                    String newUserName = request.getParameter("userName");
                    String newPassword = request.getParameter("newPassword");
                    String re_enterPassword = request.getParameter("checkNewPassword");
                    List<String> errors = new ArrayList<String>();

                    if (!newEmail.equals("")) {
                        if (!Validation.isEmail(newEmail)) {
                            errors.add("The email you entered is not a valid format. A valid format looks like this: example@somesite.com");
                        }

                        if (!Validation.isValidEmail(newEmail)) {
                            loggedInUser.setEmail(newEmail);
                            MovieDB.updateUserEmail(loggedInUser);

                        } else {
                            errors.add("Email is already tied to an account please use a different email.");
                        }
                    }

                    if (!newUserName.equals("")) {
                        if (Validation.isValidUsername(newUserName)) {
                            loggedInUser.setUsername(newUserName);
                            MovieDB.updateUserUsername(loggedInUser);
                        } else {

                            errors.add("The user name is already taken.");
                        }

                    }

                    if (!newPassword.equals("") && !re_enterPassword.equals("")) {

                        if (!newPassword.equals(re_enterPassword)) {
                            errors.add("The password and password verification fields don't match.");
                        } else if (!Validation.isValidPassword(newPassword)) {
                            errors.add("Password must be longer than 10 characters.");
                        } else {
                            newPassword = SecurityUtil.hashPassword(newPassword);

                            loggedInUser.setPassword(newPassword);
                            MovieDB.updateUserPassword(loggedInUser);
                        }

                    } else if (!newPassword.equals("") && re_enterPassword.equals("")) {
                        errors.add("You must enter re-enter your password.");
                    } else if (newPassword.equals("") && !re_enterPassword.equals("")) {
                        errors.add("You must enter your new password if you want to change it.");
                    }

                    if (errors.isEmpty()) {

                        url = "/userPage.jsp";

                    } else {
                        url = "/updateUser.jsp";
                        request.setAttribute("errors", errors);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            }

            case "gotoAdminMovie": {

                List<Genre> genres = new ArrayList();
                try {
                    genres = MovieDB.selectAllGenres();
                } catch (SQLException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }

                request.setAttribute("genres", genres);

                url = "/admin/adminMovies.jsp";
                break;
            }

            case "addMovie": {
                String title = request.getParameter("title");
                String summary = request.getParameter("summary");
                LocalDate releaseDate = LocalDate.parse(request.getParameter("releasedate"));
                int genreID = Integer.parseInt(request.getParameter("genre"));
                
                Movie movie = new Movie(title, summary, releaseDate, genreID);
                
                try {
                    MovieDB.insertMovie(movie);
                } catch (SQLException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                
                url = "/Private?action=gotoAdminMovie";
                break;
            }

            case "gotoAdminPage": {
                url = "/admin/adminPage.jsp";

                break;
            }
            case "adminUserAction": {
                url = "/admin/adminAllUsers.jsp";
                LinkedHashMap<String, User> allUsers = new LinkedHashMap();

                try {
                    allUsers = MovieDB.selectAllUsers();
                } catch (SQLException e) {
                    Logger.getLogger(MovieDB.class.getName()).log(Level.SEVERE, null, e);
                }

                request.setAttribute("allUsers", allUsers);
                break;
            }

            case "adminDeleteUser": {
                int userID = 0;
                try {
                    userID = Integer.parseInt(request.getParameter("userID"));
                } catch (Exception e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }

                try {
                    MovieDB.deleteUser(userID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                url = "/Private?action=adminUserAction";
                break;
                
                
            }
            case "movieList": {
                url = "/movies.jsp";
                ArrayList<Movie> allMovies = new ArrayList();

                try {
                    allMovies = MovieDB.selectAllMovies();
                } catch (SQLException e) {
                    Logger.getLogger(MovieDB.class.getName()).log(Level.SEVERE, null, e);
                }

                request.setAttribute("allMovies", allMovies);
                break;
            }
            case "top10movies": {
                url = "/top10movies.jsp";
                ArrayList<Movie> top10Movies = new ArrayList();
                ArrayList<Double> top10Ratings = new ArrayList();
                try {

                    top10Movies = MovieDB.getTop10();
                    top10Ratings = MovieDB.getTop10Avgs();
                } catch (SQLException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                LinkedHashMap<Movie, Double> top10map = new LinkedHashMap();
                for (int i = 0; i < top10Movies.size(); i++) {
                    top10map.put(top10Movies.get(i), top10Ratings.get(i));
                }
                request.setAttribute("top10list", top10map);
                break;
            }
            case "submitReview": {
                List<String> errors = new ArrayList();
                String comment = request.getParameter("comment");
                int rating = Integer.parseInt(request.getParameter("rating"));
                int movieID = Integer.parseInt(request.getParameter("movieID"));
                if (!comment.equals("")) {
                    try {
                        url = "/userPage.jsp";
                        Review newReview = new Review(rating, loggedInUser.getUserID(), movieID, comment);
                        MovieDB.insertReview(newReview);
                    } catch (SQLException e) {
                        Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                        url = "/userReview.jsp";
                        errors.add("There was an error with the database please try again at a later time");
                        request.setAttribute("errors", errors);
                    }

                } else {
                    try {
                        ArrayList<Movie> movies = MovieDB.selectAllMovies();
                        request.setAttribute("movies", movies);
                        errors.add("You need to to have a comment.");
                        request.setAttribute("errors", errors);
                        url = "/userReview.jsp";
                    } catch (SQLException e) {
                        Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                    }
                }

                break;
            }

        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    private void logout(HttpServletRequest request) {
        request.getSession().invalidate();
        url = "/Public?action=gotoIndex";
    }

    private void updateReview(HttpServletRequest request) {
        int reviewId = (Integer.parseInt(request.getParameter("reviewId")));
        int movieId = (Integer.parseInt(request.getParameter("movieId")));
        int userId = (Integer.parseInt(request.getParameter("userID")));
        int reviewRating = (Integer.parseInt(request.getParameter("review-rating")));
        String reviewComments = ((String) request.getParameter("review-comments"));

        List<String> errors = new ArrayList<String>();

        if (reviewComments.equals("")) {
            errors.add("You must enter a comment.");
        }

        try {
            Review review = new Review();
            review.setReviewID(reviewId);
            review.setMovieID(movieId);
            review.setUserID(userId);
            review.setRating(reviewRating);
            review.setComment(reviewComments);
            MovieDB.updateReview(review);
        } catch (SQLException e) {
            errors.add("Failed to update your review. Please try again.");
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
