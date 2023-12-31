package controllers;

import business.*;
import data.MovieDB;
import data.security.SecurityUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.time.LocalDate;
import java.util.*;

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
            case "gotoMovieFilter": {
                url = "/movieFilter.jsp";
                List<Genre> genres = new ArrayList<>();
                try {
                    genres = MovieDB.selectAllGenres();
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("genres", genres);
                break;
            }
            case "movieReviews": {
                url = "/movieReviews.jsp";
                ArrayList<userReview> userReviews = new ArrayList();
                Movie movie = new Movie();
                int movieID = 0;
                try {
                    movieID = Integer.parseInt(request.getParameter("movieID"));
                } catch (NumberFormatException en) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, en);
                }

                try {
                    movie = MovieDB.SelectedMoive(movieID);
                    userReviews = MovieDB.getMovieUserReview(movieID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("movie", movie);
                request.setAttribute("userReviews", userReviews);

                break;
            }
            case "filter": {
                url = "/Private?action=gotoMovieFilter";
                List<Movie> movies = new ArrayList<>();
                List<Genre> genres = new ArrayList<>();
                int genreID = 0;
                try {
                    genres = MovieDB.selectAllGenres();
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    genreID = Integer.parseInt(request.getParameter("genreID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }

                try {
                    movies = MovieDB.getMoviesByGenreID(genreID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (genreID == 0) {
                    url = "/Private?action=gotoMovieFilter";
                } else {
                    request.setAttribute("movies", movies);
                    request.setAttribute("genres", genres);
                }
                break;
            }
            case "delete-review": {
                deleteReview(request);
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
                    List<String> errors = new ArrayList<>();

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

                        url = "/Private?action=gotoUserPage";

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
                } catch (NumberFormatException e) {
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
            case "newReleases": {
                url = "/newReleases.jsp";
                ArrayList<Movie> newReleases = new ArrayList();
                ArrayList<Double> newReleasesRatings = new ArrayList();
                try {

                    newReleases = MovieDB.getNewReleases();
                    newReleasesRatings = MovieDB.getNewReleaseRatings();
                } catch (SQLException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                LinkedHashMap<Movie, String> newMap = new LinkedHashMap();
                for (int i = 0; i < newReleases.size(); i++) {
                    String ratingString = newReleasesRatings.get(i).toString();
                    if (newReleasesRatings.get(i) == 0) {
                        ratingString = "N/A";
                    }
                    newMap.put(newReleases.get(i), ratingString);
                }
                request.setAttribute("newReleases", newMap);
                break;
            }
            case "review": {
                url = "/reviews/addReview.jsp";

                Movie movie = new Movie();

                int movieID = 0;

                try {
                    movieID = Integer.parseInt(request.getParameter("movieID"));
                } catch (NumberFormatException en) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, en);
                }

                try {
                    movie = MovieDB.SelectedMoive(movieID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

                HttpSession session = request.getSession();
                session.setAttribute("movieID", movieID);
                request.setAttribute("movie", movie);

                break;
            }
            case "submitReview": {
                int movieID = 0;

                try {
                    movieID = Integer.parseInt(request.getParameter("movieID"));
                } catch (NumberFormatException en) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, en);
                }

                int rating = 0;
                String comment = request.getParameter("comment");

                try {
                    rating = Integer.parseInt(request.getParameter("rating"));
                } catch (NumberFormatException en) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, en);

                }

                Review review = new Review(rating, loggedInUser.getUserID(), movieID, comment);

                try {
                    MovieDB.insertReview(review);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                url = "/Private?action=movieList";

                break;
            }
            case "gotoUpdateReview": {
                navigateToUpdateReview(request);
                break;
            }
            case "update-review": {
                updateReview(request);
                break;
            }
            case "gotoUserPage": {
                url = "/userPage.jsp";
                List<UserReviewData> userReview = new ArrayList<>();
                int userID = loggedInUser.getUserID();

                try {
                    userReview = MovieDB.selectSpecificUsersDataReview(userID);
                } catch (SQLException e) {
                    Logger.getLogger(MovieDB.class.getName()).log(Level.SEVERE, null, e);
                }

                request.setAttribute("userReview", userReview);

                break;
            }
            case "userDeleteReview": {
                int userID = (int) loggedInUser.getUserID();
                int reviewID = 0;

                try {
                    reviewID = Integer.parseInt(request.getParameter("reviewID"));
                } catch (NumberFormatException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }

                try {
                    MovieDB.deleteReview(userID, reviewID);
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

                url = "/Private?action=gotoUserPage";

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
        int reviewId = (Integer.parseInt(request.getParameter("reviewID")));
        int movieId = (Integer.parseInt(request.getParameter("movieID")));
        int userId = (Integer.parseInt(request.getParameter("userID")));
        int reviewRating = (Integer.parseInt(request.getParameter("review-rating")));
        String reviewComments = ((String) request.getParameter("review-comments"));

        List<String> errors = new ArrayList<>();

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

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
        }

        url = "/Private?action=gotoUserPage";
    }

    private void navigateToUpdateReview(HttpServletRequest request) {
        int reviewId = 0;
        List<String> errors = new ArrayList<>();

        try {
            reviewId = Integer.parseInt(request.getParameter("reviewID"));
        } catch (NumberFormatException e) {
            url = "/Private?action=gotoUserPage";

            errors.add("The review is not an integer like it should, which means it was tampered with.");
        }

        Review review = null;

        try {
            review = MovieDB.getReviewById(reviewId);
        } catch (SQLException e) {
            url = "/Private?action=gotoUserPage";
            errors.add("Failed to navigate to the update review page. Please try again later.");
        }

        if (errors.size() < 1) {
            request.setAttribute("review", review);
            url = "/reviews/updateReview.jsp";
        } else {
            request.setAttribute("errors", errors);
        }
    }

    private void deleteReview(HttpServletRequest request) {
        int reviewId;
        List<String> errors = new ArrayList<>();

        try {
            reviewId = Integer.parseInt(request.getParameter("reviewId"));

            MovieDB.deleteReview(reviewId);

            request.setAttribute("successMessage", "Successfully deleted the review. Just a reminder, this can't be undone, so If you want to have the old review back, you need to rewrite the review.");
        } catch (NumberFormatException e) {
            errors.add("Something went wrong with the id of the review, as it's not an integer like it should be.");
        } catch (SQLException e) {
            errors.add("Failed to delete the review you tried to delete. Please try again later.");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
        }

        url = "/Private?action=gotoUserPage";
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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
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
