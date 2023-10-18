<%-- 
    Document   : index
    Created on : Oct 9, 2023, 1:23:21 PM
    Author     : tmdel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Genius - Home</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="MovieGenius.css" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </head>
    <body>
        <a href="#main-content" id="stmc">Skip to Main Content</a>

        <header>
            <nav class="navbar bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand">MovieGenius</a>

                    <ul>
                        <li class="nav-item text-dark">
                            <a href="index.jsp" class="nav-link">Home&emsp;&#124;</a>
                        </li>
                        <li class="nav-item">
                            <a href="movies.jsp" class="nav-link">&emsp;Movies&emsp;&#124;</a>
                        </li>
                        <c:if test="${not empty sessionScope.loggedInUser}">
                            <li class="nav-item">
                                <form action="Private" method="post">
                                    <input type="hidden" value="logout" name="action" />
                                    <button role="link" type="submit" class="nav-link text-dark">&emsp;Log Out&emsp;&#124;</button>
                                </form>
                            </li>
                        </c:if>
                        <c:if test="${empty sessionScope.loggedInUser}">
                            <li class="nav-item">
                                <a href="login.jsp" class="nav-link text-dark">&emsp;Log In&emsp;&#124;</a>
                            </li>

                            <li class="nav-item">
                                <a href="register.jsp" class="nav-link text-dark">&emsp;Register</a>
                            </li>
                        </c:if>

                    </ul>

                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search for a Movie" aria-label="Search for a Movie">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </nav>
        </header>
        <h1>Welcome to Movie Genius!</h1>
    </body>
</html>
