<%@page import="data.MovieDB"%>
<%@page import="business.Movie"%>
<%@page import="java.util.List"%>
<%@page import="business.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    //code to direct users out off of the page if they're not logged in
    User jspUser = (User) request.getSession().getAttribute("loggedInUser");
    if (jspUser == null) {
        response.sendRedirect("Public");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Genius - User Reviews</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1>User Reviews</h1>
            <c:if test="${not empty errors}">
                <ul class="list-unstyled">
                    <c:forEach items="${errors}" var="error">
                        <li class="text-danger">${error}</li>
                        </c:forEach>
                </ul>
            </c:if>
            <form>
                <input type="hidden" name="action" value="submitReview">
                <select name="movieID" id="movieID">
                    <c:forEach var="movies" items="${movies}">
                        <option value="${movies.movieID}">${movies.title}</option>
                    </c:forEach>
                </select>
                <br><br>
                <label>Rating: </label>
                <select name="rating" id="rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
                <br><br>
                <textarea id="comment" name="comment" rows="5" cols="50"></textarea>
                <br>
                <input type="submit" value="Submit Reivew">
            </form>
        </main>
    </body>
</html>
