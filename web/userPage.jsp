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
        <title>User Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="MovieGenius.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp" />
        <main id="main-content">
            <h1>Welcome <c:out value="${loggedInUser.username}"/></h1>
            <form action="Private" method="post">
                <input type="hidden" name="action" value="logout">
                <input type="submit" value="Logout">
            </form>
            
            <form action="Private" method="post">
                <input type="hidden" name="action" value="gotoUpdatePage">
                <input type="submit" value="Update Profile">
            </form>
            
            <form action="Private" method="post">
                <input type="hidden" name="action" value="gotoUserReview">
                <input type="submit" value="User Review">
            </form>
        </main>
    </body>
</html>