<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Filter</title>
    </head>
    <body>
        <form action="Private" method="post">
            <input type="hidden" name="action" value="filter">
            <select name="genreID" id="genreID">
                <c:forEach var="genres" items="${genres}">
                    <option value="${genres.genreID}">${genres.genreName}</option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Filter">
        </form>
        <br>
        <c:if test="${not empty movies}">
            <table border="1" column="1">
                <tr>
                    <th>Title</th>
                    <th>Summary</th>
                    <th>Release Date</th>
                </tr>
                <c:forEach var="movie" items="${allMovies}">
                    <tr>
                        <td>${movie.title}</td>
                        <td>${movie.summary}</td>
                        <td>${movie.releaseDate}</td>
                        <td>
                            <input type="hidden" name="action" value="review">
                            <input type="submit" value="Review">
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

    </body>
</html>