<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MovieGenius - Registration</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <style>
            #stmc {
                position: absolute;
                left: -999999px;
                top: 999999px;
                text-decoration: none;
            }
        </style>> <!-- need to make css page -->
    </head>
    <body>
        <a href="#main-content" id="stmc">Skip to Main Content</a>

        <header>
            <nav class="navbar bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand">MovieGenius</a>

                    <ul>
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

                  <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search for a Movie" aria-label="Search for a Movie">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                  </form>
                </div>
              </nav>
        </header>
        <main id="main-content">
            <h1>Register for MovieGenius</h1>
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    
                    <div class="col-4">
                        <form method="post" action="Public">
                            <label for="email-field">Email</label>
                            <br>
                            <input type="email" id=email-field" name=email" class="form-control">

                            <br>
                            <br>

                            <label for="username-field">Username</label>
                            <br>
                            <input type="text" id=username-field" name=username" class="form-control">

                            <br>
                            <br>

                            <label for="password-field">Password</label>
                            <br>
                            <input type="password" id="password-field" name="password" class="form-control">
                            
                            <br>
                            <br>

                            <label for=verify-"password-field">Verify Password</label>
                            <br>
                            <input type="password" id="verify-password-field" name="verify-password" class="form-control">
                            <br>
                            <button class="btn btn-outline-secondary" id="show-password-btn">Show Password</button
                                
                                <br>
                                <br>
                                <br>

                                <input type="hidden name=action" value="login">
                                <button type="submit" class="btn btn-success">Create Account</button>
                        </form>
                    </div>
                    
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
        <script>
            let showPasswordButton = document.getElementById("show-password-btn");
            let passwordField = document.getElementById("password-field");
            let verifyPasswordField = document.getElementById("verify-password");

            showPasswordButton.addEventListener('click', () => {
                passwordField.type = "text";
                verifyPasswordField.type = "text";

                setTimeout(() => {
                    passwordField.type = "password";
                    verifyPasswordField.type = "password";
                }, 5000);
            })
        </script>
    </body>
</html>
