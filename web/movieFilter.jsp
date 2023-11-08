
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Filter</title>
    </head>
    <body>
        <select name="genreID" id="genreID">
            <c:forEach var="movies" items="${genres}">
                <option value="${genres.genreName}">${genres.title}</option>
            </c:forEach>
        </select>
    </body>
</html>
