<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="business.Movie"%>
<%
    if (session.getAttribute("loggedInUser") == null) {
        // Redirect to a different page if loggedInUser is not in the session
        response.sendRedirect("login.jsp"); // Replace "login.jsp" with the desired redirection URL
        return; // To stop further executio     n of JSP
    }
    Movie movie = (Movie) request.getAttribute("movie");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Review</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <div class="container">
                <div class="row">
                    <h1 class="text-center">${movie.title}</h1>
                    <h2 class="text-center">${movie.releaseDate}</h2>
                    <h3 class="text-center">${movie.summary}</h3>

                    <h2 class="text-center">Reviews</h2>
                    <c:if test="${empty userReviews}">
                        <h5 class="text-secondary text-center">There are no reviews for this movie yet.</h5>
                    </c:if>

                    <c:if test="${not empty userReviews}">
                        <table class="table table-striped" border="1" column="1">
                            <tr class="table-dark">
                                <th>Username</th>
                                <th>Comment</th>
                                <th>Rating</th>
                            </tr>
                            <c:forEach var="userReviews" items="${userReviews}">
                                <tr>
                                    <td>${userReviews.username}</td>
                                    <td>${userReviews.comment}</td>
                                    <td>${userReviews.rating}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </div>
            </div>
        </main>
    </body>
</html>
