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
        <title>MovieGenius - Update User Info</title>
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
                        <c:if test="${not empty errors}">
                            <ul class="list-unstyled">
                                <c:forEach items="${errors}" var="error">
                                    <li class="text-danger">${error}</li>
                                    </c:forEach>
                            </ul>
                        </c:if>
                        <form action="Private" method="post">
                            <input type="hidden" name="action" value="updateUser">
                            <h1>Update Information</h1>
                            <label for="email-field">Email</label><br>
                            <input type="text" name="email" id="email-field" class="form-control"/>
                            <br>
                            <label for="username-field">Username</label><br>
                            <input type="text" name="userName" id="username-field" class="form-control"/>
                            <br>
                            <label for="new-password-field">New Password</label><br>
                            <input type="text" name="newPassword" id="new-password-field" class="form-control"/>
                            <br>
                            <label for="verify-new-password-field">Verify New Password</label><br>
                            <input type="text" name="checkNewPassword" id="verify-password-field" class="form-control"/>
                            <br>
                            <button type="submit" class="btn btn-success">Update Account</button>
                        </form>
                        <br><br><br>
                        <form action="Private" method="post">
                            <button type="submit" class="btn btn-success">Delete Account</button>
                            <input type="hidden" name="action" value="deleteAccount">
                        </form>
                        <div class="col-4"></div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
