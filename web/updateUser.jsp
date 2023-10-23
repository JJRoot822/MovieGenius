<%-- 
    Document   : updateUser
    Created on : Oct 9, 2023, 2:36:06 PM
    Author     : tmdel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MovieGenius - Update User Info</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <a href="#main-content" id="stmc">Skip to Main Content</a>

        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="index.jsp">Movie Genius</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#movie-genius-nav" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="movie-genius-nav">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item text-dark">
                            <a href="/" class="nav-link">Home</a>
                        </li>
                        <li class="nav-item">
                            <a href="movies.jsp" class="nav-link">Movies</a>
                        </li>
                        <c:choose>
                            <c:when test="${not empty loggedInUser}">
                                <li class="nav-item">
                                    <form action="Private" method="post">
                                        <input type="hidden" value="logout" name="action" />
                                        <button role="link" type="submit" class="nav-link text-dark">Log Out</button>
                                    </form>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a href="login.jsp" class="nav-link text-dark">Log In</a>
                                </li>

                                <li class="nav-item">
                                    <a href="register.jsp" class="nav-link text-dark">Register</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </nav>      
        </header>
        <main id="main-content">
            <form action="Private" method="post">
            <input type="hidden" name="action" value="updateUser">
            <h1>Update Information</h1>
            <label>Email:</label>
            <input type="text" name="email">
            <br><br>
            <label>User Name:</label>
            <input type="text" name="userName">
            <br><br>
            <label>Enter New Password:</label>
            <input type="text" name="newPassword">
            <br><br>
            <label>Enter Re-enter New Password:</label>
            <input type="text" name="checkNewPassword">
            <br><br>
            <label>Enter Old Password:</label>
            <input type="text" name="oldPassword">
            <br><br>
            <label>Viewable: </label>
            <br>
            <input type="radio" id="public" name="viewable" value="drill" checked="true">
            <label>Public</label>
            <br>
            <input type="radio" id="private" name="viewable" value="test">
            <label>Private</label>
            <br><br>
            <input type="submit" value="Update">
        </form>
        <br><br><br>
        <form action="Private" method="post">
            <input type="hidden" name="action" value="deleteAccount">
            <input type="submit" value="Delete Account">
        </form>
        </main>
    </body>
</html>
