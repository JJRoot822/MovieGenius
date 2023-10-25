package controllers;

import business.User;
import data.MovieDB;
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
                String newEmail = request.getParameter("email");
                String newUserName = request.getParameter("userName");
                String newPassword = request.getParameter("newPassword");
                String re_enterPassword = request.getParameter("checkNewPassword");
                String oldPassword = request.getParameter("oldPassword");
                try {
                    if (newPassword.equals(loggedInUser.getPassword())){
                        url = "/updateUser.jsp";
                    }
                    else if (!newPassword.equals(re_enterPassword)){
                        url = "/updateUser.jsp";
                    }
                    else if (!loggedInUser.getPassword().equals(oldPassword)){
                        url = "/updateUser.jsp";
                    }
                    else{
                        loggedInUser.setEmail(newEmail);
                        loggedInUser.setUsername(newUserName);
                        loggedInUser.setPassword(newPassword);
                        MovieDB.updateUser(loggedInUser);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "deleteAccount": {
                /*int userID = 0;
                try {
                    userID = loggedInUser.getUserID();
                } catch (Exception e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                try {
                    MovieDB.deleteUser(userID);
                    url = "/login.jsp";
                } catch (SQLException ex) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
                break;
            }
            case "deleteUser": {
                try {
                    MovieDB.deleteUser(4);
                } catch (SQLException e) {
                    Logger.getLogger(Private.class.getName()).log(Level.SEVERE, null, e);
                }
                url = "/test.jsp";
                break;
                
            }
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    private void logout(HttpServletRequest request) {
        request.getSession().invalidate();
        url = "/Public?action=gotoIndex";
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

















