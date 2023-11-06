package controllers;

import business.Movie;
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
            case "gotoUpdatePage": {
                url = "/updateUser.jsp";

                break;
            }
            case "logout": {
                logout(request);

                break;
            }
            case "updateUser": {
                String errorEmail = "";
                String errorNewPassword = "";
                String errorOldPassword = "";
                url = "/updateUser.jsp";
                try {
                    String newEmail = request.getParameter("email");
                    String newUserName = request.getParameter("userName");
                    if (!Validation.isEmail(newEmail)) {
                        errorEmail = "The email you entered is not a valid format. A valid format looks like this: example@somesite.com";
                        request.setAttribute("errorEmail", errorEmail);  
                        break;
                    }
                    String newPassword = request.getParameter("newPassword");
                    String re_enterPassword = request.getParameter("checkNewPassword");
                    String oldPassword = request.getParameter("oldPassword");
                    if (newPassword.equals(re_enterPassword)) {
                        newPassword = SecurityUtil.hashPassword(newPassword);
                        if (newPassword.equals(loggedInUser.getPassword())) {
                            url = "/updateUser.jsp";
                        } else if (!SecurityUtil.isMatchingPassword(oldPassword, loggedInUser.getPassword())) {
                            url = "/updateUser.jsp";
                            errorOldPassword = "The password must be the same as the old password";
                            request.setAttribute("errorOldPassword", errorOldPassword);
                        } else {
                            loggedInUser.setEmail(newEmail);
                            loggedInUser.setUsername(newUserName);
                            loggedInUser.setPassword(newPassword);
                            //MovieDB.updateUser(loggedInUser);
                            url = "/userPage.jsp";
                        }
                    }
                    else{
                        errorNewPassword = "The re-enter password must be the same as the New Password";
                        request.setAttribute("errorNewPassword", errorNewPassword);
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                break;
            }
            case "deleteAccount": {
                int userID = 0;
                try {
                    userID = loggedInUser.getUserID();
                } catch (Exception e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                try {
                    MovieDB.deleteUser(userID);
                    url = "/login.jsp";
                    request.getSession().invalidate();
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }

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
            case "test": {
                url = "/test.jsp";
                ArrayList<Movie> top10Movies = new ArrayList();
                ArrayList<Double> top10Ratings = new ArrayList();
                try {
                    
                    top10Movies = MovieDB.getTop10();
                    top10Ratings = MovieDB.getTop10Avgs();
                } catch (SQLException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                
                
                request.setAttribute("top10Movies", top10Movies);
                request.setAttribute("top10Ratings", top10Ratings);
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
        int reviewId = ((int) request.getParameter("reviewId"));
        int movieId = ((int) request.getParameter("movieId"));
        int userId = ((int) request.getParameter("userID"));
        int reviewRating = ((int) request.getParameter("review-rating"));
        String reviewComments = ((String) request.getParameter("review-comments"));
        
        List<String> errors = new ArrayList<String>();
        
        if reviewCcomments.equals("")) {
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
