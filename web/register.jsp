<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" type="text/css" href="tobemade.css" /> <!-- need to make css page -->
    </head>
    <body>
        <div id="wrapper">
            <header>
                <h1>Register To Movie Genius</h1>
            </header>
            <nav>

            </nav>   
            <main>
                <div class="logout">
                    <form action="Public">
                        <input type="hidden" name="action" value="logout">
                        <input type="submit" value="Logout">
                    </form>
                </div>
                <div>
                    <c:forEach var="errors" items="${message}">
                        <h3>${errors.value}</h3>
                    </c:forEach>
                </div>
                <div class="form">
                    <form action="Public" method="post">
                        <input type="hidden" name="action" value="register">

                        <label>Username: </label>
                        <input type="text" name="username" value="<c:out value="${user.username}"/>"><br>

                        <label>Password: </label>
                        <input type="text" name="password"><br>

                        <label>Email: </label>
                        <input type="text" name="email" value="<c:out value="${user.email}"/>"><br>

                        <br>
                        <input type="submit" value="Register">
                    </form>
                </div>
            </main>
            <footer>
                <p>Copyright &#169;2023</p>
            </footer>
        </div>
    </body>
</html>
