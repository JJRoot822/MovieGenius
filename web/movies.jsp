<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movies</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1>Movie List</h1>
            <h2>Current Movies</h2>

            <form method="post">
                
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
            </form>
            <br>
            <%--<c:forEach var="movie" items="${movies}">
                <form action="Private" method="post">
                    <input type="hidden" name="action" value="allMovies">
                </form>
            </c:forEach>
            <form action="Private" method="post">
                <input type="hidden" name="action" value="review">
                <input type="submit" value="Review">
            </form>--%>
        </main>
    </body>
</html>
