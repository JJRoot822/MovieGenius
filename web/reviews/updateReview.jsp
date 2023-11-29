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
        <title>MovieGenius - Update Review</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="/layout/navbar.jsp" />
        <main id="main-content">
            <h1>Update Review for ${movie.title}</h1>

            <div class="container">
                <div class="row">
                    <div class="col-3"></div>
                    <div class="6">
                        <form action="Private" method="put">
                            <c:forEach items="${requestScope.errors}" var="error">
                                <p class="text-danger">${error}</p>
                            </c:forEach>
                            
                            <input type="hidden" value="${review.reviewID}" name="reviewID" />
                            <input type="hidden" value="${review.movieID}" name="movieId" />
                            <input type="hidden" value="${review.userID}" name="userId" />
                            <input type="hidden" value="update-review" name="action" />

                            <label for="review-rating-field">Rating</label>
                            <br>
                            <input type="number" id="review-rating-field" class="form-control" min="1" max="10" step="1" value="<c:out value='${review.rating}' />" name="review-rating" />

                            <br>

                            <label for="review-content-field">Comments</label>
                            <textarea id="review-content-field" class="form-control" name="review-comments" maxlength="255">${review.comment}</textarea>

                            <br>

                            <input type="submit" value="Update" id="update-review-btn" class="btn btn-success">
                        </form>
                    </div>
                    <div class="col-3"></div>
                </div>
            </div>
        </main>
    </body>
</html>