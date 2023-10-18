package controllers;

import data.MovieDB;
import data.security.SecurityUtil;
import data.security.AuthenticationService;
import business.Validation;
import business.User;
import data.security.AuthenticationService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.realm.SecretKeyCredentialHandler;

/**
 *
 * @author tmdel
 */
public class Public extends HttpServlet {
    String url = "";
    User loggedInUser = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger LOG = Logger.getLogger(Public.class.getName());
        
        url = "/index.jsp";
        String action = request.getParameter("action");
        if (action == null) {
            action = "default";
        }

        switch (action) {
            case "login": {
                login(request);
                getServletContext().getRequestDispatcher(url).forward(request, response);
                break;
            }
            case "logout": {
                HttpSession session;
                session = request.getSession();
                session.invalidate();

                url = "/Public?action=gotoIndex";
                break;
            }
            case "gotoIndex": {
                url = "/index.jsp";
                break;
            }
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

    
    private void login(HttpServletRequest request) {
        String usernameOrEmail = ((String) request.getParameter("email-or-password"));
        String password = ((String) request.getParameter("password"));
                
        boolean wasLogInSuccessful = AuthenticationService.shared.login(usernameOrEmail, password);
                
        List<String> errors = new ArrayList<String>();
                
        if (wasLogInSuccessful) {
            try {
                loggedInUser = MovieDB.getUserInfo(usernameOrEmail);
                request.getSession().setAttribute("loggedInUser", loggedInUser);
                
                url = "/user-page.jsp";
            } catch (Exception e) {
                errors.add("A user with the provided details does not exist.");
                        
                url = "/login.jsp";
            }
        } else {
            errors.add("Invalid username/email or password");
            url = "/login.jsp";
        }
    }
}
