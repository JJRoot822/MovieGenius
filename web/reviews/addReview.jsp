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
            <h1>Submit a Review for ${movie.title}</h1>
            <c:forEach items="${requestScope.errors}" var="error">
                <p class="text-danger">${error}</p>
            </c:forEach>
            <div class="container">
                <div class="row">
                    <div class="col-3"></div>
                    <div class="6">
                        <form action="Private" method="put">
                            <input type="hidden" name="action" value="submitReview">
                            <label for="review-rating-field">Rating</label>
                            <select id="rating-field" name="selectedNumber" class="form-control">
                                <option value="" disabled selected>Select a rating number</option>
                                <% for (int i = 1; i <= 10; i++) {%>
                                <option value="<%= i%>"><%= i%></option>
                                <% }%>
                            </select>
                            </br>
                            <label for="comment">Comments</label>
                            <textarea id="comment" class="form-control" name="review-comments" maxlength="255">${review.comment}</textarea>
                            <br>
                            <input type="submit" value="Submit" id="submit-review-btn" class="btn btn-success">
                        </form>
                    </div>
                    <div class="col-3"></div>
                </div>
            </div>
        </main>
    </body>
</html>