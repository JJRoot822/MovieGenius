<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <title>MovieGenius - Admin Movie List</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="/layout/navbar.jsp" />
        <main id="main-content">
            <h1>Admin Movie Page</h1>
            <br>
            <div class="container">
                <div class="row">
                    <div class="col-4"></div>
                    
                    <div class="col-4">
                        <form action="Private" method="post">
                            <input type="hidden" name="action" value="addMovie">
                            <label for="title-field">Movie Title</label>
                            <br>
                            <input type="text" id="title-field" name="title" class="form-control">
                            <br>
                            <label for="summary-field">Movie Summary</label>
                            <br>
                            <textarea id="summary-field" name="summary" rows="5" cols="50" class="form-control"></textarea>
                            <br>
                            <label for="release-date-field">Release Date</label>
                            <br>
                            <input type="date" id="release-date-field" name="releasedate" class="form-control">
                            <br>
                            <label for="genre-field">Rating</label>
                            <br>
                            <select id="genre-field" name="genre" class="form-control">
                                <option value="" disabled selected>Select a movie genre.</option>
                                <c:forEach var="genre" items="${genres}">
                                    <option value="${genre.genreID}">${genre.genreName}</option>
                                </c:forEach>
                            </select>
                            <br>
                            <button type="submit" class="btn btn-success">Add Movie</button>
                        </form>
                    </div>
                    
                    <c:forEach var="genre" items="${genres}">
                        <p>${genre.genreID} ${genre.genreName}</p>
                    </c:forEach>
                        
                        
                    
                    <div class="col-4"></div>
                </div>
            </div>
        </main>
    </body>
</html>