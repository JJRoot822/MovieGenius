<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Movie Filter</title>
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
                        <form action="Private" method="post">
                            <input type="hidden" name="action" value="filter">
                            <select name="genreID" id="genreID">
                                <c:forEach var="genres" items="${genres}">
                                    <option value="${genres.genreID}">${genres.genreName}</option>
                                </c:forEach>
                            </select>
                            <br>
                            <button type="submit" class="btn btn-success">Filter</button>
                        </form>
                        <br>
                        <c:if test="${not empty movies}">
                            <table border="1" column="1">
                                <tr>
                                    <th>Title</th>
                                    <th>Summary</th>
                                    <th>Release Date</th>
                                </tr>
                                <c:forEach var="movies" items="${movies}">
                                    <tr>
                                        <td>${movies.title}</td>
                                        <td>${movies.summary}</td>
                                        <td>${movies.releaseDate}</td>
                                        <td>
                                            <input type="hidden" name="action" value="review">
                                             <button type="submit" class="btn btn-success">Review</button>
                                        </td>
                                        <td>
                                            <form action="Private" method="post">
                                                <input type="hidden" name="action" value="movieReviews">
                                                <input type="hidden" name="movieID" value="${movies.movieID}">
                                                <button type="submit" class="btn btn-success">Movie Reviews</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                        <div class="col-4"></div>
                    </div>
                </div>
                
            </div>
        </main>
    </body>
</html>