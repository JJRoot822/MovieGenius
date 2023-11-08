<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if (session.getAttribute("loggedInUser") == null) {
        response.sendRedirect("Public");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Genius - My Reviews</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1>My Reviews</h1>

            <div class="container">
                <div class="row">
                    <form action="Private" method="get">
                        <label class="col-1" for="movie-search-field">Search for a Movie</label>
                        <input type="search" id="movie-search-field" class="col-3 form-control" name="search-term" placeholder="Search..." />
                        <input type="hidden" name="action" value="moviereviewsearch" />
                        <input type="submit" class="col-1 btn btn-dark" value="Go" />
                    </form>
                    <div class="col-7"></div>
                </div>
                <div class="row">
                    <c:choose>
                        <c:when test="${not empty searchResults}">
<!-- Display reviews for movies who's title contains the search term -->
                        </c:when>
                        <c:otherwise>
                            <!-- Show all user's reviews -->
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </main>
    </body>
</html>
