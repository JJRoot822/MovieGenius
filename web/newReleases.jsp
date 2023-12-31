<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="business.Movie"%>
<%
    if (session.getAttribute("loggedInUser") == null) {
        // Redirect to a different page if loggedInUser is not in the session
        response.sendRedirect("login.jsp"); // Replace "login.jsp" with the desired redirection URL
        return; // To stop further executio     n of JSP
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MovieGenius - New Releases</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">

            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <h1>New Releases</h1>
                    </div>
                    <table class="table table-striped" border="1" column="1">
                        <tr class="table-dark">
                            <th>Title</th>
                            <th>Average Rating</th>
                            <th>Release Date</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="movie" items="${newReleases}">
                            <tr>
                                <td>${movie.key.title}</td>
                                <td>${movie.value}</td>
                                <td>${movie.key.releaseDate}</td>
                                <td>
                                    <form method="post" action="Private">
                                        <input type="hidden" name="movieID" value="${movie.key.movieID}"/>
                                        <input type="hidden" name="action" value="review">
                                        <button type="submit" class="btn btn-success">Review</button>
                                    </form>
                                </td>
                                <td>
                                    <form action="Private" method="post">
                                        <input type="hidden" name="action" value="movieReviews">
                                        <input type="hidden" name="movieID" value="${movie.key.movieID}">
                                        <button type="submit" class="btn btn-success">Movie Reviews</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
    </body>
</html>
