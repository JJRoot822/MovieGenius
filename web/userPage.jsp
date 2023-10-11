<%-- 
    Document   : userPage
    Created on : Oct 9, 2023, 1:23:53 PM
    Author     : tmdel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <nav>
            <p>
                <a href="movies.jsp">View Movies</a> 
            </p>
        </nav>
        <h1>User Name</h1>
        <h2>First Name and Last Name</h2>
        <form action="Private" method="post">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Logout">
        </form>
        
        <form action="Private" method="post">
            <input type="hidden" name="action" value="gotoUpdatePage">
            <input type="submit" value="Update Profile">
        </form>

    </body>
</html>