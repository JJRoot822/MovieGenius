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
        <title>MovieGenius - <c:out value="${loggedInUser.username}" />&apos;s Profile</title>
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
                        <h1>Welcome <c:out value="${loggedInUser.username}"/></h1>

                        <form action="Private" method="post">
                            <input type="hidden" name="action" value="gotoUpdatePage">
                            <button type="submit" class="btn btn-success">Update Profile</button>
                        </form><br>

                        <form action="Private" method="post">
                            <button type="submit" class="btn btn-success">User Review</button>
                            <input type="hidden" name="action" value="gotoUserReview">
                        </form>
                        <div class="col-4"></div>
                    </div>
                </div>

                <div class="row">
                    <form action="Private" method="get">
                        <input type="search" id="movie-search-field" class="col-10 form-control" name="search-term" placeholder="Search for a Movie..." />
                    <input type="hidden" name="action" value="movie-review-search" />
                        <input type="submit" class="col-2 btn btn-dark" value="Go" />
                    </form>
                </div>

                <div class="row">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <ul class="list-unstyled">
                            <c:forEach items="${errors}" var="error">
                                <li class="text-danger">
                                    ${error}
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="col-4"></div>
                </div>

                <div class="row">
                    <div class="col-4"></div>
                    <p class="col-4 text-success">${successMessage}</p>
                    <div class="col-4"></div>
                </div>

                <div class="row">
                    <c:choose>
                        <c:when test="${searchResults == null}">
                            <table class="table table-striped">
                                <thead>
                                    <tr class="table-dark">
                                        <th>Movie Title</th>
                                        <th>Review Rating</th>
                                        <th>Comments</th>
                                        <th colspan="2">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${reviews.entrySet}" var="review">
                                        <tr>
                                            <td>${review.value.movieTitle}</td>
                                            <td>${review.value.reviewRating}</td>
                                            <td>${review.value.reviewComment}</td>
                                            <td>
                                                <form action="Private" method="post">
                                                    <input type="hidden" value="<c:out value='${review.key}' />" name="reviewId" />
                                                    <input type="hidden" value="goto-update-review" name="action"

                                                    <button class="btn btn-light" type="submit">
                                                        Edit
                                                    </button>
                                                </form>
                                            </td>
                                            <td>
                                                <form action="Private" method="delete">
                                                    <input type="hidden" name="action" value="delete-review">
                                                    <input type="hidden" value="<c:out value='${review.key}' />" name="reviewId" />
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-striped">
                                <thead>
                                    <tr class="table-dark">
                                        <th>Movie Title</th>
                                        <th>Review Rating</th>
                                        <th>Comments</th>
                                        <th colspan="2">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${searchResults.entrySet}" var="review">
                                        <tr>
                                            <td>${review.value.movieTitle}</td>
                                            <td>${review.value.reviewRating}</td>
                                            <td>${review.value.reviewComment}</td>
                                            <td>
                                                <form action="Private" method="post">
                                                    <input type="hidden" value="<c:out value='${review.key}' />" name="reviewId" />
                                                    <input type="hidden" value="update-review" name="action" />

                                                    <button class="btn btn-light" type="submit">
                                                        Edit
                                                    </button>
                                                </form>
                                            </td>
                                            <td>
                                                <form action="Private" method="delete">
                                                    <input type="hidden" name="action" value="delete-review">
                                                    <input type="hidden" value="<c:out value='${review.key}' />" name="reviewId" />
                                                    <button type="submit" class="btn btn-danger">Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div
            </div>
        </main>
    </body>
</html>