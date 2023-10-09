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
        <input type="submit" value="Logout">
        <br><br>
        <label>First Name:</label>
        <input type="text" name="firstName">
        <br><br>
        <label>Last Name:</label>
        <input type="text" name="lastName">
        <br><br>
        <label>User Name:</label>
        <input type="text" name="userName">
        <br><br>
        <label>Enter New Password:</label>
        <input type="text" name="newPassword">
        <br><br>
        <label>Enter Old Password:</label>
        <input type="text" name="oldPassword">
        <br><br>
        <input type="submit" value="Update">
        <br><br>
        <label>Viewable: </label>
            <br>
            <input type="radio" id="public" name="viewable" value="drill" checked="true">
            <label>Public</label>
            <br>
            <input type="radio" id="private" name="viewable" value="test">
            <label>Private</label>
            <br><br>
        <input type="submit" value="Delete Account">
    </body>
</html>